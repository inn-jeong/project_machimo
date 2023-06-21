package com.example.project_machimo.admin.service;

import com.example.project_machimo.admin.dto.Criteria;
import com.example.project_machimo.admin.dto.UsersDto;

import java.util.ArrayList;

public interface AdminService {
    ArrayList<UsersDto> adminList(Criteria cri);
    public int getTotalCount();
    void adminDelete(int param );
}
