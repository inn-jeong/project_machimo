package com.example.project_machimo.main.Controller;

import com.example.project_machimo.main.Dto.ItemDto;
import com.example.project_machimo.main.Service.MainService;
import com.example.project_machimo.shop.Service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Slf4j
@Controller
@RequestMapping("/main")
public class MainController {
    @Autowired
    private MainService service;

    @RequestMapping("/page")
    public String maiinPage(Model model){
        log.info("@# MainPage");

        ArrayList<ItemDto> newest = service.newestItem();
        ArrayList<ItemDto> popular = service.popularItem();

        model.addAttribute("newestItem",newest);
        model.addAttribute("popularItem",popular);

        return "main";
    }
}
