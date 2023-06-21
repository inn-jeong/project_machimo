package com.example.project_machimo.shop.controller;

import com.example.project_machimo.shop.Dto.ImgDto;
import com.example.project_machimo.shop.Dto.ItemDto;
import com.example.project_machimo.shop.Dto.ProductDto;
import com.example.project_machimo.shop.Dto.UsersDto;
import com.example.project_machimo.shop.Service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Slf4j
@Controller
@RequestMapping("shop")
public class shopController {
    @Autowired
    private ShopService service;

    @RequestMapping("/allItemView")
    public String allItemView(Model model){
        log.info("@# allItemView");
        ArrayList<ProductDto> products = service.allItemView();
        ArrayList<ItemDto> items = new ArrayList<>();

        for (ProductDto product : products) {
            ItemDto item = new ItemDto();
            item.setProducts_id(product.getProducts_id());
            item.setUsers_id(product.getUsers_id());
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

            int userId = product.getUsers_id();
            ArrayList<UsersDto> nicknames = service.findNickName(userId);
            if (!nicknames.isEmpty()) {
                item.setU_nickname(nicknames.get(0).getU_nickname());
            }

            int productId = product.getProducts_id();
            ArrayList<ImgDto> subImages = service.viewImage(productId);
            if (!subImages.isEmpty()) {
                item.setI_sub_image(subImages.get(0).getI_sub_image());
            }

            items.add(item);
        }

        model.addAttribute("itemList", items);
        return "shop";
    }
}
