package com.renchaigao.zujuba.dao.mapper;

import com.renchaigao.zujuba.dao.Player;

public interface PlayerMapper {
    int deleteByPrimaryKey(String id);

    int insert(Player record);

    int insertSelective(Player record);

    Player selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Player record);

    int updateByPrimaryKey(Player record);
}