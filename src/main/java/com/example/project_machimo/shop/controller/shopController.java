package com.example.project_machimo.shop.controller;

import com.example.project_machimo.shop.Dto.*;
import com.example.project_machimo.shop.Service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@Slf4j
@Controller
@RequestMapping("/shop")
public class shopController {
    @Autowired
    private ShopService service;


    //모든 상품을 보는 메소드
    @RequestMapping("/allItemView")
    public String allItemView(Model model, @RequestParam(name = "category2", required = false) Integer cId
                                             ,@RequestParam(name = "category1", required = false) Integer cId2
                                            ,@RequestParam(name = "sort", required = false) String sort){

        log.info("@# allItemView");

        // 모든 카테고리와 그에 해당하는 하위 카테고리를 가져옴
//        ex)categories = '건담','브로마이드'
        ArrayList<CategoryDto> categories = service.getCategories();
        Map<Integer, ArrayList<CategoryDto>> subcategory = new HashMap<>();
        //카테고리를 순회하면서 하위 카테고리 목록을 가져온다.
        for (CategoryDto category : categories) {
//            ex)subcategory 100-'나루토 '
            ArrayList<CategoryDto> subCategories = service.getSubCategories(category.getCId());
//            가져온 값은 맵에 추가하고 상위 카테고리ID를 키로 맵에 저장
            subcategory.put(category.getCId(), subCategories);
        }

        //        전체 상품 갯수를 출력하는 메소드
        Integer countProduct = service.countProduct();

        // 선택한 카테고리에 해당하는 상품들을 가져옴
        List<ItemDto> items;
        //하위카테고리를 가져옴
        if (cId != null) {
            items = service.getProductsBySubcategoryId(cId);
            countProduct = items.size();
//            상위카테고리를 가져옴
        } else if (cId2 != null) {
            items = service.getProductsBycategoryId(cId2);
            countProduct = items.size();
        } else {
//            전체,클릭 없을 시 allItemView()
            items = service.allItemView();
            countProduct = items.size();
        }


//        상품을 정렬하는 메소드
        if (sort != null) {
            items = sortItems(items, sort);
        }
//        정렬하고 나서 남은 상품을 세기
        countProduct = items.size();


//        카테고리 값을 model에 저장 하고 넘어감
        model.addAttribute("categories", categories);
        model.addAttribute("subcategory", subcategory);


//        사용자가 선택한 카테고리 값을 가지고 넘어감
        model.addAttribute("category1", cId);
        model.addAttribute("category2", cId2);

        //사용자가 선택한 정렬방식가지고 넘어감
        model.addAttribute("selectedSort", sort);


        //        model에 item의 값을 주고 넘어감
        model.addAttribute("itemList", items);
        // 상품 갯수를 model에 저장하고 넘어감
        model.addAttribute("countProduct", countProduct);
        return "shop";
    }

    // 아이템 리스트를 정렬하는 메소드
    private List<ItemDto> sortItems(List<ItemDto> items, String sort) {
        switch (sort) {
            case "popularity":
                // 조회수로 정렬
                Collections.sort(items, Comparator.comparing(ItemDto::getPHit).reversed());
                break;
            case "newest":
                // 최신순으로 정렬
                Collections.sort(items, Comparator.comparing(ItemDto::getPCreatedAt).reversed());
                break;
            case "interest":
                Collections.sort(items, Comparator.comparing(ItemDto::getPLike).reversed());
                break;
            case "auction_p":
                //경매 상품
                //경매 상품 필터링 (P_sale_type이 0인 상품만 출력)
                items.removeIf(item -> item.getPSaleType() != 0);
                break;
            case "auction_pp":
                //경매 가격 낮은 순
                //경매 상품 필터링 후 가격 낮은 순 으로 정렬
                items.removeIf(item -> item.getPSaleType() != 0);
                Collections.sort(items, Comparator.comparing(ItemDto::getPBPrice));
                break;
            case "auction_ppd":
                //경매 가격 높은 순
                items.removeIf(item -> item.getPSaleType() != 0);
                Collections.sort(items, Comparator.comparing(ItemDto::getPBPrice).reversed());
                break;
            case "auction_n":
                //일반 상품
                //일반 상품 필터링 (P_sale_type이 1인 상품만 출력)
                items.removeIf(item -> item.getPSaleType() != 1);
                break;
            case "auction_np":
                //일반 상품 가격 낮은 순
                // 반 상품 필터링 후 가격 낮은 순 으로 정렬
                items.removeIf(item -> item.getPSaleType() != 1);
                Collections.sort(items, Comparator.comparing(ItemDto::getPDirect));
                break;
            case "auction_npd":
                //일반 상품 가격 높은 순
                items.removeIf(item -> item.getPSaleType() != 1);
                Collections.sort(items, Comparator.comparing(ItemDto::getPDirect).reversed());
                break;

        }
        return items;
    }
}

