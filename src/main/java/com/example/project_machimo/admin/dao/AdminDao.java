package com.example.project_machimo.admin.dao;

import com.example.project_machimo.admin.dto.BoardDto;
import com.example.project_machimo.admin.dto.Criteria;
import com.example.project_machimo.admin.dto.UsersDto;

import java.util.ArrayList;
import java.util.HashMap;

public interface AdminDao {
    ArrayList<UsersDto> adminList(Criteria cri);
    public int getTotalCount();
    public void adminDelete(int user_id);

    UsersDto userView(HashMap<String, String> param);

    ArrayList<BoardDto> BoardList(Criteria cri);

    public Integer updateHits(Integer board_id);

    BoardDto boardView(HashMap<String, Object> param);
    public void write(HashMap<String,Object> param);

    void adminModify(int user_id);

    void boardWrite(HashMap<String, Object> param);
}
