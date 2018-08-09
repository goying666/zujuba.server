package com.renchaigao.zujuba.dao.mapper;

import com.renchaigao.zujuba.dao.StoreRank;

public interface StoreRankMapper {
    int deleteByPrimaryKey(String id);

    int insert(StoreRank record);

    int insertSelective(StoreRank record);

    StoreRank selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(StoreRank record);

    int updateByPrimaryKey(StoreRank record);
}