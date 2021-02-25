package de.mkienitz.bachelorarbeit.order.domain;

import de.mkienitz.bachelorarbeit.order.application.InvalidOrderException;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import java.util.List;

public class Order {
    @NotNull
    @Schema(required = true, example = "12345678")
    private String shoppingCartId;

    @NotNull
    @Schema(required = true)
    private OrderBillingAddress billingAddress;

    @NotNull
    @Schema(required = true)
    private OrderShippingData shippingData;

    @NotNull
    @Schema(required = true)
    private OrderPaymentData paymentData;

    public Order() {
    }

    public Order(String shoppingCartId, OrderBillingAddress billingAddress, OrderShippingData shippingData, OrderPaymentData paymentData) {
        this.shoppingCartId = shoppingCartId;
        this.billingAddress = billingAddress;
        this.shippingData = shippingData;
        this.paymentData = paymentData;
    }

    public String getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(String shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public OrderBillingAddress getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(OrderBillingAddress billingAddress) {
        this.billingAddress = billingAddress;
    }

    public OrderShippingData getShippingData() {
        return shippingData;
    }

    public void setShippingData(OrderShippingData shippingData) {
        this.shippingData = shippingData;
    }

    public OrderPaymentData getPaymentData() {
        return paymentData;
    }

    public void setPaymentData(OrderPaymentData paymentData) {
        this.paymentData = paymentData;
    }

    public String getPaymentType() throws InvalidOrderException {
        // only one paymentType should not be null

        if(paymentData.getRechnungData() != null) {
            return "Rechnung";
        }
        if(paymentData.getLastschriftData() != null) {
            return "Lastschrift";
        }
        if(paymentData.getPaypalData() != null) {
            return "PayPal";
        }
        if(paymentData.getKreditkartenData() != null) {
            return "Kreditkarte";
        }

        throw new InvalidOrderException("Es wurde keine Zahlungsart ausgewählt.");
    }

    @Override
    public String toString() {
        return "Order{" +
                "shoppingCartId=" + shoppingCartId +
                ", billingAddress=" + billingAddress +
                ", shippingData=" + shippingData +
                ", paymentData=" + paymentData +
                '}';
    }
}
