package com.renchaigao.zujuba.dao.mapper;

import com.renchaigao.zujuba.dao.Team;

public interface TeamMapper {
    int deleteByPrimaryKey(String id);

    int insert(Team record);

    int insertSelective(Team record);

    Team selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Team record);

    int updateByPrimaryKey(Team record);
}