package com.example.project_machimo.admin.dao;

import com.example.project_machimo.admin.dto.BoardDto;
import com.example.project_machimo.admin.dto.Criteria;
import com.example.project_machimo.admin.dto.ProductDto;
import com.example.project_machimo.admin.dto.UsersDto;

import java.util.ArrayList;
import java.util.HashMap;

public interface AdminDao {

    public int getTotalCount();

    public Integer updateHits(Integer boardId);


    //user//
    ArrayList<UsersDto> adminList(Criteria cri);

    UsersDto userView(int userId);

    public void adminDelete(int userId);

    void adminModify(int userId);

    void removeReport(int reportId, int userId, int productId);

    //공지,문의//
    ArrayList<BoardDto> BoardList(Criteria cri);

    BoardDto boardView(int boardId);

    void boardWrite(BoardDto dto);

    void boardWrite(HashMap<String, Object> param);

    void boardDelete(int boardId);

    void boardModify(BoardDto dto);

    BoardDto boardModifyView(int boardId);


    //제품관리//
    ArrayList<ProductDto> pList(Criteria cri);

    void updateStatus(int ProductId, int PSalesStatus);

    void productDelete(int productId);

}