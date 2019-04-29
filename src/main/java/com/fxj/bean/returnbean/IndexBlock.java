package com.fxj.bean.returnbean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fxj.bean.Block;

import java.util.Date;

@JsonInclude
public class IndexBlock implements Comparable<IndexBlock>{
    @JsonProperty("height")
    private int height;
    @JsonProperty("time")
    private long time;
    @JsonProperty("txSize")
    private int nTx;
    @JsonProperty("merkleRoot")
    private String merkleroot;
    @JsonProperty("sizeOnDisk")
    private long nonce;

    public IndexBlock() {
    }

    @Override
    public String toString() {
        return "IndexBlock{" +
                "height=" + height +
                ", time=" + time +
                ", nTx=" + nTx +
                ", merkleroot='" + merkleroot + '\'' +
                ", nonce=" + nonce +
                '}';
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getnTx() {
        return nTx;
    }

    public void setnTx(int nTx) {
        this.nTx = nTx;
    }

    public String getMerkleroot() {
        return merkleroot;
    }

    public void setMerkleroot(String merkleroot) {
        this.merkleroot = merkleroot;
    }

    public long getNonce() {
        return nonce;
    }

    public void setNonce(long nonce) {
        this.nonce = nonce;
    }

    @Override
    public int compareTo(IndexBlock o) {
        return (int) (o.getTime()-this.time);
    }
}
