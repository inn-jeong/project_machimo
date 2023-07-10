package com.example.project_machimo.jolocal.admin.service;

import com.example.project_machimo.jolocal.admin.dto.BoardDto;
import com.example.project_machimo.jolocal.admin.dto.Criteria;

import java.util.ArrayList;

public interface HomeService {
    public int getTotalCount();
    ArrayList<BoardDto> userBoardList (Criteria cri);
    public void updateHits(int boardId);

//    ArrayList<BoardDto> boardList(Criteria cri);
//    ArrayList<BoardDto> userQnAList (Criteria cri);
}
