package com.example.project_machimo.jolocal.admin.dao;

import com.example.project_machimo.jolocal.admin.dto.BoardDto;
import com.example.project_machimo.jolocal.admin.dto.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface HomeDao {

    ArrayList<BoardDto> userBoardList(Criteria cri);
    int getTotalCount();
    public Integer updateHits(Integer boardId);

//    ArrayList<BoardDto> boardList(Criteria cri);
//    ArrayList<BoardDto> userQnAList(Criteria cri);

}
