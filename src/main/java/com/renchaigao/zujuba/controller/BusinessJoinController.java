//package com.renchaigao.zujuba.controller;
//
//import com.renchaigao.zujuba.domain.response.ResponseEntity;
//import com.renchaigao.zujuba.service.impl.BusinessJoinImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//@Controller
//@RequestMapping(value = "/store")
//public class BusinessJoinController {
//
//    @Autowired
//    BusinessJoinImpl businessJoinImpl;
//
//
//    @PostMapping(value = "/addstores",
//            consumes = "application/json")
//    @ResponseBody
//    public ResponseEntity addStores(@RequestBody Store store) {
//        return businessJoinImpl.addStores(store);
//    }
//
//
//    @PostMapping(value = "/join", consumes = "multipart/form-data")
//    @ResponseBody
//    public ResponseEntity addRecordingFile(@RequestParam("json") String store, @RequestParam("photo") MultipartFile[] photos) {
//        return businessJoinImpl.addStore(store, photos);
//    }
//
//
//    @GetMapping(value = "/get/storeinfo/{userid}", consumes = "application/json")
//    @ResponseBody
//    public ResponseEntity getStoreInfoByUserId(@PathVariable("userid") Integer userid) {
//        return businessJoinImpl.getStoreInfoByUserId(userid);
//    }
//
////    @PostMapping(value = "/addpic", consumes = "application/json")
////    @ResponseBody
////    public ResponseEntity addRecordingFile(@RequestBody Stores stores) {
////        return businessJoinImpl.addStores(stores);
////    }
//
//}
//
