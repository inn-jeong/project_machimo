package com.example.project_machimo.admin.service;

import com.example.project_machimo.admin.dto.BoardDto;
import com.example.project_machimo.admin.dto.Criteria;
import com.example.project_machimo.admin.dto.ProductDto;
import com.example.project_machimo.admin.dto.UsersDto;

import java.util.ArrayList;
import java.util.HashMap;

public interface AdminService {
    ArrayList<UsersDto> adminList(Criteria cri);
    public int getTotalCount();
    void adminDelete(int param );

    UsersDto userView(HashMap<String, String> param);

    ArrayList<BoardDto> boardList(Criteria cri);

    public void boardWrite(BoardDto dto);
    public BoardDto boardView(int board_id);

    public Integer updateHits(int board_id);

    void adminModify(int user_id);

    void boardModify(BoardDto dto);

    void boardDelete(int board_id);
    ArrayList<ProductDto> pList(Criteria cri);

}
