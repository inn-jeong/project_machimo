package com.example.project_machimo.login.Service;


import com.example.project_machimo.login.Dto.UsersDto;
import com.example.project_machimo.login.Dto.UserRequestDto;
import org.springframework.validation.Errors;

import java.util.HashMap;
import java.util.Map;

public interface LoginService {
    public UsersDto findUser(HashMap<String, String> param);
    public int loginYn(HashMap<String, String> param);
    public void userInsert(HashMap<String, String> param);
    public Map<String, String> validateHandling(Errors errors);
//    public MemDto switchMem(MemberRequestDto requestDto);
    public HashMap<String,String> switchRequestToUser(UserRequestDto requestDto);
    public UserRequestDto convertNaver(UsersDto usersDto);
    public UserRequestDto convertKakao(UsersDto usersDto);
    public UsersDto findUserId(String u_social);
    public UsersDto findMemPhone(String u_phone);
    public UsersDto findMemEmail(String u_email);
}

