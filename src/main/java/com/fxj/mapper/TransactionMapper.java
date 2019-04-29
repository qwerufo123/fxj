package com.fxj.mapper;

import com.fxj.bean.Transaction;

import java.util.Map;

public interface TransactionMapper {
    int deleteByPrimaryKey(String txid);

    int insert(Transaction record);

    int insertSelective(Map record);

    Transaction selectByPrimaryKey(String txid);

    int updateByPrimaryKeySelective(Transaction record);

    int updateByPrimaryKey(Transaction record);

    void truncate();
}