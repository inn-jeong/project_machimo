package com.example.project_machimo.login.Service;


import com.example.project_machimo.login.Dto.MemDto;

import java.util.ArrayList;
import java.util.HashMap;

public interface LoginService {
    public ArrayList<MemDto> findMem(HashMap<String, String> param);
    public int loginYn(HashMap<String, String> param);
    public void memberInsert(HashMap<String, String> param);
}
