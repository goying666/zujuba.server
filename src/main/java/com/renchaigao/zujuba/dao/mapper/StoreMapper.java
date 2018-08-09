package com.renchaigao.zujuba.dao.mapper;

import com.renchaigao.zujuba.dao.Store;

import java.util.List;

public interface StoreMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Store record);

    int insertSelective(Store record);

    Store selectByPrimaryKey(Integer id);

    List<Store> selectStoreByCityCode(String cityCode);

    int updateByPrimaryKeySelective(Store record);

    int updateByPrimaryKey(Store record);
}