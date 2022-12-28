package com.twy.tripwithyou_spring.mapper;

import com.twy.tripwithyou_spring.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@SpringBootTest
class UserMapperTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    void insert() throws ParseException {
        UserDto user = new UserDto();
        user.setUserId("sample");
        user.setName("sample");
        user.setPw("sample");
        user.setBirth(new SimpleDateFormat("yyyy-MM-dd").parse("1999-10-22"));
        user.setPhone("sample");
        user.setGender(0);
        user.setEmail("sample");
        user.setLocation("sample");
        user.setHobby("sample");
        userMapper.insert(user);
    }

    @Test
    void updateById() throws ParseException {
        UserDto user = new UserDto();
        user.setUserId("sample");
        user.setName("sample2");
        user.setPw("sample3");
        user.setBirth(new SimpleDateFormat("yyyy-MM-dd").parse("1999-12-22"));
        user.setPhone("sample1");
        user.setGender(1);
        user.setEmail("sample1");
        user.setLocation("sample3");
        user.setHobby("sample3");
        userMapper.update(user);
    }

    @Test
    void deleteById() {
        userMapper.deleteById("sample");
    }
}