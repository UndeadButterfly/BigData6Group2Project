package com.twy.tripwithyou_spring.service;

import com.twy.tripwithyou_spring.dto.UploadPreferDto;
import com.twy.tripwithyou_spring.dto.UploadPreferViewDto;
import com.twy.tripwithyou_spring.mapper.UploadMapper;
import com.twy.tripwithyou_spring.mapper.UploadPreferMapper;
import com.twy.tripwithyou_spring.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class UploadPreferServiceImp implements UploadPreferService{
    private UploadMapper uploadMapper;
    private UploadPreferMapper uploadPreferMapper;
    private UserMapper userMapper;

    public UploadPreferServiceImp(UploadMapper uploadMapper, UploadPreferMapper uploadPreferMapper, UserMapper userMapper) {
        this.uploadMapper = uploadMapper;
        this.uploadPreferMapper = uploadPreferMapper;
        this.userMapper = userMapper;
    }
    @Transactional
    @Override
    public UploadPreferViewDto detailUploadPreferView(int uploadNo, String loginUserId) {
        UploadPreferViewDto detailPreferView;
        detailPreferView=uploadMapper.countPreferById(uploadNo);
        if(loginUserId!=null){
            UploadPreferDto loginUserPrefer=uploadPreferMapper.findByUploadNoAndUserId(uploadNo,loginUserId);
            detailPreferView.setLoginUserPrefer(loginUserPrefer);
        }
        return detailPreferView;
    }

    @Override
    public int register(UploadPreferDto uploadPrefer) {
        return uploadPreferMapper.insert(uploadPrefer);
    }

    @Override
    public int modify(UploadPreferDto uploadPrefer) {
        return uploadPreferMapper.update(uploadPrefer);
    }

    @Override
    public int remove(int uploadPreferNo) {
        return uploadPreferMapper.delete(uploadPreferNo);
    }

    @Override
    public UploadPreferDto detail(int uploadNo, String loginUserId) {
        UploadPreferDto detail=uploadPreferMapper.findByUploadNoAndUserId(uploadNo,loginUserId);
        return detail;
    }
}
