package com.example.project_machimo.sqlLogTest;

import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface UserDao {
    ArrayList<UserDTO> getEmp();
}
