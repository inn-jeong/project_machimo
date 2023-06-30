package com.example.project_machimo.basket.Controller;

import com.example.project_machimo.basket.Dto.BasketDto;
import com.example.project_machimo.basket.Dto.BasketItemDto;
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
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/basket")
public class BasketController {
    @Autowired
    private BasketService service;

    @RequestMapping("/page")
    public String cart(HttpServletRequest request,HttpSession session, Model model){
//        HttpSession session = request.getSession();
        UsersDto user = (UsersDto) session.getAttribute("user");
        if(user == null){
            return "redirect:/loginT/login?login_try=no";
        }
        Integer userId = user.getUserId();
        log.info("@# basket user_id===>" +userId);
        ArrayList<BasketDto> basket = service.getBasket(userId);
//        List<ProductsDto> basketItems = service.getBasketItems(basket);
        log.info("@# basket basket=====>"+basket);
        if(!basket.isEmpty()){
            List<BasketItemDto> basketItems = service.getBasketItems(basket);
            if(!basketItems.isEmpty()){
                log.info("@# basket product_id ===>"+basketItems.get(0).getProductId());
                log.info("@# basket p_name ===>"+basketItems.get(0).getPName());
                log.info("@# basket p_direct ===>"+basketItems.get(0).getPDirect());
                model.addAttribute("basketItems",basketItems);
            }
        }
        return "basket/basket";
    }

    @RequestMapping("/deleteItem")
    @ResponseBody
    public String deleteItem(HttpServletRequest request, Model model){
        Integer productId = Integer.valueOf(request.getParameter("product_id"));
        HttpSession session = request.getSession();
        Integer userId = ((UsersDto) session.getAttribute("user")).getUserId();
        int deleteResult = service.deleteItem(userId,productId);
        String result;
        if(deleteResult == 1){
            result = "success";
        }else{
            result = "fail";
        }
        return result;
    }
}
