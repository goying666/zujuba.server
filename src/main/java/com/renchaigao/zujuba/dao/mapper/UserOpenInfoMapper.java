package com.renchaigao.zujuba.dao.mapper;

import com.renchaigao.zujuba.dao.UserOpenInfo;

public interface UserOpenInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserOpenInfo record);

    int insertSelective(UserOpenInfo record);

    UserOpenInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserOpenInfo record);

    int updateByPrimaryKey(UserOpenInfo record);
}