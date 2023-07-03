package com.example.project_machimo.admin.service;

import com.example.project_machimo.admin.dto.BoardDto;
import com.example.project_machimo.admin.dto.Criteria;
import com.example.project_machimo.admin.dto.ProductDto;
import com.example.project_machimo.admin.dto.UsersDto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public interface AdminService {
    ArrayList<UsersDto> adminList(Criteria cri);
    public int getTotalCount();
    public void adminDelete(int param );

    UsersDto userView(HashMap<String, String> param);

    public void removeReport(int reportId, int userId, int productId);


    ArrayList<BoardDto> boardList(Criteria cri);

    public void boardWrite(BoardDto dto);
    public BoardDto boardView(int boardId);

    public void updateHits(int boardId);

    public void adminModify(int userId);


    public void boardDelete(int boardId);

    public BoardDto boardModifyView(int boardId);
    public void boardModify(BoardDto dto);

    ArrayList<ProductDto> pList(Criteria cri);

    public void updateStatus(int productInd, int pSalesStatus);
    public void productDelete(int productId);
}
