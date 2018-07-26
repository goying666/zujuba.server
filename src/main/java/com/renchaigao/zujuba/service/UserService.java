package com.renchaigao.zujuba.service;

import com.renchaigao.zujuba.dao.User;
import com.renchaigao.zujuba.domain.response.ResponseEntity;

public interface UserService {

    ResponseEntity addUser(User user);
//    ResponseEntity addUserLogin(UserLoin userLoin);
}
