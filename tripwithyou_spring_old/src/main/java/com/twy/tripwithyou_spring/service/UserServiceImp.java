package com.twy.tripwithyou_spring.service;

import com.twy.tripwithyou_spring.dto.UserDto;
import com.twy.tripwithyou_spring.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    private UserMapper userMapper;

    public UserServiceImp(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDto login(String id, String pw) {
        System.out.println(userMapper);
        UserDto user = userMapper.findByUserIdAndPw(id, pw);
        System.out.println(user);
        return user;
    }
}
