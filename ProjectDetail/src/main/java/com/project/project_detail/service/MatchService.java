package com.project.project_detail.service;

import com.project.project_detail.dto.MatchDto;

import java.util.List;

public interface MatchService {
    List<MatchDto> listAllTest();
    int match(MatchDto match);
}
