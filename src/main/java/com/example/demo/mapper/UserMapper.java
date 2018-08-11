package com.example.demo.mapper;

import com.example.demo.entities.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description:
 * @author: swg
 * @created: 2018/8/11
 */
@Mapper
public interface UserMapper {

    User selectOne(Integer id);

    void insertOne(User user);

}
