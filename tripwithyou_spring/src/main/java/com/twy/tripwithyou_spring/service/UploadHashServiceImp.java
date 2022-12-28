package com.twy.tripwithyou_spring.service;

import com.twy.tripwithyou_spring.dto.CourseDto;
import com.twy.tripwithyou_spring.dto.CoursePlaceDto;
import com.twy.tripwithyou_spring.dto.UploadHashDto;
import com.twy.tripwithyou_spring.dto.VehicleDto;
import com.twy.tripwithyou_spring.mapper.UploadHashMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UploadHashServiceImp implements UploadHashService {
    private UploadHashMapper uploadHashMapper;

    public UploadHashServiceImp(UploadHashMapper uploadHashMapper) {
        this.uploadHashMapper = uploadHashMapper;
    }

    @Override
    public int registerCourseHash(CourseDto course) {
        int register=0;
        UploadHashDto uploadHash = new UploadHashDto();
        uploadHash.setUploadNo(course.getUploadNo());
        System.out.println("업로드 타입:");
        uploadHash.setUptype(course.getUploadDto().getUpType());

        uploadHash.setHashWord(course.getUploadDto().getTitle());
        register += uploadHashMapper.insertOne(uploadHash);
        uploadHash.setHashWord(course.getUploadDto().getContents());
        register += uploadHashMapper.insertOne(uploadHash);
        uploadHash.setHashWord(course.getUploadDto().getUserId());
        register += uploadHashMapper.insertOne(uploadHash);
        uploadHash.setHashWord("2");
        register += uploadHashMapper.insertOne(uploadHash);

        uploadHash.setHashWord((course.getDuration()-1)+"박"+course.getDuration()+"일");
        register += uploadHashMapper.insertOne(uploadHash);

        for(CoursePlaceDto coursePlace : course.getCoursePlaceList()) {
            uploadHash.setHashWord(coursePlace.getAddress());
            register += uploadHashMapper.insertOne(uploadHash);
            if(coursePlace.getMemo()!=null) {
                uploadHash.setHashWord(coursePlace.getMemo());
                register += uploadHashMapper.insertOne(uploadHash);
            }
            uploadHash.setHashWord(coursePlace.getName());
            register += uploadHashMapper.insertOne(uploadHash);
            uploadHash.setHashWord(coursePlace.getRate().toString());
            register += uploadHashMapper.insertOne(uploadHash);
        }

        for(VehicleDto vehicle : course.getVehicleList()) {
            uploadHash.setHashWord(vehicle.getVType());
            register += uploadHashMapper.insertOne(uploadHash);
            if(vehicle.getMemo()!=null) {
                uploadHash.setHashWord(vehicle.getMemo());
                register += uploadHashMapper.insertOne(uploadHash);
            }
        }

        return register;
    }

    @Override
    public List<UploadHashDto> search(String word, int uptype) {
        return uploadHashMapper.findByWordForType(word, uptype);
    }

    @Override
    public int modifyCourseHash(CourseDto course) {
        System.out.println(course);
        int modify = uploadHashMapper.deleteByUploadNo(course.getUploadNo());
        modify += registerCourseHash(course);
        return modify;
    }
}
