package com.example.project_machimo.admin.controller;

import com.example.project_machimo.admin.dto.Criteria;
import com.example.project_machimo.admin.dto.PageDto;
import com.example.project_machimo.admin.dto.UsersDto;
import com.example.project_machimo.admin.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

@Controller
@Slf4j
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService service;

    //사용자관리
    @RequestMapping("/adminList")
    public String adminList(Criteria cri, Model model){
        log.info("@# controller adminList start");
        model.addAttribute("adminList",service.adminList(cri));
        int total = service.getTotalCount();
        model.addAttribute("pageMaker",new PageDto(total, cri));
        return "admin/adminList";
    }

}
