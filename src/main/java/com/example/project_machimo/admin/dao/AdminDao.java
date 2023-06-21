package com.example.project_machimo.admin.dao;

import com.example.project_machimo.admin.dto.Criteria;
import com.example.project_machimo.admin.dto.UsersDto;

import java.util.ArrayList;

public interface AdminDao {
    ArrayList<UsersDto> adminList(Criteria cri);
    public int getTotalCount();
    void adminDelete(int user_id);
}
