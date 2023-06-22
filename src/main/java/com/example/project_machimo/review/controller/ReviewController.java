package com.example.project_machimo.review.controller;

import com.example.project_machimo.review.dto.ReviewDto;
import com.example.project_machimo.review.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        int total = service.getTotalCount();//페이징구현아직안함

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
        System.out.println("param.get(\"reviewContent\") = " + param.get("reviewContent"));
        service.modify(param);
        return "redirect:list";
    }

    @RequestMapping("/modify_view")
    public String modify_view(@RequestParam("reviewId") String reviewId ,Model model) {
        ReviewDto reviewDto = service.modify_view(reviewId);
        model.addAttribute("content_view",reviewDto);
        return "review/modify_view";
    }

//    @RequestMapping("/modify")
//    public String modify(@RequestParam("reviewId") int reviewId, HashMap<String, String> param, Model model) {
//        log.info("@# modify");
//        ReviewDto dto = service.contentView(reviewId, param);
//        model.addAttribute("content_view", dto);
//        return "review/modify_view";
//    }



    @RequestMapping("/delete")
//    public String delete(@RequestParam("reviewId") HashMap<String, String> param) {
    public String delete(@RequestParam("reviewId") String reviewId) {
        log.info("@# delete");

        service.delete(reviewId);

        return "redirect:list";
    }
}
