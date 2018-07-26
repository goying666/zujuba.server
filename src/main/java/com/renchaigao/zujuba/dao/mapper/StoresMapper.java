package com.renchaigao.zujuba.dao.mapper;

import com.renchaigao.zujuba.dao.Stores;

public interface StoresMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Stores record);

    int insertSelective(Stores record);

    Stores selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Stores record);

    int updateByPrimaryKey(Stores record);
}