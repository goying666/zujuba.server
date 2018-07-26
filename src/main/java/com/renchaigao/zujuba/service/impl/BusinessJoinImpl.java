package com.renchaigao.zujuba.service.impl;

import com.renchaigao.zujuba.dao.Stores;
import com.renchaigao.zujuba.dao.mapper.StoresMapper;
import com.renchaigao.zujuba.domain.response.RespCode;
import com.renchaigao.zujuba.domain.response.ResponseEntity;
import com.renchaigao.zujuba.service.BusinessJoin;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Service
public class BusinessJoinImpl implements BusinessJoin{


    private static Logger logger = Logger.getLogger(BusinessJoinImpl.class);

    @Autowired
    StoresMapper storesMapper;

    @Override
    public ResponseEntity addStores(Stores stores) {
        try {
            storesMapper.insert(stores);
            return new ResponseEntity(RespCode.SUCCESS, stores);
        }catch (Exception e){
            return new ResponseEntity(RespCode.EXCEPTION, e);

        }
    }
    /**********************************************
     * 功能：生成系统下 各用户对应的recording 目录
     * 入参：用户id  userId
     **********************************************/
    private String creatRecodingPathOnservice(Integer userId) {
        File file = new File("/fpfolder/recording/users/" + userId.toString());
        if (!file.exists())
            file.mkdirs();
        return "/fpfolder/recording/users/" + userId.toString() + "/";
    }
    @Override
    public ResponseEntity addPic(MultipartFile file){
        String filePathOnService = creatRecodingPathOnservice(1);
        if (!file.isEmpty()) {
            try {
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(
                        new File(filePathOnService + file.getOriginalFilename())));
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                logger.warn(e);
                return new ResponseEntity(RespCode.WARN, e.getMessage());
            } catch (IOException eIO) {
                logger.warn(eIO);
                return new ResponseEntity(RespCode.WARN, eIO.getMessage());
            }
            return new ResponseEntity(RespCode.SUCCESS);
        } else {
            return new ResponseEntity(RespCode.EXCEPTION);
        }
    }
}
