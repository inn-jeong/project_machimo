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
    void adminDelete(int param );

    UsersDto userView(HashMap<String, String> param);

    ArrayList<BoardDto> boardList(Criteria cri);

    public void boardWrite(BoardDto dto);
    public BoardDto boardView(int boardId);

    public Integer updateHits(int boardId);

    void adminModify(int userId);

    void boardModify(BoardDto dto);

    void boardDelete(int boardId);
    ArrayList<ProductDto> pList(Criteria cri);

    public void save(ProductDto dto) throws IOException;

    void updateStatus(int productInd, int pSalesStatus);
    public void productDelete(int productId);
}
