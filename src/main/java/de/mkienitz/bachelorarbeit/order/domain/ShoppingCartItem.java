package de.mkienitz.bachelorarbeit.order.domain;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.constraints.NotNull;

public class ShoppingCartItem {
    @NotNull
    @Schema(required = true, example = "3")
    private String id;

    @NotNull
    @Schema(required = true, example = "3")
    protected String translationKey;

    @NotNull
    @Schema(required = true, example = "3")
    protected Double price;

    @NotNull
    @Schema(required = true, example = "2")
    private Integer amount;

    public ShoppingCartItem() {
    }

    public ShoppingCartItem(String id, String translationKey, Double price, Integer amount) {
        this.id = id;
        this.translationKey = translationKey;
        this.price = price;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTranslationKey() {
        return translationKey;
    }

    public void setTranslationKey(String translationKey) {
        this.translationKey = translationKey;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "ShoppingCartItem{" +
                "id='" + id + '\'' +
                ", translationKey='" + translationKey + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }
}
