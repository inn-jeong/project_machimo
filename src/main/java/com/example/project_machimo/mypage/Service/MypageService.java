package com.example.project_machimo.mypage.Service;

import com.example.project_machimo.mypage.Dto.PurchaseItem;
import com.example.project_machimo.mypage.Dto.SalesItem;

import java.util.ArrayList;

public interface MypageService {
    public ArrayList<PurchaseItem> getPurchaseItems(Integer userId);
    public ArrayList<SalesItem> getSalesItems(Integer userId);
    public int deleteItem(Integer productId);
}
