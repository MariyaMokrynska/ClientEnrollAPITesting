package com.clients.pojos;

public class PaymentIntentRequest {

    private int customerId;
    private int productId;
    private String priceId;
    private String paymentMethod;

    public PaymentIntentRequest() {
    }

    public PaymentIntentRequest(int customerId, int productId, String priceId, String paymentMethod) {
        this.customerId = customerId;
        this.productId = productId;
        this.priceId = priceId;
        this.paymentMethod = paymentMethod;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getPriceId() {
        return priceId;
    }

    public void setPriceId(String priceId) {
        this.priceId = priceId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
