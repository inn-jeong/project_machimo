package com.example.project_machimo.review.controller;

import com.example.project_machimo.review.dto.ReviewDto;
import com.example.project_machimo.review.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

@Slf4j
@Controller
//@RestController
//@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    private ReviewService service;

    @RequestMapping("/list")
    public String list(Model model) {
        log.info("@# list");
        ArrayList<ReviewDto> list = service.list();
        model.addAttribute("list",list);
//        return "list";
        return "review/list";
    }
    @RequestMapping("/write_view")
    public String writeView() {
        log.info("@# writeView");
        return "review/write_view";
    }

    @RequestMapping("/write")
    public String write(@RequestParam HashMap<String, String> param) {
        log.info("@# write");
        service.write(param);
        return "redirect:list";
    }

    @RequestMapping("/content_view")
    public String contentView(@RequestParam HashMap<String, String> param, Model model) {
        log.info("@# contentView");
        ReviewDto dto = service.contentView(param);
        model.addAttribute("content_view", dto);
        return "review/content_view";
    }
    @RequestMapping("/modify")
    public String modify(@RequestParam HashMap<String, String> param) {
        log.info("@# modify");
        service.modify(param);
        return "redirect:list";
    }
    @RequestMapping("/delete")
    public String delete(@RequestParam HashMap<String, String> param) {
        log.info("@# delete");

        service.delete(param);

        return "redirect:list";
    }
}
