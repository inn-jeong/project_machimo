package com.example.project_machimo.login.Dao;

import com.example.project_machimo.login.Dto.LoginDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface LoginDao {
    public ArrayList<LoginDto> list();
}
