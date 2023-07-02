package com.example.project_machimo.alert.controller;

import com.example.project_machimo.alert.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AlertController {

    private final AlertService service;

    @Autowired
    public AlertController(AlertService service) {
        this.service = service;
    }

    @RequestMapping("test23")
    public String test23(@RequestParam int alId){
        service.checkedFlag(alId);

        return "redirect:test24";
    }

    @RequestMapping("test24")
    public String test24(){
        return "alert/test1";
    }



}
