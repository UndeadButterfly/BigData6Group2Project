package com.twy.tripwithyou_spring.mapper;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CourseMapperTest {
    @Autowired
    private CourseMapper courseMapper;
    private Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());
    @Test
    void findAll() {
    }

    @Test
    void findPaging() {
    }

    @Test
    void count() {
    }

    @Test
    void findById() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void updateById() {
    }

    @Test
    void insert() {
    }

    @Test
    void findByUploadNo() {
    }

    @Test
    void findPagingByUserId() {

    }

    @Test
    void countById() {
    }

    @Test
    void countZzimById() {
        log.info(courseMapper.countZzimById(1).toString());
    }
}