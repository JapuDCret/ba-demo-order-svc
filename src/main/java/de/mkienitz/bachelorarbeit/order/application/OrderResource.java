package de.mkienitz.bachelorarbeit.order.application;

import de.mkienitz.bachelorarbeit.order.domain.Order;
import de.mkienitz.bachelorarbeit.order.domain.Receipt;
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
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/order")
@RequestScoped
public class OrderResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderResource.class.getName());

    @Inject
    private OrderApplicationService service;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Traced(operationName = "OrderResource.order")
    public Response order(
            @RequestBody(required = true, content = @Content(schema = @Schema(implementation = Order.class)))
            @NotNull @Valid Order order
    ) {
        try {
            Receipt receipt = service.placeOrder(order);

            return Response.ok(receipt).build();
        } catch (InvalidOrderException e) {
            LOGGER.error("order(): could not place order, Exception = ", e);

            return Response.status(Response.Status.BAD_REQUEST.getStatusCode(), e.getMessage()).build();
        }
    }
}
