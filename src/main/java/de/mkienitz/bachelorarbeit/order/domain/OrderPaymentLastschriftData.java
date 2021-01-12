package de.mkienitz.bachelorarbeit.order.domain;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.constraints.NotNull;

public class OrderPaymentLastschriftData {
    @NotNull
    @Schema(required = true, example = "Max Mustermann")
    private String inhaber;

    @NotNull
    @Schema(required = true, example = "DE89 3704 0044 0532 0130 00")
    private String iban;

    public OrderPaymentLastschriftData() {
    }

    public OrderPaymentLastschriftData(String inhaber, String iban) {
        this.inhaber = inhaber;
        this.iban = iban;
    }

    public String getInhaber() {
        return inhaber;
    }

    public void setInhaber(String inhaber) {
        this.inhaber = inhaber;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    @Override
    public String toString() {
        return "OrderPaymentLastschriftData{" +
                "inhaber='" + inhaber + '\'' +
                ", iban='" + iban + '\'' +
                '}';
    }
}
