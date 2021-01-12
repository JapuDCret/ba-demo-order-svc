package de.mkienitz.bachelorarbeit.order.domain;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.constraints.NotNull;

import java.util.List;

public class ShoppingCart {
    @NotNull
    @Schema(required = true)
    private List<ShoppingCartItem> items;

    public ShoppingCart() {
    }

    public ShoppingCart(List<ShoppingCartItem> items) {
        this.items = items;
    }

    public List<ShoppingCartItem> getItems() {
        return items;
    }

    public void setItems(List<ShoppingCartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "items=" + items +
                '}';
    }
}
