package com.fxj.bean;

public class Blockchain {
    private Integer blockchainId;

    private String name;

    private String type;

    private String shortname;

    public Blockchain(Integer blockchainId, String name, String type, String shortname) {
        this.blockchainId = blockchainId;
        this.name = name;
        this.type = type;
        this.shortname = shortname;
    }

    public Blockchain() {
        super();
    }

    public Integer getBlockchainId() {
        return blockchainId;
    }

    public void setBlockchainId(Integer blockchainId) {
        this.blockchainId = blockchainId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname == null ? null : shortname.trim();
    }
}