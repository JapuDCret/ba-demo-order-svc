package de.mkienitz.bachelorarbeit.order.application;

import de.mkienitz.bachelorarbeit.order.domain.ShoppingCart;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;

@RegisterRestClient
@Path("/data/cart")
public interface CartServiceClient {

    @GET
    @Path("/{shoppingCartId}")
    @Produces("application/json")
    ShoppingCart getShoppingCart(@PathParam("shoppingCartId") String shoppingCartId);
}
