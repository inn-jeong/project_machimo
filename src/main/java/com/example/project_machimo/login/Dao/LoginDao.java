package com.example.project_machimo.login.Dao;

import com.example.project_machimo.login.Dto.LoginDto;
import com.example.project_machimo.login.Dto.MemDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;

@Mapper
public interface LoginDao {
//    public ArrayList<LoginDto> loginYn(HashMap<String, String> param);
    public MemDto findMem(HashMap<String, String> param);
    public void memberInsert(HashMap<String, String> param);
}
