package com.renchaigao.zujuba.controller;

import com.renchaigao.zujuba.dao.User;
import com.renchaigao.zujuba.domain.response.RespCode;
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
    @PostMapping(value = "/login/{mode}/{code}" , consumes = "application/json")
    @ResponseBody
    public ResponseEntity userAddInfo(
                                        @RequestBody User user,
                                        @PathVariable("mode") String inputMode,
                                        @PathVariable("code") String verCode){
        if(inputMode.equals("auto")){
            return userServiceImpl.autoLoginUser(user);
        }
        else if(inputMode.equals("secret")){
            return userServiceImpl.secretLoginUser(user);
        }
        else if(inputMode.equals("vercode")){
            return userServiceImpl.vercodeLoginUser(user,verCode);
        }
        else if(inputMode.equals("add")){
            return userServiceImpl.addUser(user,verCode);
        }
        else {
            return new ResponseEntity(RespCode.WRONGIP,null);
        }
    }


}
