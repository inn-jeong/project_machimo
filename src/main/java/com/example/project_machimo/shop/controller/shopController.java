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
    public String allItemView(Model model, @RequestParam(name = "sort", required = false) String sort
                                    , @RequestParam(name = "category2", required = false) Integer cId
                                    ,@RequestParam(name = "category1", required = false) Integer cId2){
        log.info("@# allItemView");

        // 모든 카테고리와 그에 해당하는 하위 카테고리를 가져옴
        ArrayList<CategoryDto> categories = service.getCategories();
        Map<Integer, ArrayList<CategoryDto>> subcategory = new HashMap<>();
        for (CategoryDto category : categories) {
            ArrayList<CategoryDto> subCategories = service.getSubCategories(category.getCId());
            subcategory.put(category.getCId(), subCategories);
        }


        // 선택한 카테고리에 해당하는 상품들을 가져옴
//        List<ProductDto> products;
        List<ItemDto> items;
        //하위카테고리를 가져옴
        if (cId != null) {
            items = service.getProductsBySubcategoryId(cId);
//            상위카테고리를 가져옴
        } else if (cId2 != null) {
            items = service.getProductsBycategoryId(cId2);
        } else {
//            전체,클릭 없을 시 allItemView()
            items = service.allItemView();
        }

        // 상품 정보를 변환하고 추가 정보를 조회하여 ItemDto 리스트로 변환
        for (ItemDto item : items) {
//            ItemDto item = changeItem(product);
            addInformation(item);
            log.info("@@# addInformation"+item);
        }

//        상품을 정렬하는 메소드
        if (sort != null) {
            items = sortItems(items, sort);
        }

//        카테고리 값을 model에 저장 하고 넘어감
        model.addAttribute("categories", categories);
        model.addAttribute("subcategory", subcategory);

//        model에 사용자가 선택한 옵션을 들고 넘어감
        model.addAttribute("selectedSort", sort);
        model.addAttribute("selectedCategory", cId);//카테고리 선택 정렬

        //        model에 item의 값을 주고 넘어감
        model.addAttribute("itemList", items);
        return "shop";
    }

//    @RequestMapping("/sortItems")
//    public String sortItems(@RequestParam("categoryId") int categoryId, @RequestParam("sortOption") String sortOption, Model model) {
//        // 카테고리 필터링
//        List<ItemDto> filteredItems = itemService.getItemsByCategoryId(categoryId);
//
//        // 정렬 기능 수행
//        List<ItemDto> sortedItems;
//        switch (sortOption) {
//            case "popular":
//                sortedItems = sortItemsByPopularity(filteredItems);
//                break;
//            case "latest":
//                sortedItems = sortItemsByLatest(filteredItems);
//                break;
//            // 다른 정렬 옵션에 대한 처리 추가
//            default:
//                sortedItems = filteredItems; // 정렬 옵션이 없을 경우, 필터링된 아이템 리스트를 그대로 사용
//        }
//
//        // 정렬된 아이템 리스트를 모델에 추가
//        model.addAttribute("items", sortedItems);
//
//        return "shop";
//    }

//    // ProductDto를 ItemDto로 변환하는 메소드
//    private ItemDto changeItem(ProductDto product) {
//        ItemDto item = new ItemDto();
//        // 상품 정보 변환 작업 수행
//        item.setProduct_id(product.getProduct_id());
//        item.setUser_id(product.getUser_id());
//        item.setC_id(product.getC_id());
//        item.setP_name(product.getP_name());
//        item.setP_info(product.getP_info());
//        item.setP_direct(product.getP_direct());
//        item.setP_dur(product.getP_dur());
//        item.setP_b_price(product.getP_b_price());
//        item.setP_created_at(product.getP_created_at());
//        item.setP_updated_at(product.getP_updated_at());
//        item.setP_hit(product.getP_hit());
//        item.setP_sales_status(product.getP_sales_status());
//        item.setP_sale_type(product.getP_sale_type());
//        item.setP_account(product.getP_account());
//        item.setP_address(product.getP_address());
//        item.setP_bank(product.getP_bank());
//        item.setC_id2(product.getC_id2());
//
//        return item;
//    }

    // 상품에 대한 추가 정보를 조회하고 ItemDto에 추가하는 메소드
    private void addInformation(ItemDto item) {
        //닉네임 추가하기
//        int userId = item.getUser_id();
//        ArrayList<UsersDto> nicknames = service.findNickName(userId);
//        if (!nicknames.isEmpty()) {
//            item.setU_nickname(nicknames.get(0).getU_nickname());
//        }
        //이미지 추가하기
//        int productId = item.getProduct_id();
//        ArrayList<ImgDto> subImages = service.viewImage(productId);
//        if (!subImages.isEmpty()) {
//            item.setI_sub_image(subImages.get(0).getI_sub_img());
//        }
        //좋아요 추가하기
        int productId = item.getProductId();
        ArrayList<WishlistDto> wishLike = service.wishLike(productId);
        if (!wishLike.isEmpty()) {
            item.setWishLike(wishLike.get(0).getWishLike());
        }
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
                Collections.sort(items, Comparator.comparing(ItemDto::getWishLike).reversed());
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

