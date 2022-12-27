package com.twy.tripwithyou_spring.service;

import com.twy.tripwithyou_spring.dto.CoursePlaceDto;
import com.twy.tripwithyou_spring.mapper.CoursePlaceMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoursePlaceServiceImp implements CoursePlaceService{
    private CoursePlaceMapper coursePlaceMapper;
    public CoursePlaceServiceImp(CoursePlaceMapper coursePlaceMapper) {
        this.coursePlaceMapper = coursePlaceMapper;
    }

    @Override
    public List<CoursePlaceDto> list(int courseNo) {
        return coursePlaceMapper.findByCourseNo(courseNo);
    }

    @Override
    public int register(CoursePlaceDto coursePlace) {
        return coursePlaceMapper.insert(coursePlace);
    }

    @Override
    public int modify(int courseNo, List<CoursePlaceDto> coursePlaceList) {
        int delete = coursePlaceMapper.deleteByCourseNo(courseNo);
        if (delete <= 0) {
            return delete;
        }
        int modify=0;
        for(CoursePlaceDto coursePlace : coursePlaceList) {
            modify += coursePlaceMapper.insert(coursePlace);
        }
        return modify;
    }
}