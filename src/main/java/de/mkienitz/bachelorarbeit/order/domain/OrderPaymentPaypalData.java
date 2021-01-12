package de.mkienitz.bachelorarbeit.order.domain;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.constraints.NotNull;

public class OrderPaymentPaypalData {
    @NotNull
    @Schema(required = true, example = "max.mustermann@example.com")
    private String email;

    public OrderPaymentPaypalData() {
    }

    public OrderPaymentPaypalData(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "OrderPaymentPaypalData{" +
                "email='" + email + '\'' +
                '}';
    }
}
