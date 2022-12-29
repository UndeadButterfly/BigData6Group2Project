package com.twy.tripwithyou_spring.service;

import com.twy.tripwithyou_spring.dto.ZzimDto;
import com.twy.tripwithyou_spring.dto.ZzimViewDto;

public interface ZzimService {
    ZzimViewDto detailZzimView(int courseNo, String loginUserID);
    int register(ZzimDto zzim);
    int remove(int zzimNo);
    ZzimDto detail(int zzimNo, String loginUserId);
}
