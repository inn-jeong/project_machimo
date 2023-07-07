package com.example.project_machimo.admin.dao;

import com.example.project_machimo.admin.dto.BoardDto;
import com.example.project_machimo.admin.dto.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface HomeDao {
    ArrayList<BoardDto> boardList(Criteria cri);

    int getTotalCount();
}
