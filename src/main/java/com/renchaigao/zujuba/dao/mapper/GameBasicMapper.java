package com.renchaigao.zujuba.dao.mapper;

import com.renchaigao.zujuba.dao.GameBasic;

public interface GameBasicMapper {
    int deleteByPrimaryKey(String id);

    int insert(GameBasic record);

    int insertSelective(GameBasic record);

    GameBasic selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GameBasic record);

    int updateByPrimaryKey(GameBasic record);
}