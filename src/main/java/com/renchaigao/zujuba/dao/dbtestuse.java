package com.renchaigao.zujuba.dao;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Setter
@Getter
public class dbtestuse {

    @Id
    private Integer id;
    private String name;

    public dbtestuse(Integer id,String name)
    {
        this.id = id;
        this.name = name;
    }
}
