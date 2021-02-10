package de.mkienitz.bachelorarbeit.order.application;

import de.mkienitz.bachelorarbeit.order.application.cart.CartServiceClient;
import de.mkienitz.bachelorarbeit.order.domain.Order;
import de.mkienitz.bachelorarbeit.order.domain.Receipt;
import de.mkienitz.bachelorarbeit.order.domain.ShoppingCart;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.opentracing.Traced;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/order")
@RequestScoped
public class OrderResource {

    private static final Logger log = LoggerFactory.getLogger(OrderResource.class.getName());

    @Inject
    private OrderService service;

    @Inject
    private CartServiceClient cartServiceClient;

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
        } catch (WebApplicationException e) {
            log.error("order(): could not request shoppingCart, Exception = ", e);

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Warenkorb konnte nicht abgerufen werden: " + e.getMessage()).build();
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
