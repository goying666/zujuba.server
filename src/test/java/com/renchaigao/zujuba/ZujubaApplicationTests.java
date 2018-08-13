package com.renchaigao.zujuba;

import com.alibaba.fastjson.JSONObject;
import com.renchaigao.zujuba.domain.info.user.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZujubaApplicationTests {
	@Autowired
	StringRedisTemplate redisTemplate;

	@Autowired
	MongoTemplate mongoTemplate;

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

	@Test
	public void mongoDBupdataTest(){
		UserInfo userInfo = JSONObject.parseObject("{\n" +
				"    \"age\": \"0\",\n" +
				"    \"ageLevel\": \"0\",\n" +
				"    \"deleteStyle\": false,\n" +
				"    \"gender\": \"0\",\n" +
				"    \"id\": \"45521042ff0b42aebfedc5d2e4656759\",\n" +
				"    \"idCard\": \"0\",\n" +
				"    \"job\": \"0\",\n" +
				"    \"marriage\": \"0\",\n" +
				"    \"myAddressId\": \"43f47ed2a1184f8da1e6c56bfa3227d8\",\n" +
				"    \"myAddressInfo\": {\n" +
				"        \"id\": \"43f47ed2a1184f8da1e6c56bfa3227d8\"\n" +
				"    },\n" +
				"    \"myFreiendInfo\": {\n" +
				"        \"id\": \"194b0a0d3dc64fc18161d0e33923f059\"\n" +
				"    },\n" +
				"    \"myFriendInfoId\": \"194b0a0d3dc64fc18161d0e33923f059\",\n" +
				"    \"myGamesId\": \"1f274c3a51c043b4bfe608bec83089b3\",\n" +
				"    \"myIntegrationInfoId\": \"67749b72d0b1418e9498c3c5f3bbe75e\",\n" +
				"    \"myMessageInfoId\": \"23fb51f5a9af4462b9baa81dfedc848e\",\n" +
				"    \"myOpenInfoId\": \"5f4b19f450424506a6acff96d30afa5a\",\n" +
				"    \"myPermissionInfo\": {\n" +
				"        \"id\": \"5c097ba2d6514039b47c677c9504793b\"\n" +
				"    },\n" +
				"    \"myPermissionInfoId\": \"5c097ba2d6514039b47c677c9504793b\",\n" +
				"    \"myPhotoInfo\": {\n" +
				"        \"deleteStyle\": false,\n" +
				"        \"id\": \"ea87c3d84f9947cebc760f476eec8ff0\",\n" +
				"        \"replace\": true\n" +
				"    },\n" +
				"    \"myPlayGamesInfo\": {\n" +
				"        \"id\": \"1f274c3a51c043b4bfe608bec83089b3\"\n" +
				"    },\n" +
				"    \"myRankInfoId\": \"5c7bf6dd88c24ba1a4773370e1a8184b\",\n" +
				"    \"mySpendInfo\": {\n" +
				"        \"id\": \"add512c40b264fd2b69339d2aabe239f\"\n" +
				"    },\n" +
				"    \"mySpendInfoId\": \"add512c40b264fd2b69339d2aabe239f\",\n" +
				"    \"myStoresId\": \"535c2481c6984579a2f308a03a392f8f\",\n" +
				"    \"myStoresInfo\": {\n" +
				"        \"id\": \"535c2481c6984579a2f308a03a392f8f\"\n" +
				"    },\n" +
				"    \"myTeamsId\": \"86974bada0d643f794b0f18e6198ee7f\",\n" +
				"    \"myTeamsInfo\": {\n" +
				"        \"id\": \"86974bada0d643f794b0f18e6198ee7f\"\n" +
				"    },\n" +
				"    \"nickName\": \"0\",\n" +
				"    \"peopleListId\": \"c263595899d44554a82e5104b18915f7\",\n" +
				"    \"photoInfoId\": \"ea87c3d84f9947cebc760f476eec8ff0\",\n" +
				"    \"picPath\": \"0\",\n" +
				"    \"realName\": \"0\",\n" +
				"    \"telephone\": \"13040837899\",\n" +
				"    \"token\": \"sTFoAPtC7fYhZNsKww222g==\",\n" +
				"    \"upTime\": \"2018-8-10 21:06:17\",\n" +
				"    \"userInfoId\": \"9c260ae41e8c4ae29256dff04e93ec33\",\n" +
				"    \"userPWD\": \"123456\"\n" +
				"}",UserInfo.class);
		Criteria criteria = Criteria.where("id").is("45521042ff0b42aebfedc5d2e4656759");
		Update update = Update.update("userInfo", userInfo);
		mongoTemplate.updateFirst(Query.query(criteria), update, UserInfo.class);
	}
	@Test
	public void mongoDBInsertTest(){

		UserInfo userInfo = new UserInfo();
		userInfo.setId("123");
		userInfo.setNickName("nicknameabc");
//		测试增加
		mongoTemplate.insert(userInfo);

		UserInfo userInfo1 = new UserInfo();
		userInfo1.setId("1234");
		userInfo1.setNickName("nickname1111");
//		测试增加
		mongoTemplate.insert(userInfo1);


		UserInfo userInfo2 = new UserInfo();
		userInfo2.setId("12345");
		userInfo2.setNickName("nickname222");
//		测试增加
		mongoTemplate.insert(userInfo2,"user2");

//		Criteria criteria = Criteria.where("id").is("45521042ff0b42aebfedc5d2e4656759");
//		Update update = Update.update("userInfo", userInfo);
//		mongoTemplate.updateFirst(Query.query(criteria), update, UserInfo.class);
	}

	@Test
	public void mongoDBSaveTest(){
		UserInfo userInfo = new UserInfo();
		userInfo.setId("12345");
		userInfo.setNickName("nicknameabc save");
		mongoTemplate.save(userInfo);
	}
	@Test
	public void mongoDBUpdateTest(){
		UserInfo userInfo = new UserInfo();
		userInfo.setId("12345");
		userInfo.setNickName("update test");


		Criteria criteria = Criteria.where("id").is("12345");
		Update update = Update.update("nickName","gaoyan");
		mongoTemplate.updateFirst(Query.query(criteria), update, UserInfo.class);
	}




}
