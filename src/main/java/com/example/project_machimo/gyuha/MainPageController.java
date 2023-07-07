package com.example.project_machimo.gyuha;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {
    @GetMapping("/")
    public String test(Model model, HttpSession session){
        session.setAttribute("userId",2);
        return "index";
    }

    @GetMapping("/delete")
    public String test2(HttpSession session){
        session.invalidate();
        return "testHtml/index";
    }


}
