package com.example.project_machimo.basket.Controller;

import com.example.project_machimo.basket.Dto.BasketDto;
import com.example.project_machimo.basket.Dto.ProductsDto;
import com.example.project_machimo.basket.Service.BasketService;
import com.example.project_machimo.login.Dto.UsersDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/basket")
public class BasketController {
    @Autowired
    private BasketService service;

    @RequestMapping("/page")
    public String cart(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        UsersDto dto = (UsersDto) session.getAttribute("user");
        Integer user_id = dto.getUser_id();
        log.info("@# basket user_id===>" +user_id);
        ArrayList<BasketDto> basket = service.getBasket(user_id);
        List<ProductsDto> basketItems = service.getBasketItems(basket);
        model.addAttribute("basketItems",basketItems);
        return "basket/basket";
    }
}
