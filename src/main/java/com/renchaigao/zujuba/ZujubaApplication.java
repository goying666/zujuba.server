package com.renchaigao.zujuba;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
@MapperScan("com.renchaigao.zujuba.dao.mapper")
public class ZujubaApplication {
	public static void main(String[] args) {
		SpringApplication.run(ZujubaApplication.class, args);

	}
}
