package com.twy.tripwithyou_spring.service;

import com.twy.tripwithyou_spring.dto.PagingDto;
import com.twy.tripwithyou_spring.dto.UploadDto;
import com.twy.tripwithyou_spring.dto.UploadImgDto;
import com.twy.tripwithyou_spring.mapper.UploadImgMapper;
import com.twy.tripwithyou_spring.mapper.UploadMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UploadServiceImp implements UploadService{
    private UploadMapper uploadMapper;
    private UploadImgMapper uploadImgMapper;

    public UploadServiceImp(UploadMapper uploadMapper, UploadImgMapper uploadImgMapper) {
        this.uploadMapper = uploadMapper;
        this.uploadImgMapper = uploadImgMapper;
    }

    @Override
    public List<UploadDto> testList(){
        return uploadMapper.findAll();
    }

    @Override
    public UploadDto detail(int uploadNo) {
        return uploadMapper.findById(uploadNo);
    }

    @Transactional
    @Override
    public int register(UploadDto upload) {
        int register=0;
        register+=uploadMapper.insert(upload);
        if (upload.getUploadImgList()!=null){
            for(UploadImgDto uploadImg : upload.getUploadImgList()){
                uploadImg.setUploadNo(upload.getUploadNo());
                register+=uploadImgMapper.insertOne(uploadImg);
            }
        }
        return register;
    }

    @Override
    public List<UploadDto> list(PagingDto paging) {
        int totalRows=uploadMapper.count(paging);
        paging.setTotalRows(totalRows);
        List<UploadDto> uploadList = uploadMapper.findPaging(paging);
        return uploadList;
    }

    @Override
    public List<UploadDto> list(PagingDto paging, int uptype) {
        int totalRows=uploadMapper.countByType(paging,uptype);
        paging.setTotalRows(totalRows);
        List<UploadDto> uploadList = uploadMapper.findByType(uptype,paging);
        return uploadList;
    }

    @Override
    public List<UploadDto> list(PagingDto paging, String userId) {
        int totalRows=uploadMapper.countByUserId(paging,userId);
        paging.setTotalRows(totalRows);
        List<UploadDto> uploadList = uploadMapper.findPagingByUserId(paging,userId);
        return uploadList;
    }

    @Transactional
    @Override
    public List<UploadImgDto> modify(UploadDto upload, int[] delImgNos) {
        int modify=0;
        List<UploadImgDto> delImgList=new ArrayList<>();
        if(delImgNos!=null){
            for(int delImgNo : delImgNos){
                UploadImgDto delImg=uploadImgMapper.findById(delImgNo);
                delImgList.add(delImg);
                uploadImgMapper.deleteOne(delImgNo);
            }
        }
        if(upload.getUploadImgList()!=null){
            for(UploadImgDto uploadImg : upload.getUploadImgList()){
                uploadImg.setUploadNo(upload.getUploadNo());
                uploadImgMapper.insertOne(uploadImg);
            }
        }
        System.out.println(upload);
        uploadMapper.updateById(upload);
        return delImgList;
    }

    @Override
    public int delete(UploadDto upload) {
        return 0;
    }

    @Override
    public List<UploadDto> popularList(PagingDto paging) {
        return uploadMapper.findByPrefer(paging,1);
    }
}
