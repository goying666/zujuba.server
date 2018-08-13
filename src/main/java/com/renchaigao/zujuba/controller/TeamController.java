//package com.renchaigao.zujuba.controller;
//
//import com.renchaigao.zujuba.domain.response.ResponseEntity;
//import com.renchaigao.zujuba.service.impl.TeamServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequestMapping(value = "/team")
//public class TeamController {
//    @Autowired
//    TeamServiceImpl teamServiceImpl;
//
//    @PostMapping(value = "/create", consumes = "application/json")
//    @ResponseBody
//    public ResponseEntity createTeam(@RequestBody TeamInfo teamInfo) {
//        return teamServiceImpl.createTeam(teamInfo);
//    }
//
//    @GetMapping(value = "/get/{userid}", consumes = "application/json")
//    @ResponseBody
//    public ResponseEntity getTeam(@PathVariable("userid") Integer userid) {
//        return teamServiceImpl.getTeamsByUserId(userid,null);
//    }
//}
