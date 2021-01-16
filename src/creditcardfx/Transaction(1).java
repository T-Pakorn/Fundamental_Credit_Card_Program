/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creditcardfx;

import java.time.LocalDate;

/**
 *
 * @author user
 */
public class Transaction {

    private CreditCard creditCard; //Tell who made the transaction (serial) + how much was left (balance)
    private String shopName, itemName; //Tell where the card was used + what was it used for
    private Double paidAmount; //Tell how much was paid
    private LocalDate transactionDate; //Tell when the transaction was made

    public Transaction(CreditCard creditCard, String shopName, String itemName, Double paidAmount, LocalDate transactionDate) {
        this.creditCard = creditCard;
        this.shopName = shopName;
        this.itemName = itemName;
        this.paidAmount = paidAmount;
        this.transactionDate = transactionDate;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

}
