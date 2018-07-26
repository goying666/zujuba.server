package com.renchaigao.zujuba.controller;

import com.renchaigao.zujuba.dao.User;
import com.renchaigao.zujuba.domain.response.ResponseEntity;
import com.renchaigao.zujuba.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserServiceImpl userServiceImpl;

//    @PostMapping(value = "/login" , consumes = "application/json")
//    @ResponseBody
//    public ResponseEntity userWxLogin(@RequestBody UserLoin userLoin){
//        return  userServiceImpl.addUserLogin(userLoin);
//    }
    @PostMapping(value = "/add" , consumes = "application/json")
    @ResponseBody
    public ResponseEntity userAddInfo(@RequestBody User user){
        return userServiceImpl.addUser(user);
    }


}
