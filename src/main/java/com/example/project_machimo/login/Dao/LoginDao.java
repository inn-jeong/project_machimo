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
    public void socialUserInsert(HashMap<String, String> param);
    public UsersDto findUserId(String uSocial);
    public UsersDto findMemPhone(String uPhone);
    public UsersDto findMemEmail(String uEmail);
    public void updatePassword(String uId, String uPassword);
}
