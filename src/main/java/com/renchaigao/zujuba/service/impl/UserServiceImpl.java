package com.renchaigao.zujuba.service.impl;

import com.renchaigao.zujuba.dao.User;
import com.renchaigao.zujuba.dao.UserToken;
import com.renchaigao.zujuba.dao.mapper.UserMapper;
import com.renchaigao.zujuba.dao.mapper.UserTokenMapper;
import com.renchaigao.zujuba.domain.response.RespCode;
import com.renchaigao.zujuba.domain.response.ResponseEntity;
import com.renchaigao.zujuba.function.TokenMaker;
import com.renchaigao.zujuba.function.dateUse;
import com.renchaigao.zujuba.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.renchaigao.zujuba.function.dateUse.tokenDateCompare;

@Service
public class UserServiceImpl implements UserService {

    private static Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserTokenMapper userTokenMapper;

    @Override
    public ResponseEntity addUser(User userinfo, String inputMode) {
        String token;
        UserToken userToken = new UserToken();
        try {
            User user = userMapper.selectByTelephone(userinfo.getTelephone());
            if (user != null) {
//                  用户已存在，不用创建
//                  验证密码
                if (userinfo.getUserpwd().equals(user.getUserpwd())) {
//                  密码正确
                    userToken = userTokenMapper.selectByPrimaryKey(user.getId());
//                    判断是否是前端自动发送的信息，如果是就判断token的正误和寿命
                    if (inputMode.equals("auto")) {
                        if (tokenDateCompare(userToken.getCreatetime())) {
                            if (!userToken.getToken().equals(user.getToken())) {
                                return new ResponseEntity(RespCode.TOKENWRONG, userinfo);
                            }
                        } else
                            return new ResponseEntity(RespCode.TOKENOLD, userinfo);
                    }
//                    无论是前端自动发送的信息，还是 用户手动填写的账号密码，接下来都更新token
                    token = TokenMaker.EncoderByMd5(userinfo.getTelephone() + userinfo.getUserpwd());
                    user.setToken(token);
                    userToken.setToken(token);
                    userToken.setCreatetime(new Date());
                    try {
//                        更新user表中的token信息：token
                        userMapper.updateByPrimaryKey(user);
//                        更新token表中的token信息：时间、token
                        userTokenMapper.updateByPrimaryKey(userToken);
                        return new ResponseEntity(RespCode.UPDATEUSERINFOSUCCESS, user);
                    } catch (Exception e) {
                        return new ResponseEntity(RespCode.EXCEPTION, e);
                    }
                } else {
//                  密码错误
                    return new ResponseEntity(RespCode.WRONGPWD, userinfo);
                }
            } else {
//                  用户不存在，创建用户
                if (null != userinfo.getTelephone() && null != userinfo.getUserpwd()) {
                    token = TokenMaker.EncoderByMd5(userinfo.getTelephone() + userinfo.getUserpwd());
                    userinfo.setToken(token);
                    userMapper.insert(userinfo);
//                    创建用户对应的token信息
                    User u= userMapper.selectByTelephone(userinfo.getTelephone());
                    userToken = new UserToken();
                    userToken.setId(u.getId());
                    userToken.setUserid(u.getId());
                    userToken.setToken(u.getToken());
                    userToken.setCreatetime(new Date());
                    userTokenMapper.insert(userToken);
                    return new ResponseEntity(RespCode.SUCCESS, userinfo);
                } else {
                    return new ResponseEntity(RespCode.USERNOTEL, userinfo);
                }
            }
        } catch (Exception e) {
            logger.info("Exception e : " + e);
            return new ResponseEntity(RespCode.WARN, e);
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
