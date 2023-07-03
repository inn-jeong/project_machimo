package com.example.project_machimo.mypage.Service;

import com.example.project_machimo.login.Dto.UserRequestDto;
import com.example.project_machimo.login.Dto.UsersDto;
import com.example.project_machimo.mypage.Dto.PurchaseItem;
import com.example.project_machimo.mypage.Dto.SalesItem;
import com.example.project_machimo.mypage.Dto.UserUpdateRequestDto;
import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface MypageService {
    public ArrayList<PurchaseItem> getPurchaseItems(Integer userId);
    public ArrayList<SalesItem> getSalesItems(Integer userId);
    public int deleteItem(Integer productId);
    public Map<String, String> validateHandling(Errors errors);
    public HashMap<String,String> switchRequestToUser(UserUpdateRequestDto requestDto);
    public int updateUser(HashMap<String, String> param);
    public UsersDto findUser(String uId);
}
