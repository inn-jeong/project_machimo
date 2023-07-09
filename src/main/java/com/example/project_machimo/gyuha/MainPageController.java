package com.example.project_machimo.gyuha;

import com.example.project_machimo.gyuha.alert.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {

    private final AlertService alertService;

    @Autowired
    public MainPageController(AlertService alertService) {
        this.alertService = alertService;
    }


   @GetMapping("/test123")
    public String test(){
        return "testPay";
   }

}
