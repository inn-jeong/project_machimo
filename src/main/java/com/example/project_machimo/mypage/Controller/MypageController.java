package com.example.project_machimo.mypage.Controller;

import com.example.project_machimo.basket.Service.BasketService;
import com.example.project_machimo.mypage.Service.MypageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/mypage")
@Slf4j
@Controller
public class MypageController {
    @Autowired
    private MypageService service;

    @RequestMapping("/mypage_page")
    public String mypage(){
        return "mypage/mypage";
    }

}
