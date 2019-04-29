package com.fxj.bean.returnbean;

public class IndexTransaction implements Comparable<IndexTransaction>{

    private String txhash;

    private Long time;

    private String fees;

    private String price;

    public String getTxhash() {
        return txhash;
    }

    public void setTxhash(String txhash) {
        this.txhash = txhash;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "IndexTransaction{" +
                "txhash='" + txhash + '\'' +
                ", time=" + time +
                ", fees='" + fees + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

    @Override
    public int compareTo(IndexTransaction o) {
        return (int) (o.getTime()-this.time);
    }
}
