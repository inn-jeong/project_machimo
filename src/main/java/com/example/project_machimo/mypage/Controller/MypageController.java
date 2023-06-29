package com.example.project_machimo.mypage.Controller;

import com.example.project_machimo.basket.Service.BasketService;
import com.example.project_machimo.login.Dto.UsersDto;
import com.example.project_machimo.mypage.Service.MypageService;
import com.example.project_machimo.mypage.dto.PurchaseItem;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/mypage")
@Slf4j
@Controller
public class MypageController {
    @Autowired
    private MypageService service;

    @RequestMapping("/mypage_page")
    public String mypage(HttpSession session, Model model){
        UsersDto user = (UsersDto)session.getAttribute("user");
        if(user == null){
            return "redirect:/loginT/login?login_try=no";
        }
        Integer user_id = user.getUser_id();
        log.info("@# basket user_id===>" +user_id);
        List<PurchaseItem> items = service.getPurchaseItems(user_id);
        model.addAttribute("items",items);
        return "mypage/mypage";
    }

}
