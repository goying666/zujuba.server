package com.renchaigao.zujuba.dao.mapper;

import com.renchaigao.zujuba.dao.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    List<User> selectByTelephone(String telephone);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}