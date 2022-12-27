package com.twy.tripwithyou_spring.service;

import com.twy.tripwithyou_spring.dto.UserDto;

public interface UserService {
    UserDto login(String id, String pw);
}
