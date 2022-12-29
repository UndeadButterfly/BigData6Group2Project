package com.twy.tripwithyou_spring.service;

import com.twy.tripwithyou_spring.dto.UserDto;

public interface UserService {
    UserDto login(String id, String pw);
    UserDto logout(UserDto user);

    int register(UserDto user);

    UserDto getInfo(String userId);

    int withdrawal(String userId);

    int modify(UserDto user);
}
