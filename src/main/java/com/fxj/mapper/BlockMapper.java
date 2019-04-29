package com.fxj.mapper;

import com.alibaba.fastjson.JSONObject;
import com.fxj.bean.Block;

public interface BlockMapper {
    int deleteByPrimaryKey(String blockhash);

    int insert(Block record);

    int insertSelective(JSONObject record);

    Block selectByPrimaryKey(String blockhash);

    int updateByPrimaryKeySelective(Block record);

    int updateByPrimaryKey(Block record);

    void truncate();
}