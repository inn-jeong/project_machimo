package com.example.project_machimo.shop.controller;

import com.example.project_machimo.shop.Dto.*;
import com.example.project_machimo.shop.Service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

@Slf4j
@Controller
@RequestMapping("/shop")
public class shopController {
    @Autowired
    private ShopService service;

    //모든 상품을 보는 메소드
    @RequestMapping("/allItemView")
    public String allItemView(Model model, @RequestParam(name = "sort", required = false) String sort){
        log.info("@# allItemView");
        ArrayList<ProductDto> products = service.allItemView();
        ArrayList<ItemDto> items = new ArrayList<>();


        for (ProductDto product : products) {
            ItemDto item = new ItemDto();
            item.setProducts_id(product.getProducts_id());
            item.setUsers_id(product.getUser_id());
            item.setC_id(product.getC_id());
            item.setP_name(product.getP_name());
            item.setP_info(product.getP_info());
            item.setP_direct(product.getP_direct());
            item.setP_dur(product.getP_dur());
            item.setP_b_price(product.getP_b_price());
            item.setP_created_at(product.getP_created_at());
            item.setP_updated_at(product.getP_updated_at());
            item.setP_hit(product.getP_hit());
            item.setP_sales_status(product.getP_sales_status());
            item.setP_sale_type(product.getP_sale_type());
            item.setP_account(product.getP_account());
            item.setP_address(product.getP_address());
            item.setP_bank(product.getP_bank());

            int userId = product.getUser_id();
            ArrayList<UsersDto> nicknames = service.findNickName(userId);
            if (!nicknames.isEmpty()) {
                item.setU_nickname(nicknames.get(0).getU_nickname());
            }

            int productId = product.getProducts_id();
            ArrayList<ImgDto> subImages = service.viewImage(productId);
            if (!subImages.isEmpty()) {
                item.setI_sub_image(subImages.get(0).getI_sub_img());
            }

            ArrayList<WishlistDto> wishLike = service.wishLike(productId);
            if (!wishLike.isEmpty()){
                item.setWish_like(wishLike.get(0).getWish_like());
            }

            items.add(item);
        }

        if (sort != null) {
            switch (sort) {
                case "popularity":
                    // 조회수로 정렬
                    Collections.sort(items, Comparator.comparing(ItemDto::getP_hit).reversed());
                    break;
                case "newest":
                    // 최신순으로 정렬
                    Collections.sort(items, Comparator.comparing(ItemDto::getP_created_at).reversed());
                    break;
                case "interest":
                    Collections.sort(items, Comparator.comparing(ItemDto::getWish_like).reversed());
                    break;
                case "auction_p":
                    //경매 상품
                    //경매 상품 필터링 (P_sale_type이 0인 상품만 출력)
                    items.removeIf(item -> item.getP_sale_type() != 0);
                    break;
                case "auction_pp":
                    //경매 가격 낮은 순
                    //경매 상품 필터링 후 가격 낮은 순 으로 정렬
                    items.removeIf(item -> item.getP_sale_type() != 0);
                    Collections.sort(items, Comparator.comparing(ItemDto::getP_b_price));
                    break;
                case "auction_ppd":
                    //경매 가격 높은 순
                    items.removeIf(item -> item.getP_sale_type() != 0);
                    Collections.sort(items, Comparator.comparing(ItemDto::getP_b_price).reversed());
                    break;
                case "auction_n":
                    //일반 상품
                    //일반 상품 필터링 (P_sale_type이 1인 상품만 출력)
                    items.removeIf(item -> item.getP_sale_type() != 1);
                    break;
                case "auction_np":
                    //일반 상품 가격 낮은 순
                    // 반 상품 필터링 후 가격 낮은 순 으로 정렬
                    items.removeIf(item -> item.getP_sale_type() != 1);
                    Collections.sort(items, Comparator.comparing(ItemDto::getP_direct));
                    break;
                case "auction_npd":
                    //일반 상품 가격 높은 순
                    items.removeIf(item -> item.getP_sale_type() != 1);
                    Collections.sort(items, Comparator.comparing(ItemDto::getP_direct).reversed());
                    break;
            }
        }

//        model에 item의 값을 주고 넘어감
        model.addAttribute("itemList", items);
//        model에 사용자가 선택한 옵션을 들고 넘어감
        model.addAttribute("selectedSort", sort);
        return "shop";
    }
}
