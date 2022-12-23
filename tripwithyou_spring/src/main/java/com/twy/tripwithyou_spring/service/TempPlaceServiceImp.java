package com.twy.tripwithyou_spring.service;

import com.twy.tripwithyou_spring.dto.TempPlaceDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TempPlaceServiceImp implements TempPlaceService {

    private  TempPlaceMapper tempPlaceMapper;

    @Override
    public List<TempPlaceDto> list(int tPlaceNo) {
        return null;
    }
}
