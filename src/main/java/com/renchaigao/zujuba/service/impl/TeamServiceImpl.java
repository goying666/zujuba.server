package com.renchaigao.zujuba.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.renchaigao.zujuba.dao.User;
import com.renchaigao.zujuba.dao.mapper.UserMapper;
import com.renchaigao.zujuba.domain.*;
import com.renchaigao.zujuba.dao.Team;
import com.renchaigao.zujuba.dao.mapper.TeamMapper;
import com.renchaigao.zujuba.domain.response.RespCode;
import com.renchaigao.zujuba.domain.response.ResponseEntity;
import com.renchaigao.zujuba.function.UUIDUtil;
import com.renchaigao.zujuba.service.TeamService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    private static Logger logger = Logger.getLogger(BusinessJoinImpl.class);

    @Autowired
    TeamMapper teamMapper;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    UserMapper userMapper;

    @Override
    public ResponseEntity createTeam(TeamInfo teamInfo) {
//        1、判断用户是否已存在相同的组局：否——继续，是——返回错误；
//        a)从MongoDB中拿关于用户的my信息
//        String filterInfo = teamInfo.getF
        String str = JSONObject.toJSONString(teamInfo);
        Integer userId = teamInfo.getCreaterId();
        List<Team> teamsList = teamMapper.selectByCreaterId(userId);
        if (teamsList != null) {
            for (Team i : teamsList) {
                if (i.getStartDate() == teamInfo.getStartDate()
                        && i.getStartTime() == teamInfo.getStartTime()) {
                    return new ResponseEntity(RespCode.TEAMHAD, JSONObject.toJSONString(i));
                }
            }
        }
        AddressInfo addressInfo = teamInfo.getAddressInfo();
        addressInfo.setId(UUIDUtil.getUUID());
        FilterInfo filterInfo = teamInfo.getFilterInfo();
        filterInfo.setId(UUIDUtil.getUUID());
        PlayerInfo playerInfo = new PlayerInfo();
        playerInfo.setId(UUIDUtil.getUUID());
        User creater = userMapper.selectByPrimaryKey(userId);
        ArrayList<Player> playersList = new ArrayList<>();
        playersList.add(JSONObject.parseObject(JSONObject.toJSONString(creater),Player.class));
        playerInfo.setPlayers(playersList);

        mongoTemplate.insert(addressInfo);
        mongoTemplate.insert(filterInfo);
        mongoTemplate.insert(playerInfo);

//        mongoTemplate.insert(teamInfo);
//        teamInfo.setId(999);
//        teamInfo = mongoTemplate.findById(114,TeamInfo.class);
//        if()
//        2、数据库新增team信息；
        teamInfo.setAddressId(addressInfo.getId());
        teamInfo.setScoreFilterID(filterInfo.getId());
        teamInfo.setPlayerinfoId(playerInfo.getId());
        teamInfo.setOwnerId(userId);
        teamInfo.setTeamstate("waiting");
        teamMapper.insert(teamInfo);
//        3、更新user相关team的信息；
//        4、更新redis中team的信息；
//        5、更新team对应的address的信息；
//        6、更新user在redis中的

        return new ResponseEntity(RespCode.SUCCESS, JSONObject.toJSONString(teamInfo));

//        return null;
    }

    @Override
    public ResponseEntity getTeamsByUserId(Integer userId, String getWay) {
        Criteria criteria = Criteria.where("id").ne(userId);
        List<TeamInfo> teamInfoList = mongoTemplate.find(Query.query(criteria), TeamInfo.class);
        return null;
//        return new ResponseEntity(RespCode.SUCCESS, JSONObject.toJSONString(teamInfo));
    }

    @Override
    public ResponseEntity updateTeamInfo(Integer userId, Integer teamId, TeamInfo teamInfo) {
        return null;
    }

    @Override
    public ResponseEntity deleteTeam(TeamInfo teamInfo) {
        return null;
    }
}
