package com.renchaigao.zujuba.domain.info.user;

import com.renchaigao.zujuba.dao.User;
import com.renchaigao.zujuba.dao.UserRank;
import com.renchaigao.zujuba.domain.info.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserInfo extends User {
    private String userId;
    private myTeamsInfo myTeamsInfo;
    private myPlayGamesInfo myPlayGamesInfo;
    private myStoresInfo myStoresInfo;
    private photo myPhotoInfo;
    private addressInfo myAddressInfo;
    private userSpendInfo mySpendInfo;
    private UserRank myRankInfo;
    private userFriendInfo myFreiendInfo;
//    private ; //积分信息
//    private ; //名单信息
    private userPermissionInfo myPermissionInfo;

    public UserInfo(){

    }
    public UserInfo(User user){
        this.setId(user.getId());
    }
}
