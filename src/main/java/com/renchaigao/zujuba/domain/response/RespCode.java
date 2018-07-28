package com.renchaigao.zujuba.domain.response;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum  RespCode {
    EXCEPTION(1, "Throw an exception"),
    SUCCESS(0, "Request success"),
    UPDATEUSERINFOSUCCESS(1002, "Update userinfo success"),
    WARN(-1, "Network exception, please try again later"),
    USERHAD(1001,"User already exists"),
    WRONGPWD(-1001,"Wrong password"),
    USERNOTEL(-1002,"Telephone missing"),
    TOKENOLD(-1003,"Token is Expired"),
    TOKENWRONG(-1004,"Token is wrong"),
    STOREWRONG(-1100,"Storeinfo is wrong"),
    STOREFILEWRONG(-1101,"StoreFile is wrong");



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
