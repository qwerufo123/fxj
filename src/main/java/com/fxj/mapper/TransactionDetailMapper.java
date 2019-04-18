package com.fxj.mapper;

import com.fxj.bean.TransactionDetail;
import com.fxj.bean.TransactionDetailKey;

public interface TransactionDetailMapper {
    int deleteByPrimaryKey(TransactionDetailKey key);

    int insert(TransactionDetail record);

    int insertSelective(TransactionDetail record);

    TransactionDetail selectByPrimaryKey(TransactionDetailKey key);

    int updateByPrimaryKeySelective(TransactionDetail record);

    int updateByPrimaryKey(TransactionDetail record);
}