package com.renchaigao.zujuba;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZujubaApplicationTests {
	@Autowired
	StringRedisTemplate redisTemplate;
	@Test
	public void contextLoads() {
		redisTemplate.opsForValue().set("teamInfo","teamInfo this here.");
		String string = redisTemplate.opsForValue().get("teamInfo");
	}
	@Test
	public void redisTest() {
		redisTemplate.opsForValue().set("teamInfo1","22222");
		redisTemplate.opsForValue().set("teamInfo1","11111.");
		String string = redisTemplate.opsForValue().get("teamInfo");
	}

}
