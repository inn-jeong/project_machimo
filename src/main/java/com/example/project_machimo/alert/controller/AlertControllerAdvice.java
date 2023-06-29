package com.example.project_machimo.alert.controller;

import com.example.project_machimo.alert.service.AlertService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class AlertControllerAdvice {

    private final AlertService service;

    @Autowired
    public AlertControllerAdvice(AlertService service) {
        this.service = service;
    }

    @ModelAttribute("test1")
    public int test1(){


        return 1;
    }


    @ModelAttribute("alretResult")
    public Integer alertCount(HttpSession session){
        //나중에 바꾸기
        Integer userId = (Integer) session.getAttribute("user");
        return service.alertResult(userId);
    }







}
