package de.mkienitz.bachelorarbeit.order.application.cart;

import de.mkienitz.bachelorarbeit.order.domain.ShoppingCart;

import javax.ws.rs.*;

@Path("/data/cart")
public interface CartServiceClient {

    @GET
    @Path("/{shoppingCartId}")
    @Produces("application/json")
    ShoppingCart getShoppingCart(@PathParam("shoppingCartId") String shoppingCartId);
}
