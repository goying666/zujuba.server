package com.renchaigao.zujuba.dao.mapper;

import com.renchaigao.zujuba.dao.UserRank;

public interface UserRankMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserRank record);

    int insertSelective(UserRank record);

    UserRank selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserRank record);

    int updateByPrimaryKey(UserRank record);
}