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
        UserDto user = userMapper.findByUserIdAndPw(id, pw);
        return user;
    }

    @Override
    public int register(UserDto user) {
        return userMapper.insert(user);
    }
}
