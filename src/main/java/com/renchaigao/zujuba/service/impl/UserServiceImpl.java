package com.renchaigao.zujuba.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.renchaigao.zujuba.dao.User;
import com.renchaigao.zujuba.dao.UserLogin;
import com.renchaigao.zujuba.dao.UserRank;
import com.renchaigao.zujuba.dao.mapper.FriendMapper;
import com.renchaigao.zujuba.dao.mapper.UserLoginMapper;
import com.renchaigao.zujuba.dao.mapper.UserMapper;
import com.renchaigao.zujuba.dao.mapper.UserRankMapper;
import com.renchaigao.zujuba.domain.info.*;
import com.renchaigao.zujuba.domain.info.user.UserInfo;
import com.renchaigao.zujuba.domain.info.user.myPlayGamesInfo;
import com.renchaigao.zujuba.domain.info.user.myStoresInfo;
import com.renchaigao.zujuba.domain.info.user.myTeamsInfo;
import com.renchaigao.zujuba.domain.response.RespCode;
import com.renchaigao.zujuba.domain.response.ResponseEntity;
import com.renchaigao.zujuba.function.TokenMaker;
import com.renchaigao.zujuba.function.UUIDUtil;
import com.renchaigao.zujuba.function.dateUse;
import com.renchaigao.zujuba.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static Logger logger = Logger.getLogger(UserServiceImpl.class);


    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserLoginMapper userLoginMapper;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    UserRankMapper userRankMapper;

    @Autowired
    FriendMapper friendMapper;

    @Override
//    自动登陆
    public ResponseEntity autoLoginUser(User userApp) {
        String telephone = userApp.getTelephone();
        User userSql = userMapper.selectByTelephone(telephone).get(0);
//          判断token的正误
        if (!dateUse.tokenDateCompare(dateUse.StringToDate(userSql.getUpTime()))){
            return new ResponseEntity(RespCode.TOKENOLD, userApp);
        }
        if (!userSql.getToken().equals(userApp.getToken())) {
            return new ResponseEntity(RespCode.TOKENWRONG, userApp);
        }
        return checkUserInfoFUNC(telephone, userSql);

    }

    @Override
//    密码登陆
    public ResponseEntity secretLoginUser(User userApp) {
        String telephone = userApp.getTelephone();
        List<User> userArrayList = userMapper.selectByTelephone(telephone);
        if (userArrayList.size()==0){
            return new ResponseEntity(RespCode.TELEPHONENOJOIN, userApp);
        }
        User userSql = userMapper.selectByTelephone(telephone).get(0);
        if (userSql.getUserPWD().equals("0")) {
            return new ResponseEntity(RespCode.PASSWORDMISSING, userApp);
        }
        if (userSql.getUserPWD().equals(userApp.getUserPWD())) {
            return checkUserInfoFUNC(telephone, userSql);
        } else {
            return new ResponseEntity(RespCode.WRONGPWD, userApp);
        }
    }

    @Override
