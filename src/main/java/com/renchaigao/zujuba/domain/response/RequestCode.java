package com.renchaigao.zujuba.domain.response;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum  RequestCode {
    EXCEPTION(1, "Throw an exception"),
    STOREFILEWRONG(-1101,"StoreFile is wrong");

    private int code;
    private String msg;

    RequestCode(int code, String msg) {
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
