package com.renchaigao.zujuba.service.impl;

import com.renchaigao.zujuba.dao.User;
import com.renchaigao.zujuba.dao.mapper.UserMapper;
import com.renchaigao.zujuba.domain.response.RespCode;
import com.renchaigao.zujuba.domain.response.ResponseEntity;
import com.renchaigao.zujuba.function.TokenMaker;
import com.renchaigao.zujuba.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private static Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    UserMapper userMapper;

    @Override
    public ResponseEntity addUser(User userinfo) {
        String token;
        try {
            User user = userMapper.selectByTelephone(userinfo.getTelephone());
            if (user != null) {
//                  用户已存在，不用创建
                if (userinfo.getUserpwd().equals(user.getUserpwd()))
                    return new ResponseEntity(RespCode.SUCCESS, user);
                else
                    return new ResponseEntity(RespCode.WRONGPWD, userinfo);
            } else {
//                  用户不存在，创建用户
                if (null != userinfo.getTelephone() && null != userinfo.getUserpwd()) {
                    token = TokenMaker.EncoderByMd5(userinfo.getTelephone() + userinfo.getUserpwd());
                    userinfo.setToken(token);
                    userMapper.insert(userinfo);
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
