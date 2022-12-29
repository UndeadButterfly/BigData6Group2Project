package com.twy.tripwithyou_spring.dto;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class UserDtoTest {
    private UserDto user=new UserDto();
    @Test
    void setAges() throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("1990-08-44");
        user.setBirth(date);
        user.setAges();
        System.out.println(user.getDate().getYear());
        System.out.println(date.getYear());
        System.out.println(user.getAges());
    }
}