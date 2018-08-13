//package com.renchaigao.zujuba.service.impl;
//
//import com.alibaba.fastjson.JSONObject;
//import com.renchaigao.zujuba.domain.response.RespCode;
//import com.renchaigao.zujuba.domain.response.ResponseEntity;
//import com.renchaigao.zujuba.function.DistanceFunc;
//import com.renchaigao.zujuba.service.BusinessJoin;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.List;
//
//@Service
//public class BusinessJoinImpl implements BusinessJoin {
//
//
//    private static Logger logger = Logger.getLogger(BusinessJoinImpl.class);
//
//    @Autowired
//    StoreMapper storesMapper;
//    @Autowired
//    UserMapper userMapper;
//
//    @Override
//    public ResponseEntity addStores(Store stores) {
//        try {
//            storesMapper.insert(stores);
//            return new ResponseEntity(RespCode.SUCCESS, stores);
//        } catch (Exception e) {
//            return new ResponseEntity(RespCode.EXCEPTION, e);
//
//        }
//    }
//
//    /**********************************************
//     * 功能：生成系统下 各用户对应的recording 目录
//     * 入参：用户id  userId
//     **********************************************/
//    private String creatFilePathOnservice(Integer userId) {
//        File file = new File("/fpfolder/recording/users/" + userId.toString());
//        if (!file.exists())
//            file.mkdirs();
//        return "/fpfolder/recording/users/" + userId.toString() + "/";
//    }
//
//    private String creatPhotoFilePath(Integer userId, Integer storeId) {
//        String path = "/fpfolder/recording/users/" + userId.toString() + "/" + storeId.toString();
//        File file = new File(path);
//        if (!file.exists())
//            file.mkdirs();
//        return path + "/";
//    }
//
//    @Override
//    public ResponseEntity addPic(MultipartFile file) {
//        String filePathOnService = creatFilePathOnservice(1);
//        if (!file.isEmpty()) {
//            try {
//                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(
//                        new File(filePathOnService + file.getOriginalFilename())));
//                out.write(file.getBytes());
//                out.flush();
//                out.close();
//            } catch (FileNotFoundException e) {
//                logger.warn(e);
//                return new ResponseEntity(RespCode.WARN, e.getMessage());
//            } catch (IOException eIO) {
//                logger.warn(eIO);
//                return new ResponseEntity(RespCode.WARN, eIO.getMessage());
//            }
//            return new ResponseEntity(RespCode.SUCCESS);
//        } else {
//            return new ResponseEntity(RespCode.EXCEPTION);
//        }
//    }
//
//    @Override
//    public ResponseEntity addStore(String json, MultipartFile[] photos) {
//        if (null != json) {
//            try {
//                JSONObject storeJsonObject = JSONObject.parseObject(json);
//                Store store = JSONObject.parseObject(json, Store.class);
//                store.setPicturesurl(creatFilePathOnservice(store.getOwnerid()));
//                storesMapper.insert(store);
//                Integer storeId = store.getId();
//
//                //判断file数组不能为空并且长度大于0
//                if (photos != null && photos.length > 0) {
//                    //循环获取file数组中得文件
//                    for (int i = 0; i < photos.length; i++) {
//                        MultipartFile file = photos[i];
//                        //将不同storeID的文件放入不同的以storeID命名的文件夹下；
//                        String filePath = creatPhotoFilePath(store.getOwnerid(), storeId)
//                                + file.getOriginalFilename();
//                        // 转存文件
//                        try {
//                            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
//                            out.write(file.getBytes());
//                            out.flush();
//                            out.close();
//                        } catch (FileNotFoundException e) {
//                            logger.warn(e);
//                            return new ResponseEntity(RespCode.WARN, e.getMessage());
//                        } catch (IOException eIO) {
//                            logger.warn(eIO);
//                            return new ResponseEntity(RespCode.WARN, eIO.getMessage());
//                        }
//                    }
//                    return new ResponseEntity(RespCode.SUCCESS);
//                } else
//                    return new ResponseEntity(RespCode.STOREFILEWRONG, json);
//            } catch (Exception e) {
//                return new ResponseEntity(RespCode.EXCEPTION, e);
//            }
//        } else {
//            return new ResponseEntity(RespCode.STOREWRONG, json);
//        }
//    }
//
//    @Override
//    public ResponseEntity getStoreInfoByUserId(Integer userId) {
////        通过用户id查询用户的基本数据，获得用户的城市信息city 和 经纬度
//        User user = userMapper.selectByPrimaryKey(userId);
//        String userCityCode = user.getCitycode();
//        Double userX = user.getLatitude(), userY = user.getLongitude();
////        获取同用户所在城市的所有商铺的信息，并存入redis，保留id、经纬度；
//        List<Store> storeList = storesMapper.selectStoreByCityCode(userCityCode);
////        计算在城市中所有商铺距离用户的距离，排序；
//        List<StoreInfo> storeInfosList = new ArrayList<StoreInfo>();
//        for (int i = 0; i < storeList.size(); i++) {
//            storeInfosList.add(JSONObject.parseObject(JSONObject.toJSONString(storeList.get(i)),StoreInfo.class));
//            storeInfosList.get(i).setDistance(DistanceFunc.getDistance
//                    (userX,userY,storeList.get(i).getLatitude(),storeList.get(i).getLongitude()));
//        }
//        Collections.sort(storeInfosList, new Comparator<StoreInfo>() {
//            @Override
//            public int compare(StoreInfo o1, StoreInfo o2) {
////                    从小到大
//                    return (int)(o1.getDistance() - o2.getDistance());
////                    从大到小
////                return (int)(o2.getDistance() - o1.getDistance());
//            }
//        });
////        返回数据给用户前端；
//        return new ResponseEntity(RespCode.SUCCESS, storeInfosList);
//    }
//}
