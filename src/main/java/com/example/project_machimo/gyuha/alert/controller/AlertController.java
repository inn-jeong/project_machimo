package com.example.project_machimo.gyuha.alert.controller;

import com.example.project_machimo.gyuha.alert.service.AlertService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alert")
@Slf4j
public class AlertController {

    private final AlertService alertService;

    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    @PostMapping("/delete")
    public void del(@RequestParam int id){
        log.info("알러트 아이디 ===> {}",id);
        alertService.delete(id);

    }
    @PostMapping("/del-all")
    public void del(HttpSession session){
        Integer userId = (Integer) session.getAttribute("userId");

        alertService.deleteAll(userId);
    }

    @PostMapping("/test")
    public ResponseEntity<?> flug(@RequestParam int id){
        System.out.println("인트의 값은 "+id);
        alertService.checkedFlag(id);
        return ResponseEntity.ok().build();
    }

}
