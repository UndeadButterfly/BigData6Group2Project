package com.twy.tripwithyou_spring.mapper;

import com.twy.tripwithyou_spring.dto.PagingDto;
import com.twy.tripwithyou_spring.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends CRUD<UserDto,String>{
    List<UserDto> findAll();
    UserDto findByUserIdAndPw(String id, String pw);
    List<UserDto> findPaging(PagingDto paging);
    int insert(UserDto dto);
    int updateLogin(String userId);

    void switchLoginState(String id);
}
