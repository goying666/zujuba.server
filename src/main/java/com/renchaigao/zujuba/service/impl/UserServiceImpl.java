package com.renchaigao.zujuba.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.renchaigao.zujuba.dao.User;
import com.renchaigao.zujuba.dao.UserLogin;
import com.renchaigao.zujuba.dao.mapper.UserLoginMapper;
import com.renchaigao.zujuba.dao.mapper.UserMapper;
import com.renchaigao.zujuba.domain.info.user.UserInfo;
import com.renchaigao.zujuba.domain.response.RespCode;
import com.renchaigao.zujuba.domain.response.ResponseEntity;
import com.renchaigao.zujuba.function.TokenMaker;
import com.renchaigao.zujuba.function.UUIDUtil;
import com.renchaigao.zujuba.function.dateUse;
import com.renchaigao.zujuba.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    private static Logger logger = Logger.getLogger(UserServiceImpl.class);


    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserLoginMapper userLoginMapper;

    @Override
//    自动登陆
    public ResponseEntity autoLoginUser(User userApp) {
        String telephone = userApp.getTelephone();
        User userSql = userMapper.selectByTelephone(telephone);
//      验证密码
        if (userSql.getUserPWD().equals(userApp.getUserPWD())) {
//          判断token的正误
            if (!userSql.getToken().equals(userApp.getToken())) {
                return new ResponseEntity(RespCode.TOKENWRONG, userApp);
            }
            return checkUserInfoFUNC(telephone, userSql);
        } else {
            return new ResponseEntity(RespCode.WRONGPWD, userApp);//                  密码错误
        }
    }

    @Override
//    密码登陆
    public ResponseEntity secretLoginUser(User userApp) {
        String telephone = userApp.getTelephone();
        User userSql = userMapper.selectByTelephone(telephone);
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
        User userSql = userMapper.selectByTelephone(telephone);
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

                if (userMapper.insert(userRet) == 1) {


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


//    @Override
//    public ResponseEntity addUserLogin(UserLogin userLoin) {
//        try {
//            User user = userMapper.selectByTelephone(userLoin.getTelephone());
//            if (user != null) {
////                  用户已存在，不用创建
//                return new ResponseEntity(RespCode.USERHAD, user);
//            } else {
////                  用户不存在，创建用户
//                if (null != userLoin.getTelephone()) {
//                    userMapper.insert(userLoin);
//                    return new ResponseEntity(RespCode.SUCCESS, userinfo);
//                } else {
//                    return new ResponseEntity(RespCode.USERNOTEL, userinfo);
//                }
//            }
//        } catch (Exception e) {
//            logger.info("Exception e : " + e);
//            return new ResponseEntity(RespCode.WARN, e);
//        }
//    }
}
