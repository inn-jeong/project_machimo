package com.example.project_machimo.admin.service;

import com.example.project_machimo.admin.dto.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public interface AdminService {
    ArrayList<UsersDto> adminList(Criteria cri);
    public int getTotalCount();
    public void adminDelete(int param );

    UsersDto userView(int userId);

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

//    public int auctionsStatus (ProductId, PSalesStatus, p_sale_type);

    public void productDelete(int productId);
}
