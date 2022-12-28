package com.twy.tripwithyou_spring.service;

import com.twy.tripwithyou_spring.dto.PagingDto;
import com.twy.tripwithyou_spring.dto.UploadDto;
import com.twy.tripwithyou_spring.dto.UploadImgDto;

import java.util.List;

public interface UploadService {
    List<UploadDto> testList();
    // 디테일
    UploadDto detail(int uploadNo);

    // 게시판 글 등록
    int register(UploadDto upload);
    //게시판 목록
    List<UploadDto> list(PagingDto paging);
    List<UploadDto> list(PagingDto paging, int uptype);
    List<UploadDto> list(PagingDto paging, String userId);
    //게시판 글 수정  //12.26 khs 반환타입을 UploadImgDto List 로 바꿈. controller 에서 호출 시 삭제할 이미지 리스트를 반환함.
    List<UploadImgDto> modify(UploadDto upload, int[] delImgNos);
    //게시글 삭제
    int delete(UploadDto upload);
    //로그인 여부 확인

    //인기 상위 10개 글
    List<UploadDto> popularList(PagingDto paging);
    //12.27 검색 기능
    List<UploadDto> searchingList(String searching,String condition,PagingDto paging,int upType);
}
