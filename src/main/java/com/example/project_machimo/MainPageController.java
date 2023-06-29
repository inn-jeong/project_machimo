package com.example.project_machimo;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {
    @GetMapping("/")
    public String test(Model model, HttpSession session){
        session.setAttribute("user",4);
        return "testHtml/index";
    }


}
