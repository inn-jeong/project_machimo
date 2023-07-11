package com.example.project_machimo.inn_jeong.basket.Controller;

import com.example.project_machimo.inn_jeong.basket.Dto.BasketItemDto;
import com.example.project_machimo.inn_jeong.basket.Service.BasketService;
import com.example.project_machimo.inn_jeong.login.Dto.UsersDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Slf4j
@Controller
@RequestMapping("/basketView")
public class BasketController {
    @Autowired
    private BasketService service;

    //장바구니 페이지
    @RequestMapping("/page")
    public String cart(HttpServletRequest request,HttpSession session, Model model){
//        HttpSession session = request.getSession();
        UsersDto user = (UsersDto) session.getAttribute("user");
        if(user == null){
            return "redirect:/loginT/login?login_try=no";
        }
        Integer userId = user.getUserId();
        log.info("@# basket user_id===>" +userId);

        //장바구니에서 join하여 제품 목록 조회
        ArrayList<BasketItemDto> items = service.getBasketItems(userId);
        model.addAttribute("basketItems",items);
        model.addAttribute("type","basket");
        return "mypage/mypage";
    }

    //장바구니에서 제품 삭제(ajax)
    @RequestMapping("/deleteItem")
    @ResponseBody
    public String deleteItem(HttpServletRequest request, Model model){
        //ajax로 productId를 받음
        Integer productId = Integer.valueOf(request.getParameter("product_id"));
        HttpSession session = request.getSession();
        Integer userId = ((UsersDto) session.getAttribute("user")).getUserId();
        //해당 유저의 장바구니에서 제품을 삭제
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
