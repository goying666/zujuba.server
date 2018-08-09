package com.renchaigao.zujuba.controller;

import com.renchaigao.zujuba.domain.response.ResponseEntity;
import com.renchaigao.zujuba.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping(value = "/add/{mode}" , consumes = "application/json")
    @ResponseBody
    public ResponseEntity userAddInfo(@RequestBody User user,@PathVariable("mode") String inputMode){
        return userServiceImpl.addUser(user,inputMode);
    }


}
