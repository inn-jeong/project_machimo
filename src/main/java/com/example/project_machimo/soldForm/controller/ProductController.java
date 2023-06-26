//package com.example.project_machimo.soldForm.controller;
//
//import com.example.project_machimo.soldForm.dto.ProductDto;
//import com.example.project_machimo.soldForm.service.ProductService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//
//@Slf4j
//@Controller
////@RestController
////@RequiredArgsConstructor
//@RequestMapping("/soldForm")
//public class ProductController {
//    @Autowired
//    private ProductService service;
//
////    @RequestMapping("/list")
//    @GetMapping("/list")
//    public String list(Model model) {
//        log.info("@# list");
//        ArrayList<ProductDto> list = service.list();
//        model.addAttribute("list",list);
////        return "list";
//        return "soldForm/list";
//    }
////    @RequestMapping("/write")
//    @GetMapping("/write")
//
//    public String write(@RequestParam HashMap<String, String> param) {
//        log.info("@# write");
//        service.write(param);
//        return "redirect:list";
//    }
//
////    @RequestMapping("/write_view")
//    @GetMapping("/write_view")
//    public String write_view() {
//        log.info("@# write_view");
//
////        return "write_view";
//        return "soldForm/write_view";
//    }
//
//
////    @RequestMapping("/content_view")
//    @GetMapping("/content_view")
//    public String content_view(@RequestParam HashMap<String, String> param, Model model) {
//        log.info("@# content_view");
////        ProductDto dto = service.contentView(param);
////        model.addAttribute("content_view",dto);
////        return "content_view";
//        return "soldForm/content_view";
//    }
////    @RequestMapping("/modify")
//    @GetMapping("/modify")
//    public String modify(@RequestParam HashMap<String, String> param) {
//        log.info("@# modify");
//        service.modify(param);
//        return "redirect:list";
//    }
////    @RequestMapping("/delete")
//    @GetMapping("/delete")
//    public String delete(@RequestParam HashMap<String, String> param) {
//        log.info("@# delete");
//
//        service.delete(param);
//
//        return "redirect:list";
//    }
//}
