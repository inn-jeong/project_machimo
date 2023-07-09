package com.example.project_machimo.jolocal.admin.service;

import com.example.project_machimo.jolocal.admin.dto.BoardDto;
import com.example.project_machimo.jolocal.admin.dto.Criteria;
import com.example.project_machimo.jolocal.admin.dto.ProductDto;
import com.example.project_machimo.jolocal.admin.dto.UsersDto1;

import java.util.ArrayList;

public interface AdminService {
    ArrayList<UsersDto1> adminList(Criteria cri);
    public int getTotalCount();
    public void adminDelete(int param );
    UsersDto1 userView(int userId);
    UsersDto1 reportView(int userId);

//    public void removeReport(int reportId, int userId, int productId);


    ArrayList<BoardDto> boardList(Criteria cri);

    public void boardWrite(BoardDto dto);
    public BoardDto boardView(int boardId);
//    public void boardModify(BoardDto dto);
    void boardModify(BoardDto dto);

    public void updateHits(int boardId);

    public void Authorization(int userId);


    public void boardDelete(int boardId);

    public BoardDto boardModifyView(int boardId);

    ArrayList<ProductDto> pList(Criteria cri);

    public void productDelete(int productId);

    void removeReport(int userId);

    int loginUser(int boardId);
}
