package com.example.project_machimo.admin.controller;

import com.example.project_machimo.admin.dto.BoardDto;
import com.example.project_machimo.admin.dto.Criteria;
import com.example.project_machimo.admin.dto.PageDto;
import com.example.project_machimo.admin.dto.UsersDto;
import com.example.project_machimo.admin.service.AdminService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashMap;

@Controller
@Slf4j
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService service;



    //관리자 확인//
    private boolean isAdmin(HttpSession session){
        int userId = (int) session.getAttribute("user_id");
        return userId == 1;
    }


    //사용자관리//
    @RequestMapping("/adminList")
    public String adminList(Criteria cri, Model model){
        log.info("@# controller adminList start");
        model.addAttribute("adminList",service.adminList(cri));
        int total = service.getTotalCount();
        model.addAttribute("pageMaker",new PageDto(total, cri));
        return "admin/adminList";
    }

    @GetMapping("/adminDelete/{user_id}")
    public String adminDelete(@PathVariable int user_id){
        service.adminDelete(user_id);
        return "redirect:/admin/adminList";
    }
    @RequestMapping("/adminModify/{user_id}")
    public String adminModify(@PathVariable int user_id){
        System.out.println("@# controller adminModify user_id = "+ user_id );
        service.adminModify(user_id);
        return "redirect:/admin/adminList";
    }

    @RequestMapping("/userView")
    public String userView(@RequestParam HashMap<String, String> param ,Model model){
        UsersDto dto = service.userView(param);
        model.addAttribute("userView",dto);
        model.addAttribute("pageMaker",param);
        return "admin/userView";
    }

    //공지.문의//
    @RequestMapping("/boardList")
    public String boardList(Criteria cri, Model model){
        System.out.println("@# controller boardList");
        model.addAttribute("boardList",service.boardList(cri));
        int total = service.getTotalCount();
        model.addAttribute("pageMaker",new PageDto(total,cri));
        return "admin/boardList";
    }
    @RequestMapping("/boardView")
    public String boardView(@RequestParam HashMap<String,Object>param, Model model){
        System.out.println("@# boardId = "+param.get("board_id"));

        Integer boardId = Integer.parseInt(String.valueOf(param.get("board_id")));
        service.updateHits(boardId);
        BoardDto dto = service.boardView(param);
        model.addAttribute("boardView",dto);
        model.addAttribute("pageMaker",param);
        return "admin/boardView";
    }

    @RequestMapping("/boardWrite")
    public String boardWrite(@RequestParam HashMap<String, Object>param){
        System.out.println("@# controller boardWrite");
        service.boardWrite(param);
        return "/admin/boardWrite";
    }
}
