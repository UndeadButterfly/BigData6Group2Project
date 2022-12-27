package com.twy.tripwithyou_spring.service;

import com.twy.tripwithyou_spring.dto.CourseDto;
import com.twy.tripwithyou_spring.dto.PagingDto;
import com.twy.tripwithyou_spring.dto.UploadDto;
import com.twy.tripwithyou_spring.mapper.CourseMapper;
import com.twy.tripwithyou_spring.mapper.UploadMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImp implements CourseService{
    private CourseMapper courseMapper;
    private UploadMapper uploadMapper;

    public CourseServiceImp(CourseMapper courseMapper, UploadMapper uploadMapper) {
        this.courseMapper = courseMapper;
        this.uploadMapper = uploadMapper;
    }

    @Override
    public List<List<CourseDto>> mainList() {
        List<String> orderFields = new ArrayList<>();
        orderFields.add("upload_no"); //가장 최근것부터
        orderFields.add("likes"); //가장 선호하는 코스
        orderFields.add("views"); //가장 인기있는 코스
        List<List<CourseDto>> courseListsList=new ArrayList<>();

        for(String orderField : orderFields) {
            //생성자로 가능하게 만들기?
            PagingDto paging = new PagingDto(1, 10, orderField, "DESC");
            List<UploadDto> uploadList = uploadMapper.findByType(2, paging);
            System.out.println(uploadList);
            List<CourseDto> courseList = new ArrayList<>();
            for(UploadDto upload : uploadList) {
                CourseDto course = courseMapper.findByUploadNo(upload.getUploadNo());
                courseList.add(course);
            }
            courseListsList.add(courseList);
        }

        return courseListsList;
    }

    @Override
    public CourseDto detail(int courseNo) {
        return courseMapper.findById(courseNo);
    }

    @Override
    public List<CourseDto> list(PagingDto pagingDto) {
        return courseMapper.findPaging(pagingDto);
    }

    @Override
    public List<CourseDto> listById(PagingDto pagingDto, String userId) {
        int totalRows = courseMapper.countById(userId);
        pagingDto.setTotalRows(totalRows);
        return courseMapper.findPagingByUserId(pagingDto,userId);
    }

    @Override
    public int register(CourseDto course) {
        int register = 0;
        UploadDto upload = course.getUploadDto();
        register = uploadMapper.insert(upload);
        int uploadNo = upload.getUploadNo();
        course.setUploadNo(uploadNo);
        register += courseMapper.insert(course);
        System.out.println(register);

        return register;
    }

    @Override
    public int modify(CourseDto course) {
        int modify = uploadMapper.updateById(course.getUploadDto());
        if(modify<1) {
            return modify;
        }
        modify += courseMapper.updateById(course);
        return modify;
    }
}