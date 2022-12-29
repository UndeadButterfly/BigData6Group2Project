package com.twy.tripwithyou_spring.service;

import com.twy.tripwithyou_spring.dto.TempImgDto;
import com.twy.tripwithyou_spring.mapper.TempImgMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TempImgServiceImp implements TempImgService{

    private TempImgMapper tempImgMapper;
    public TempImgServiceImp(TempImgMapper tempImgMapper){
        this.tempImgMapper=tempImgMapper;
    }
    @Override
    public List<TempImgDto> list() {
        return tempImgMapper.findAll() ;
    }

}

