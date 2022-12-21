package com.twy.tripwithyou_spring.service;

import com.twy.tripwithyou_spring.dto.PagingDto;
import com.twy.tripwithyou_spring.dto.UploadDto;

import java.util.List;

public interface UploadService {
    List<UploadDto> testList();
    // 디테일
    UploadDto detail(int uploadNo);

    // 게시판 글 등록
    int register(UploadDto upload);
    //게시판 목록
    List<UploadDto> list(PagingDto paging);
    //게시판 글 수정
    int modify(UploadDto upload);
    //게시글 삭제
    int delete(UploadDto upload);
    //로그인 여부 확인
}
