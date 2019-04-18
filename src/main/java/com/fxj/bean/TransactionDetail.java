package com.fxj.bean;

public class TransactionDetail extends TransactionDetailKey {
    private Byte type;

    private Double amount;

    public TransactionDetail(String txid, String address, Byte type, Double amount) {
        super(txid, address);
        this.type = type;
        this.amount = amount;
    }

    public TransactionDetail() {
        super();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}