package com.renchaigao.zujuba.domain.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class RequestEntity {
    private int code;
    private String msg;
    private Object data;

    public RequestEntity(RequestCode respCode){
        this.code = respCode.getCode();
        this.msg = respCode.getMsg();
    }

    public  RequestEntity(RequestCode respCode,Object data){
        this(respCode);
        this.data = data;
    }
}