//    验证码登陆
    public ResponseEntity vercodeLoginUser(User userApp, String verCode) {
        String telephone = userApp.getTelephone();
        User userSql = userMapper.selectByTelephone(telephone).get(0);
        if (checkVercode(verCode)) {
            return checkUserInfoFUNC(telephone, userSql);
        } else {
            return new ResponseEntity(RespCode.WRONGPWD, userApp);
        }
    }

    @Override
    //系统添加新用户信息
    public ResponseEntity addUser(User userApp, String verCode) {
        String telephone = userApp.getTelephone();
        String uniqueId = userApp.getUniqueId();
        User userRet = new User();
        String token;
        if(userMapper.selectByTelephone(telephone).size()!=0)
            return new ResponseEntity(RespCode.USERHAD, userApp);
        try {
            if (null != userApp.getTelephone()) {
//          验证码判断部分

//          验证码判断部分
                try {
                    token = TokenMaker.EncoderByMd5(telephone);
                } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
                    e.printStackTrace();
                    return new ResponseEntity(RespCode.TOKENEXCEPTION, e);
                }
                /*  为user 配置相应的属性； */
                //配置id属性;
                userRet.setId(UUIDUtil.getUUID());
                //配置age属性;
                userRet.setAge("0");
                //配置ageLevel属性;
                userRet.setAgeLevel("0");
                //配置realName属性;
                userRet.setRealName("0");
                //配置nickName属性;
                userRet.setNickName("0");
                //配置idCard属性;
                userRet.setIdCard("0");
                //配置gender属性;
                userRet.setGender("0");
                //配置job属性;
                userRet.setJob("0");
                //配置telephone属性;
                userRet.setTelephone(telephone);
                //配置marriage属性;
                userRet.setMarriage("0");
                //配置userPWD属性;
                userRet.setUserPWD("0");
                //配置picPath属性;
                userRet.setPicPath("0");
                //配置deleteStyle属性;
                userRet.setDeleteStyle(false);
                //配置myOpenInfoId属性;
                userRet.setMyOpenInfoId(UUIDUtil.getUUID());
                //配置userInfoId属性;
                userRet.setUserInfoId(UUIDUtil.getUUID());
                //配置uniqueId属性;
                userRet.setUniqueId(uniqueId);
                //配置myTeamsId属性;
                userRet.setMyTeamsId(UUIDUtil.getUUID());
                //配置myGamesId属性;
                userRet.setMyGamesId(UUIDUtil.getUUID());
                //配置myStoresId属性;
                userRet.setMyStoresId(UUIDUtil.getUUID());
                //配置 photoInfoId属性;
                userRet.setPhotoInfoId(UUIDUtil.getUUID());
                //配置 myAddressId属性;
                userRet.setMyAddressId(UUIDUtil.getUUID());
                //配置 myRankInfoId属性;
                userRet.setMyRankInfoId(UUIDUtil.getUUID());
                //配置 mySpendInfoId属性;
                userRet.setMySpendInfoId(UUIDUtil.getUUID());
                //配置 myMessageInfoId属性;
                userRet.setMyMessageInfoId(UUIDUtil.getUUID());
                //配置 myFriendInfoId属性;
                userRet.setMyFriendInfoId(UUIDUtil.getUUID());
                //配置 myIntegrationInfoId属性;
                userRet.setMyIntegrationInfoId(UUIDUtil.getUUID());
                //配置 peopleListId属性;
                userRet.setPeopleListId(UUIDUtil.getUUID());
                //配置 myPermissionInfoId属性;
                userRet.setMyPermissionInfoId(UUIDUtil.getUUID());
                //配置upTime属性;
                userRet.setUpTime(dateUse.DateToString(new Date()));
                //配置token属性;
                userRet.setToken(token);

                if (userMapper.insert(userRet) == 1) {//sql 添加user数据成功；

                    redisTemplate.opsForValue().set(userRet.getId(),
                            JSONObject.toJSONString(userRet));
                    return new ResponseEntity(RespCode.SUCCESS, userRet);
                } else {
                    return new ResponseEntity(RespCode.USERADDFAIL, userRet);
                }
            } else {
                return new ResponseEntity(RespCode.USERNOTEL, userApp);
            }
        } catch (Exception e) {
            return new ResponseEntity(RespCode.EXCEPTION, e);
        }
    }

    @Override
    public ResponseEntity getUserInfo(User userApp) {
        UserInfo userInfo = mongoTemplate.findById(userApp.getId(), UserInfo.class);
        if (userInfo == null)//没有userInfo，创建一个在MongoDB里。
        {
            userInfo = new UserInfo();
//            先将user内的信息付给userInfo
            userInfo = JSONObject.parseObject(JSONObject.toJSONString(userApp),UserInfo.class);
            /*  创建各个object */
//          创建myRankInfo
            UserRank userRank = new UserRank();
            userRank.setId(userApp.getMyRankInfoId());
            userRank.setDeleteStyle(false);
            userRank.setUpTime(dateUse.DateToString(new Date()));
            userRank.setIntegral(0);
            userRank.setCityIntegralRank(0);
            userRank.setCountryIntegralRank(0);
            userRank.setProvinceIntegralRank(0);
            userRank.setUserId(userApp.getId());
            userRankMapper.updateByPrimaryKeySelective(userRank);
            userInfo.setMyRankInfo(userRank);
            //          创建myTeamsInfo
            myTeamsInfo myTeamsInfo = new myTeamsInfo();
            myTeamsInfo.setId(userApp.getMyTeamsId());
            mongoTemplate.save(myTeamsInfo);
            userInfo.setMyTeamsInfo(myTeamsInfo);
//          创建myGamesInfo
            myPlayGamesInfo myPlayGamesInfo = new myPlayGamesInfo();
            myPlayGamesInfo.setId(userApp.getMyGamesId());
            mongoTemplate.save(myPlayGamesInfo);
            userInfo.setMyPlayGamesInfo(myPlayGamesInfo);
//          创建myStoresInfo
            myStoresInfo myStoresInfo = new myStoresInfo();
            myStoresInfo.setId(userApp.getMyStoresId());
            mongoTemplate.save(myStoresInfo);
            userInfo.setMyStoresInfo(myStoresInfo);
//          创建myPhotoInfo
            photo photo = new photo();
            photo.setId(userApp.getPhotoInfoId());
            mongoTemplate.save(photo);
            userInfo.setMyPhotoInfo(photo);
//          创建myAddress
            addressInfo addressInfo = new addressInfo();
            addressInfo.setId(userApp.getMyAddressId());
            mongoTemplate.save(addressInfo);
            userInfo.setMyAddressInfo(addressInfo);
//          创建mySpendInfo
            userSpendInfo userSpendInfo = new userSpendInfo();
            userSpendInfo.setId(userApp.getMySpendInfoId());
            mongoTemplate.save(userSpendInfo);
            userInfo.setMySpendInfo(userSpendInfo);
//          创建myMessageInfo

//          创建myFriendInfo
            userFriendInfo userFriendInfo = new userFriendInfo();
            userFriendInfo.setId(userApp.getMyFriendInfoId());
            mongoTemplate.save(userFriendInfo);
            userInfo.setMyFreiendInfo(userFriendInfo);
//          创建myIntegrationInfo

//          创建peopleList

//          创建myPermissionInfo
            userPermissionInfo userPermissionInfo = new userPermissionInfo();
            userPermissionInfo.setId(userApp.getMyPermissionInfoId());
            mongoTemplate.save(userPermissionInfo);
            userInfo.setMyPermissionInfo(userPermissionInfo);

            mongoTemplate.save(userInfo);
            return new ResponseEntity(RespCode.USERINFOADD, userInfo);
        } else {

            userInfo = JSONObject.parseObject(JSONObject.toJSONString(userApp),UserInfo.class);
//          userInfo已存在，更新后发送给前端
            userInfo.setMyTeamsInfo(mongoTemplate.findById(userApp.getMyTeamsId(), myTeamsInfo.class));
            userInfo.setMyPlayGamesInfo(mongoTemplate.findById(userApp.getMyGamesId(), myPlayGamesInfo.class));
            userInfo.setMyStoresInfo(mongoTemplate.findById(userApp.getMyStoresId(), myStoresInfo.class));
            userInfo.setMyPhotoInfo(mongoTemplate.findById(userApp.getPhotoInfoId(), photo.class));
            userInfo.setMyAddressInfo(mongoTemplate.findById(userApp.getMyAddressId(), addressInfo.class));
            userInfo.setMySpendInfo(mongoTemplate.findById(userApp.getMySpendInfoId(), userSpendInfo.class));
            userInfo.setMyRankInfo(mongoTemplate.findById(userApp.getMyRankInfoId(), UserRank.class));
//          创建myMessageInfo
            userInfo.setMyFreiendInfo(mongoTemplate.findById(userApp.getMyFriendInfoId(), userFriendInfo.class));
//          创建myIntegrationInfo
//          创建peopleList
            userInfo.setMyPermissionInfo(mongoTemplate.findById(userApp.getMyPermissionInfoId(), userPermissionInfo.class));
//          更新MongoDB内的userInfo
            mongoTemplate.save(userInfo);
//            Criteria criteria = Criteria.where("id").ne(userApp.getId());
//            Update update = Update.update("userInfo", userInfo);
//            mongoTemplate.updateFirst(Query.query(criteria), update, UserInfo.class);
//            logger.error(JSONObject.toJSONString(userInfo));
//        List<TeamInfo> teamInfoList = mongoTemplate.find(Query.query(criteria), TeamInfo.class);
            return new ResponseEntity(RespCode.SUCCESS, userInfo);
        }
    }

    private boolean checkVercode(String verCode) {
        return true;
    }

    private ResponseEntity checkUserInfoFUNC(String telephone, User userSql) {
        String token;
        UserLogin userLogin = new UserLogin();
        String nowTime = dateUse.DateToString(new Date());
        try {
            token = TokenMaker.EncoderByMd5(telephone);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return new ResponseEntity(RespCode.TOKENEXCEPTION, e);
        }
        userSql.setToken(token);//通过判断后，更新token
        userSql.setUpTime(nowTime);//更新时间
        userLogin.setDeleteStyle(false);//通过判断后，新增login信息
        userLogin.setId(UUIDUtil.getUUID());
        userLogin.setLoginTime(nowTime);
        userLogin.setUserId(userSql.getId());
        try {
            userLoginMapper.insert(userLogin);
            userMapper.updateByPrimaryKey(userSql);//更新user信息；
            return new ResponseEntity(RespCode.SUCCESS, userSql);
        } catch (Exception e) {
            return new ResponseEntity(RespCode.EXCEPTION, e);
        } finally {
//            if (redisTemplate.opsForValue().get(userSql.getId()) == null)
//                redisTemplate.opsForValue().set(userSql.getId(),
//                        JSONObject.toJSONString(userSql));
//            else {
//                redisTemplate.opsForValue().
//            }
        }
    }

}
