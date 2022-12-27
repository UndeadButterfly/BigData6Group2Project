package com.twy.tripwithyou_spring.dto;

public class TemplateHashDto {
    private int hashNo; //PK hash_no
    private int templateNo; //FK Upload(template_no):TemplateHash(template_no) = 1:N
    private String hashWord; //hash_word
}
