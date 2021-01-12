package de.mkienitz.bachelorarbeit.order.application;

import de.mkienitz.bachelorarbeit.order.domain.ShoppingCart;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;

@RegisterRestClient
@Path("/data/cart")
@Produces("application/json")
@Consumes("application/json")
public interface CartServiceClient {

    @GET
    @Path("/{shoppingCartId}")
    ShoppingCart getShoppingCart(@PathParam("shoppingCartId") String shoppingCartId);
}
