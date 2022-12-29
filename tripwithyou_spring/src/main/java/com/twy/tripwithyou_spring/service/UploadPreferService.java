package com.twy.tripwithyou_spring.service;

import com.twy.tripwithyou_spring.dto.UploadPreferDto;
import com.twy.tripwithyou_spring.dto.UploadPreferViewDto;

public interface UploadPreferService {
    UploadPreferViewDto detailUploadPreferView(int uploadNo,String loginUserId);
    int register(UploadPreferDto uploadPrefer);
    int modify(UploadPreferDto uploadPrefer);
    int remove(int uploadPreferNo);
    UploadPreferDto detail(int uploadNo,String loginUserId);
}
