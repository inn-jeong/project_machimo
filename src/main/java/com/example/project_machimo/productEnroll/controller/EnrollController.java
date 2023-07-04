package com.example.project_machimo.productEnroll.controller;


import com.example.project_machimo.review.dto.CommentVO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/productEnroll")
public class EnrollController {

    @RequestMapping("/jusoPopup")
//    public String jusoPopup(@RequestParam HashMap<String,String> param,
//                            HttpServletRequest request,
//                            Model model){
    public String jusoPopup(HttpServletRequest request, Model model){
//  <input type="hidden" id="inputYn" th:value="${inputYn}">
//  <input type="text" id="roadAddrPart1" th:value="${roadAddrPart1}">
//  <input type="text" id="addrDetail" th:value="${addrDetail}">
//  <input type="text" id="zipNo" th:value="${zipNo}">
        model.addAttribute("inputYn", request.getParameter("inputYn"));//확인용

        return "/soldEnrollForm/jusoPopup";
    }

}
