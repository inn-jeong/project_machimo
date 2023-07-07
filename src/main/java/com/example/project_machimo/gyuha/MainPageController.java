package com.example.project_machimo.gyuha;

import com.example.project_machimo.gyuha.alert.service.AlertService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainPageController {

    private final AlertService alertService;

    @Autowired
    public MainPageController(AlertService alertService) {
        this.alertService = alertService;
    }

    @GetMapping("/")
    public String test(Model model, HttpSession session) {
        session.setAttribute("userId", 2);
        return "index";
    }

    @GetMapping("/delete")
    public String test2(HttpSession session) {
        session.invalidate();
        return "index";
    }

    @GetMapping("/test23")
    public String test(@RequestParam Integer alId) {
        alertService.checkedFlag(alId);
        return "index";
    }

}
