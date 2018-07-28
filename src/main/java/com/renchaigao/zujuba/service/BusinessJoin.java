package com.renchaigao.zujuba.service;

import com.alibaba.fastjson.JSONObject;
import com.renchaigao.zujuba.dao.Store;
import com.renchaigao.zujuba.domain.response.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface BusinessJoin {

    ResponseEntity addStores(Store stores);

    ResponseEntity addPic(MultipartFile file);

    ResponseEntity addStore(String json, MultipartFile[] photos);
    ResponseEntity getStoreInfoByUserId(Integer userId);

}
