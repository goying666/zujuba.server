package com.renchaigao.zujuba.dao.mapper;

import com.renchaigao.zujuba.dao.Store;

public interface StoreMapper {
    int deleteByPrimaryKey(String id);

    int insert(Store record);

    int insertSelective(Store record);

    Store selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Store record);

    int updateByPrimaryKey(Store record);
}