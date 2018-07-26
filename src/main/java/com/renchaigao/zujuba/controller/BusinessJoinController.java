package com.renchaigao.zujuba.controller;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.renchaigao.zujuba.dao.Stores;
import com.renchaigao.zujuba.domain.response.ResponseEntity;
import com.renchaigao.zujuba.service.impl.BusinessJoinImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/join")
public class BusinessJoinController {

    @Autowired
    BusinessJoinImpl businessJoinImpl;

    @PostMapping(value = "/addstores",
            consumes = "application/json")
    @ResponseBody
    public ResponseEntity addStores(@RequestBody Stores stores) {
        return businessJoinImpl.addStores(stores);
    }

    @PostMapping(value = "/addpic", consumes = "multipart/form-data")
    @ResponseBody
    public ResponseEntity addRecordingFile(@RequestParam("file") MultipartFile file,
                                           @RequestParam("photo2") MultipartFile photo2,
                                           @RequestParam("json") String stores) {

        System.out.println("json is : " + stores);
        businessJoinImpl.addStores(JSON.parseObject(stores,Stores.class));
        businessJoinImpl.addPic(photo2);
        return businessJoinImpl.addPic(file);
    }

//    @PostMapping(value = "/addpic", consumes = "application/json")
//    @ResponseBody
//    public ResponseEntity addRecordingFile(@RequestBody Stores stores) {
//        return businessJoinImpl.addStores(stores);
//    }

}

