package com.renchaigao.zujuba.domain.info;

import com.renchaigao.zujuba.dao.Address;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class addressInfo extends Address {
    private Integer distance;
    private Double startLatitude;
    private Double startLongitude;

}
