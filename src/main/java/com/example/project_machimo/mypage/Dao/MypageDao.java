package com.example.project_machimo.mypage.Dao;

import com.example.project_machimo.login.Dto.UsersDto;
import com.example.project_machimo.mypage.Dto.PurchaseItem;
import com.example.project_machimo.mypage.Dto.SalesItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;

@Mapper
public interface MypageDao {
    public ArrayList<PurchaseItem> getPurchaseItems(Integer userId);
    public ArrayList<SalesItem> getSalesItems(Integer userId);
    public int deleteItem(Integer productId);
    public int updateUser(HashMap<String, String> param);
    public UsersDto findUser(String uId);
}
