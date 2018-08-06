package com.renchaigao.zujuba.domain;

import com.renchaigao.zujuba.dao.Team;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TeamInfo extends Team {

    private String scoreFilter;
    private String scoreFilterID;
    private AddressInfo addressInfo;
    private FilterInfo filterInfo;
    private PlayerInfo playerInfo;
}
