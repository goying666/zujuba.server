package com.renchaigao.zujuba.dao.mapper;

import com.renchaigao.zujuba.dao.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    User selectByTelephone(String telephone);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}