package com.example.project_machimo.login.Controller;

import com.example.project_machimo.login.Service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

@Slf4j
@RequestMapping("/loginT")
@Controller
public class LoginController {
    @Autowired
    private LoginService service;

    @RequestMapping("/login")
    public String login() {
        log.info("login");
        return "loginTest";
    }

    @RequestMapping("/login-process")
    public String login_process(@RequestParam HashMap<String, String> param) {
        log.info("@# login_process start");
        int re = service.loginYn(param);
        String str = "";
        if(re == 1) {
            str = "redirect:login_ok";
        }else {
            str = "redirect:login";
        }
        log.info("@# login_process end");
        return str;
    }

    @RequestMapping("/login_ok")
    public String login_ok() {
        log.info("login_ok");
        return "index";
    }


    @RequestMapping("/register_page")
    public String register_jsp() {
        return "registerTest";
    }

    @RequestMapping("/register")
    public String register(@RequestParam HashMap<String, String> param) {
        log.info("@# id ===>"+param.get("u_id"));
        service.memberInsert(param);
        return "redirect:login";
    }

    @RequestMapping("/callback")
    public String naverCallback(){
        return "callback";
    }
}
