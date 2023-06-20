package com.example.project_machimo.login.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("login")
public class LoginController {
    @RequestMapping("/page")
    public String login(){
        return "loginTest";
    }
}
