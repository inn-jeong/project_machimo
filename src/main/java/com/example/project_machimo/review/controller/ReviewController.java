package com.example.project_machimo.review.controller;

import com.example.project_machimo.review.dto.ReviewDto;
import com.example.project_machimo.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

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
//        model.addAttribute("list",list);
        model.addAttribute("list",list);
        return "list";
    }
}
