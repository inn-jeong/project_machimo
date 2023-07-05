package com.example.project_machimo.admin.controller;

import com.example.project_machimo.admin.dto.UsersDto;
import com.example.project_machimo.admin.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@Slf4j
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService service;
    @GetMapping("/list")
    public String list(Model model){
        log.info("@# list");
        ArrayList<UsersDto> list = service.list();
        model.addAttribute("list",list);
        return "admin/list";
    }
}
