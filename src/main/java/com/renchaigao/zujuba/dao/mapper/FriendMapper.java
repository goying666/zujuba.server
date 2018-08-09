package com.renchaigao.zujuba.dao.mapper;

import com.renchaigao.zujuba.dao.Friend;

public interface FriendMapper {
    int deleteByPrimaryKey(String id);

    int insert(Friend record);

    int insertSelective(Friend record);

    Friend selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Friend record);

    int updateByPrimaryKey(Friend record);
}