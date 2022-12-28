package com.twy.tripwithyou_spring.service;

import com.twy.tripwithyou_spring.dto.TempPlaceDto;
import com.twy.tripwithyou_spring.mapper.TempPlaceMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TempPlaceServiceImp implements TempPlaceService {

    private TempPlaceMapper tempPlaceMapper;
    public TempPlaceServiceImp(TempPlaceMapper tempPlaceMapper){
        this.tempPlaceMapper = tempPlaceMapper;
    }
    @Override
    public List<TempPlaceDto> list(int tpNo) {
        return tempPlaceMapper.findByTpNo(tpNo);
    }
}
