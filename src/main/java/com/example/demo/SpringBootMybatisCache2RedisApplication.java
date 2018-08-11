package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.example.demo.mapper")
public class SpringBootMybatisCache2RedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMybatisCache2RedisApplication.class, args);
	}
}
