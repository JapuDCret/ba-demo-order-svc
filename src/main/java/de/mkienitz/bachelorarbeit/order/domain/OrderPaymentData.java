package de.mkienitz.bachelorarbeit.order.domain;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class OrderPaymentData {
    @Schema(required = true)
    private OrderPaymentRechnungData rechnungData;

    @Schema(required = true)
    private OrderPaymentLastschriftData lastschriftData;

    @Schema(required = true)
    private OrderPaymentPaypalData paypalData;

    @Schema(required = true)
    private OrderPaymentKreditkartenData kreditkartenData;

    public OrderPaymentData() {
    }

    public OrderPaymentData(OrderPaymentRechnungData rechnungData, OrderPaymentLastschriftData lastschriftData, OrderPaymentPaypalData paypalData, OrderPaymentKreditkartenData kreditkartenData) {
        this.rechnungData = rechnungData;
        this.lastschriftData = lastschriftData;
        this.paypalData = paypalData;
        this.kreditkartenData = kreditkartenData;
    }

    public OrderPaymentRechnungData getRechnungData() {
        return rechnungData;
    }

    public void setRechnungData(OrderPaymentRechnungData rechnungData) {
        this.rechnungData = rechnungData;
    }

    public OrderPaymentLastschriftData getLastschriftData() {
        return lastschriftData;
    }

    public void setLastschriftData(OrderPaymentLastschriftData lastschriftData) {
        this.lastschriftData = lastschriftData;
    }

    public OrderPaymentPaypalData getPaypalData() {
        return paypalData;
    }

    public void setPaypalData(OrderPaymentPaypalData paypalData) {
        this.paypalData = paypalData;
    }

    public OrderPaymentKreditkartenData getKreditkartenData() {
        return kreditkartenData;
    }

    public void setKreditkartenData(OrderPaymentKreditkartenData kreditkartenData) {
        this.kreditkartenData = kreditkartenData;
    }

    @Override
    public String toString() {
        return "OrderPaymentData{" +
                "rechnungData=" + rechnungData +
                ", lastschriftData=" + lastschriftData +
                ", paypalData=" + paypalData +
                ", kreditkartenData=" + kreditkartenData +
                '}';
    }
}
