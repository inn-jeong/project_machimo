package com.example.project_machimo.shop.Service;

import com.example.project_machimo.shop.Dto.ImgDto;
import com.example.project_machimo.shop.Dto.ItemDto;
import com.example.project_machimo.shop.Dto.ProductDto;
import com.example.project_machimo.shop.Dto.UsersDto;

import java.util.ArrayList;

public interface ShopService {
    public ArrayList<ProductDto> allItemView();
    public ArrayList<UsersDto> findNickName(int user_id);
    public ArrayList<ImgDto> viewImage(int product_id);
}
