package com.renchaigao.zujuba.dao.mapper;

import com.renchaigao.zujuba.dao.Team;

import java.util.List;

public interface TeamMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Team record);

    int insertSelective(Team record);

    Team selectByPrimaryKey(Integer id);

    List<Team> selectByCreaterId(Integer userId);

    List<Team> selectAllTeam();
    int updateByPrimaryKeySelective(Team record);

    int updateByPrimaryKey(Team record);
}