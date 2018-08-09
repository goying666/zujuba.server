package com.renchaigao.zujuba.dao.mapper;

import com.renchaigao.zujuba.domain.FilterInfo;

public interface FilterInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FilterInfo record);

    int insertSelective(FilterInfo record);

    FilterInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FilterInfo record);

    int updateByPrimaryKey(FilterInfo record);
}