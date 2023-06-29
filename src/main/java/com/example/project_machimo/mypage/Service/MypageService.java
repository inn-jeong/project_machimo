package com.example.project_machimo.mypage.Service;

import com.example.project_machimo.mypage.dto.PurchaseItem;

import java.util.ArrayList;

public interface MypageService {
    public ArrayList<PurchaseItem> getPurchaseItems(Integer user_id);
}
