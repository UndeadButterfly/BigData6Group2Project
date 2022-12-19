package com.twy.tripwithyou_spring.service;

import com.twy.tripwithyou_spring.dto.PagingDto;
import com.twy.tripwithyou_spring.dto.UploadDto;
import com.twy.tripwithyou_spring.mapper.UploadMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UploadServiceImp implements UploadService{
    private UploadMapper uploadMapper;

    public UploadServiceImp(UploadMapper uploadMapper) {
        this.uploadMapper = uploadMapper;
    }

    @Override
    public List<UploadDto> testList(){
        return uploadMapper.findAll();
    }

    @Override
    public UploadDto detail(int uploadNo) {
        return uploadMapper.findById(uploadNo);
    }

    @Override
    public int register(UploadDto upload) {
        return 0;
    }

    @Override
    public List<UploadDto> list(PagingDto paging) {
        int totalRows=uploadMapper.count(paging);
        paging.setTotalRows(totalRows);
        List<UploadDto> uploadList = uploadMapper.findPaging(paging);
        return uploadList;
    }

    @Override
    public int modify(UploadDto upload) {
        return 0;
    }

    @Override
    public int delete(UploadDto upload) {
        return 0;
    }
}
