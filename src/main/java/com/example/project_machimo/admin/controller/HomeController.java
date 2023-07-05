package com.example.project_machimo.admin.controller;

import com.example.project_machimo.admin.dto.Criteria;
import com.example.project_machimo.admin.dto.PageDto;
import com.example.project_machimo.admin.dto.UsersDto;
import com.example.project_machimo.admin.service.HomeService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Slf4j
@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private HomeService hservice;
    @Autowired
    private HttpSession session;

    @GetMapping("/boardList")
    public String boardList(Criteria cri, Model model){
        System.out.println("@# ==> boardList start");

        //임시 user session 생성
        UsersDto user = new UsersDto();
        user.setUserId(0); //user
        session.setAttribute("user",user);

        int total = hservice.getTotalCount();
        model.addAttribute("boardList",hservice.boardList(cri));
        model.addAttribute("pageMaker",new PageDto(total, cri));
        return "userBoardList";
    }


}
