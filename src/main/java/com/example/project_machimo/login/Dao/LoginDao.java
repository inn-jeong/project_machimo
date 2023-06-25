package com.example.project_machimo.login.Dao;

import com.example.project_machimo.login.Dto.MailDto;
import com.example.project_machimo.login.Dto.UsersDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface LoginDao {
//    public ArrayList<LoginDto> loginYn(HashMap<String, String> param);
    public UsersDto findUser(HashMap<String, String> param);
    public void userInsert(HashMap<String, String> param);
    public UsersDto findUserId(String u_social);
    public UsersDto findMemPhone(String u_phone);
    public UsersDto findMemEmail(String u_email);
    public void updatePassword(String u_id, String u_password);
}
