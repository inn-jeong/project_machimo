package com.example.project_machimo.login.Service;


import com.example.project_machimo.admin.dto.UsersDto;
import com.example.project_machimo.login.Dto.MemDto;
import com.example.project_machimo.login.Dto.MemberRequestDto;
import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface LoginService {
    public MemDto findMem(HashMap<String, String> param);
    public int loginYn(HashMap<String, String> param);
    public void memberInsert(HashMap<String, String> param);
    public Map<String, String> validateHandling(Errors errors);
//    public MemDto switchMem(MemberRequestDto requestDto);
    public HashMap<String,String> switchRequestToMem(MemberRequestDto requestDto);
    public MemberRequestDto switchMemToRequest(MemDto memDto);
    public MemberRequestDto switchMemToRequest2(MemDto memDto);
    public MemDto findMemPhone(String u_phone);
    public MemDto findMemEmail(String u_email);
}

