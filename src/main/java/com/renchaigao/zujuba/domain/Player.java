package com.renchaigao.zujuba.domain;

import com.renchaigao.zujuba.dao.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Player extends User {

    private Long distance;
    private String gameState;//游戏状态：游戏中，离场；
    private String teamState;//组局状态：加入，准备，到场，游戏中；
}
