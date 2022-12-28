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
        int totalRows=uploadMapper.countByType(uptype);
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

    @Transactional
    @Override
    public int delete(UploadDto upload) {
        int delete=0;
        if(upload.getUploadImgList()!=null){
            int imgCnt=upload.getUploadImgList().size();
            for(UploadImgDto uploadImg : upload.getUploadImgList()){
                delete+=uploadImgMapper.deleteOne(uploadImg.getImgNo());
            }
            if (delete==imgCnt) {
                delete += uploadMapper.deleteById(upload.getUploadNo());
            }
        }else{
            delete+=uploadMapper.deleteById(upload.getUploadNo());
        }
        return delete;
    }

    // PagingDto 를 받는 것으로 바꿈
    @Override
    public List<UploadDto> popularList(PagingDto paging) {
        paging.setTotalRows(uploadMapper.countByType(1));
        return uploadMapper.findByPopular(paging,1);
    }
    //12.27 검색 서비스
    @Override
    public List<UploadDto> searchingList(String searching, String condition, PagingDto paging, int upType) {
        List<UploadDto> searchingList=uploadMapper.findBySearching(searching,condition,paging,upType);
        paging.setOrderField(condition);
        int totalRows=uploadMapper.countBySearching(searching,condition,upType);
        paging.setTotalRows(totalRows);
        return searchingList;
    }
}
