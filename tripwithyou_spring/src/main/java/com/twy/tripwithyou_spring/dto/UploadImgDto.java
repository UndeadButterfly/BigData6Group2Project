package com.twy.tripwithyou_spring.dto;

import lombok.Data;

@Data
public class UploadImgDto {
    private int imgNo; //pk auto increment
    private int uploadNo; //fk board.board_no
    private String imgPath;
}
