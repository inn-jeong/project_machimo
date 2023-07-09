package com.example.project_machimo.jolocal.admin.dao;

import com.example.project_machimo.inn_jeong.login.Dto.UsersDto;
import com.example.project_machimo.jolocal.admin.dto.*;

import java.util.ArrayList;
import java.util.HashMap;

public interface AdminDao {

    public int getTotalCount();



    //user//
    ArrayList<UsersDto1> adminList(Criteria cri);

    UsersDto1 userView(int userId);

    public void adminDelete(int userId);

    void Authorization(int userId);

    UsersDto1 reportView(int userId);


    //공지,문의//
    ArrayList<BoardDto> BoardList(Criteria cri);

    BoardDto boardView(int boardId);

    void boardWrite(BoardDto dto);

    void boardWrite(HashMap<String, Object> param);

    void boardDelete(int boardId);

//    void boardModify(BoardDto dto);
    void boardModify(BoardDto dto);

    BoardDto boardModifyView(int boardId);
    public Integer updateHits(Integer boardId);


    //제품관리//
    ArrayList<ProductDto> pList(Criteria cri);

    int updateStatus(StatusDto dto);

    void productDelete(int productId);

    void removeReport(int userId);

    int loginUser(int boardId);
}