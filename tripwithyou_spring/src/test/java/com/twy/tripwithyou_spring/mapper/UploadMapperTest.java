package com.twy.tripwithyou_spring.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UploadMapperTest {
    @Autowired
    private UploadMapper uploadMapper;
    @Test
    void findAll() {
        uploadMapper.findAll();
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
}