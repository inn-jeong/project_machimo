package com.example.project_machimo.sqlLogTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    private UserDao dao;

//    sqlLog Test 최규하
    @RequestMapping("/employee")
    public String userlistPage(Model model){
        model.addAttribute("employee", dao.getEmp());
        return "logbackTest";
    }

}
