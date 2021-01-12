package de.mkienitz.bachelorarbeit.order.domain;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.constraints.NotNull;

public class Receipt {
    @NotNull
    @Schema(required = true)
    private Integer orderId;

    @NotNull
    @Schema(required = true)
    private OrderShippingData shippingData;

    @NotNull
    @Schema(required = true)
    private String paymentType;

    @NotNull
    @Schema(required = true)
    private Integer itemCount;

    @NotNull
    @Schema(required = true)
    private Double total;

    public Receipt() {
    }

    public Receipt(Integer orderId, OrderShippingData shippingData, String paymentType, Integer itemCount, Double total) {
        this.orderId = orderId;
        this.shippingData = shippingData;
        this.paymentType = paymentType;
        this.itemCount = itemCount;
        this.total = total;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public OrderShippingData getShippingData() {
        return shippingData;
    }

    public void setShippingData(OrderShippingData shippingData) {
        this.shippingData = shippingData;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "orderId=" + orderId +
                ", shippingData=" + shippingData +
                ", paymentType='" + paymentType + '\'' +
                ", itemCount=" + itemCount +
                ", total=" + total +
                '}';
    }
}
