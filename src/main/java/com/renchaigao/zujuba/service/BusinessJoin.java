package com.renchaigao.zujuba.service;

import com.renchaigao.zujuba.dao.Stores;
import com.renchaigao.zujuba.domain.response.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface BusinessJoin {

    ResponseEntity addStores(Stores stores);

    ResponseEntity addPic(MultipartFile file);
}
