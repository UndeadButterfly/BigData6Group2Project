package com.twy.tripwithyou_spring.service;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UploadServiceImpTest {
    @Autowired
    private UploadService uploadService;
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Test
    void testList() {
    }

    @Test
    void detail() {
    }

    @Test
    void register() {
    }

    @Test
    void list() {
    }

    @Test
    void testList1() {
    }

    @Test
    void testList2() {
    }

    @Test
    void modify() {
    }

    @Test
    void delete() {
        int delete = uploadService.delete(31);
        System.out.println(delete);
    }

    @Test
    void popularList() {
    }
}