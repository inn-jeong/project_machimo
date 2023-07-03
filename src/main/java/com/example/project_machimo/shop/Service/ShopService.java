package com.example.project_machimo.shop.Service;

import com.example.project_machimo.shop.Dto.*;

import java.util.ArrayList;
import java.util.List;

public interface ShopService {
    public ArrayList<ProductDto> allItemView();
    public ArrayList<UsersDto> findNickName(int user_id);
    public ArrayList<ImgDto> viewImage(int product_id);
    public ArrayList<WishlistDto> wishLike(int product_id);
    public ArrayList<CategoryDto> getCategories();
    public ArrayList<CategoryDto> getSubCategories(Integer c_id);
//    카테고리 메소드인데 List타입을 사용한 이유는 해당 ID가 상품들을 여러개 조회 할 수 있기 때문에
//    해당 상품이 없을땐 빈 리스트기 때문에 LIST를 사용함
    List<ProductDto> getProductsByCategoryId(int c_id2);
}
