package com.gniot.crs.bean;

public class PaymentDetails {
    private String paymentMethod;  // Store the chosen payment method (Credit Card, Debit Card, Net Banking)
    private String cardNumber;     // Store card number (for Credit Card and Debit Card)
    private String cvv;            // Store CVV (for Credit Card and Debit Card)
    private String expiryDate;     // Store expiry date (for Credit Card and Debit Card)
    private String bankName;       // Store bank name (for Net Banking)

    // Getters and Setters

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
  
    //toString method
    @Override
    public String toString() {
        return "PaymentDetails{" +
                "paymentMethod='" + paymentMethod + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", cvv='" + cvv + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                ", bankName='" + bankName + '\'' +
                '}';
    }
}
