package com.example.demo;

import com.example.demo.entities.User;
import com.example.demo.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootMybatisCache2RedisApplicationTests {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private UserMapper userMapper;

	@Test
	public void testInsert(){
		User user = new User(null, "test", 1);
		userMapper.insertOne(user);
	}

	@Test
	public void testMapper(){
		System.out.println(userMapper.selectOne(1));
	}

	@Test
	public void contextLoads() {
		System.out.println(redisTemplate.keys("*"));
		jdbcTemplate.execute("SELECT 1");
	}

}
