package com.example.project_machimo.admin.service;

import com.example.project_machimo.admin.dto.BoardDto;
import com.example.project_machimo.admin.dto.Criteria;

import java.util.ArrayList;

public interface HomeService {
    ArrayList<BoardDto> boardList(Criteria cri);
    public int getTotalCount();
}
