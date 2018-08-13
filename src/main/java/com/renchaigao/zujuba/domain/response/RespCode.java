package com.renchaigao.zujuba.domain.response;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum  RespCode {
    EXCEPTION(1, "Throw an exception"),
    SUCCESS(0, "Request success"),
    UPDATEUSERINFOSUCCESS(1002, "Update userinfo success"),

    /* normal part*/
    WRONGIP(-2,"IP WRONG"),
    WARN(-1, "Network exception, please try again later"),
    /* normal part*/

    /* user part*/
    USERINFOADD(1002,"Add new userInfo"),
    USERHAD(1001,"User already exists"),

    WRONGPWD(-1001,"Wrong password"),
    USERNOTEL(-1002,"Telephone missing"),
    TOKENOLD(-1003,"Token is Expired"),
    TOKENWRONG(-1004,"Token is wrong"),
    TOKENEXCEPTION(-1005,"Make Token is wrong"),
    USERADDFAIL(-1006,"Add user fail"),
    USERINFONONE(-1007,"No userInfo."),
    PASSWORDMISSING(-1008,"No set pwd."),
    TELEPHONENOJOIN(-1009,"Telephone is not join."),
    /* user login part*/
    STOREWRONG(-1100,"Storeinfo is wrong"),
    STOREFILEWRONG(-1101,"StoreFile is wrong"),

    TEAMHAD(-1201,"Same team had exists");

    private int code;
    private String msg;

    RespCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
}
