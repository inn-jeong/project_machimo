package com.example.project_machimo.jomuragi.shop.Service;

import com.example.project_machimo.jomuragi.shop.Dto.*;

import java.util.ArrayList;
import java.util.List;

public interface ShopService {
    public ArrayList<ItemDto> allItemView();
    public ArrayList<UsersDto> findNickName(int userId);
    public ArrayList<ImgDto> viewImage(int productId);
    public ArrayList<WishlistDto> wishLike(int productId);
    public ArrayList<CategoryDto> getCategories();
    public ArrayList<CategoryDto> getSubCategories(Integer cId2);
//    카테고리 메소드인데 List타입을 사용한 이유는 해당 ID가 상품들을 여러개 조회 할 수 있기 때문에
//    해당 상품이 없을땐 빈 리스트기 때문에 LIST를 사용함
    List<ItemDto> getProductsBySubcategoryId(int cId);
    List<ItemDto> getProductsBycategoryId(int cId2);
    ArrayList<CategoryDto> getCategoryById(Integer cId);
}
