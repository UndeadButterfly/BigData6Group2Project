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
        userMapper.updateLogin(id);
        userMapper.switchLoginState(id);
        UserDto user = userMapper.findByUserIdAndPw(id, pw);
        return user;
    }

    @Override
    public UserDto logout(UserDto user) {
        userMapper.switchLoginState(user.getUserId());
        return user;
    }

    @Override
    public int register(UserDto user) {
        return userMapper.insert(user);
    }

    @Override
    public UserDto getInfo(String userId) {
        return userMapper.findById(userId);
    }

    @Override
    public int withdrawal(String userId) {
        return userMapper.deleteById(userId);
    }

    @Override
    public int modify(UserDto user) {
        return userMapper.update(user);
    }

}
