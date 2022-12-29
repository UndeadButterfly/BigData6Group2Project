package com.twy.tripwithyou_spring.service;

import com.twy.tripwithyou_spring.dto.ZzimDto;
import com.twy.tripwithyou_spring.dto.ZzimViewDto;
import com.twy.tripwithyou_spring.mapper.CourseMapper;
import com.twy.tripwithyou_spring.mapper.UserMapper;
import com.twy.tripwithyou_spring.mapper.ZzimMapper;
import org.springframework.stereotype.Service;

@Service
public class ZzimServiceImp implements ZzimService {
    CourseMapper courseMapper;
    ZzimMapper zzimMapper;

    public ZzimServiceImp(CourseMapper courseMapper, ZzimMapper zzimMapper, UserMapper userMapper) {
        this.courseMapper = courseMapper;
        this.zzimMapper = zzimMapper;
        this.userMapper = userMapper;
    }

    UserMapper userMapper;

    @Override
    public ZzimViewDto detailZzimView(int courseNo, String loginUserID) {
        return null;
    }

    @Override
    public int register(ZzimDto zzim) {
        return 0;
    }

    @Override
    public int remove(int zzimNo) {
        return 0;
    }

    @Override
    public ZzimDto detail(int zzimNo, String loginUserId) {
        return null;
    }
}
