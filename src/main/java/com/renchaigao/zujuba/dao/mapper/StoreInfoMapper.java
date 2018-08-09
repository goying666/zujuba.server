package com.renchaigao.zujuba.dao.mapper;

import com.renchaigao.zujuba.domain.StoreInfo;

public interface StoreInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StoreInfo record);

    int insertSelective(StoreInfo record);

    StoreInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StoreInfo record);

    int updateByPrimaryKey(StoreInfo record);
}