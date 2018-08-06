package com.renchaigao.zujuba.mongodb.dao;

import com.renchaigao.zujuba.domain.StoreInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StoreMG extends MongoRepository<StoreInfo,Long>{
}
