package com.renchaigao.zujuba.service;

import com.renchaigao.zujuba.dao.User;
import com.renchaigao.zujuba.domain.response.ResponseEntity;

public interface UserService {

    ResponseEntity autoLoginUser(User user);
    ResponseEntity secretLoginUser(User user);
    ResponseEntity vercodeLoginUser(User user,String verCode);
    ResponseEntity addUser(User user,String verCode);
//    ResponseEntity addUserLogin(UserLoin userLoin);
}
