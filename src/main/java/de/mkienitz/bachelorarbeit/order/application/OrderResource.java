package de.mkienitz.bachelorarbeit.order.application;

import de.mkienitz.bachelorarbeit.order.domain.Order;
import de.mkienitz.bachelorarbeit.order.domain.Receipt;
import de.mkienitz.bachelorarbeit.order.domain.ShoppingCart;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.opentracing.Traced;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 */
@Path("/order")
@Singleton
public class OrderResource {

    public static final String SYS_ENV_VAR_CART_SERVICE_URL = "BA_CART_SERVICE_URL";

    private static Logger log = LoggerFactory.getLogger(OrderResource.class.getName());

    @Inject
    private OrderService service;

    private CartServiceClient cartServiceClient;

    public OrderResource() throws MalformedURLException {
        String cartServiceUrl = System.getenv(SYS_ENV_VAR_CART_SERVICE_URL);

        log.info(String.format("OrderResource(): env.%s = %s", SYS_ENV_VAR_CART_SERVICE_URL, cartServiceUrl));

        log.debug("OrderResource(): creating CartServiceClient");

        URL cartServiceUrl2 = new URL(cartServiceUrl);

        CartServiceClient cartServiceClient = RestClientBuilder
                .newBuilder()
                .baseUrl(cartServiceUrl2)
                .build(CartServiceClient.class);

        log.debug("OrderResource(): successfully created CartServiceClient");

        this.cartServiceClient = cartServiceClient;
    }

    @POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    @Traced(operationName = "OrderResource.order")
    public Response order(
            @RequestBody(required = true, content = @Content(schema = @Schema(implementation = Order.class)))
            @NotNull @Valid Order order
    ) {
        ShoppingCart shoppingCart;

        try {
            shoppingCart = cartServiceClient.getShoppingCart(order.getShoppingCartId());
        } catch (Exception e) {
            log.error("order(): could not request shoppingCart, Exception = ", e);

            return Response.status(Response.Status.BAD_REQUEST.getStatusCode(), "Warenkorb konnte nicht abgerufen werden: " + e.getMessage()).build();
        }

        try {
            Receipt receipt = service.placeOrder(order, shoppingCart);

            return Response.ok(receipt).build();
        } catch (InvalidOrderException e) {
            log.error("order(): could not place order, Exception = ", e);

            return Response.status(Response.Status.BAD_REQUEST.getStatusCode(), e.getMessage()).build();
        }
    }
}
