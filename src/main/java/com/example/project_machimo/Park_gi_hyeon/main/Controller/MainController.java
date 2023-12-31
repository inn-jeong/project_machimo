package com.example.project_machimo.Park_gi_hyeon.main.Controller;

import com.example.project_machimo.Park_gi_hyeon.main.Dto.ItemDto;
import com.example.project_machimo.Park_gi_hyeon.main.Service.MainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Slf4j
@Controller
//@RequestMapping("/main")
public class MainController {
    @Autowired
    private MainService service;

    @RequestMapping("/")
    public String mainPage(Model model){
        log.info("@# MainPage");

        ArrayList<ItemDto> newest = service.newestItem();
        ArrayList<ItemDto> popular = service.popularItem();
        ArrayList<ItemDto> fiqure = service.figureItem();
        ArrayList<ItemDto> goods = service.goodsItem();

        model.addAttribute("newestItem",newest);
        model.addAttribute("popularItem",popular);
        model.addAttribute("figureItem",fiqure);
        model.addAttribute("goodsItem",goods);

        return "index";
    }
}
