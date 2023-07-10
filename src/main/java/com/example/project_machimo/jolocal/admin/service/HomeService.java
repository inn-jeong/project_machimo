package com.example.project_machimo.jolocal.admin.service;

import com.example.project_machimo.jolocal.admin.dto.BoardDto;
import com.example.project_machimo.jolocal.admin.dto.Criteria;

import java.util.ArrayList;

public interface HomeService {
    ArrayList<BoardDto> boardList(Criteria cri);
    public int getTotalCount();
    ArrayList<BoardDto> userQnAList (Criteria cri);
    ArrayList<BoardDto> userBoardList (Criteria cri);
}
