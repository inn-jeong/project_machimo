package com.example.project_machimo.admin.service;

import com.example.project_machimo.admin.dto.BoardDto;
import com.example.project_machimo.admin.dto.Criteria;
import com.example.project_machimo.admin.dto.UsersDto;

import java.util.ArrayList;
import java.util.HashMap;

public interface AdminService {
    ArrayList<UsersDto> adminList(Criteria cri);
    public int getTotalCount();
    void adminDelete(int param );

    UsersDto userView(HashMap<String, String> param);

    ArrayList<BoardDto> boardList(Criteria cri);

    public Integer updateHits(Integer board_id);
    BoardDto boardView(HashMap<String,Object>param);

    void adminModify(int user_id);
    public void boardWrite(HashMap<String,Object>param);
}
