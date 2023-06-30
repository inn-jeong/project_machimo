package com.example.project_machimo.shop.controller;

import com.example.project_machimo.shop.Dto.*;
import com.example.project_machimo.shop.Service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

            // 상품 정보 변환 작업 수행
            ItemDto item = changeItem(product);

            // 상품 정보에 추가 작업 수행(닉네임,이미지)
            addInformation(item);

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

    // ProductDto를 ItemDto로 변환하는 메소드
    private ItemDto changeItem(ProductDto product) {
        ItemDto item = new ItemDto();
        // 상품 정보 변환 작업 수행
        item.setProduct_id(product.getProduct_id());
        item.setUser_id(product.getUser_id());
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
        item.setC_id2(product.getC_id2());

        return item;
    }

    // 상품에 대한 추가 정보를 조회하고 ItemDto에 추가하는 메소드
    private void addInformation(ItemDto item) {
        //닉네임 추가하기
        int userId = item.getUser_id();
        ArrayList<UsersDto> nicknames = service.findNickName(userId);
        if (!nicknames.isEmpty()) {
            item.setU_nickname(nicknames.get(0).getU_nickname());
        }
        //이미지 추가하기
        int productId = item.getProduct_id();
        ArrayList<ImgDto> subImages = service.viewImage(productId);
        if (!subImages.isEmpty()) {
            item.setI_sub_image(subImages.get(0).getI_sub_img());
        }
        //좋아요 추가하기
        ArrayList<WishlistDto> wishLike = service.wishLike(productId);
        if (!wishLike.isEmpty()) {
            item.setWish_like(wishLike.get(0).getWish_like());
        }
    }
    @RequestMapping("/allItemView")
    public String shopPage(Model model) {
        ArrayList<CategoryDto> categories = service.getCategories();
        model.addAttribute("categories", categories);
        return "shop";
    }

    @RequestMapping("/allItemView")
    public String categoryPage(@RequestParam("categoryId") int categoryId, Model model) {
        ArrayList<CategoryDto> categories = service.getCategories();
        ArrayList<CategoryDto> subCategories = service.getSubCategories(categoryId);
        model.addAttribute("categories", categories);
        model.addAttribute("subCategories", subCategories);
        return "shop";
    }

}

