package com.fxj.bean;

public class TransactionDetailKey {
    private String txid;

    private String address;

    public TransactionDetailKey(String txid, String address) {
        this.txid = txid;
        this.address = address;
    }

    public TransactionDetailKey() {
        super();
    }

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid == null ? null : txid.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }
}