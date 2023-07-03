package com.example.project_machimo;

import com.example.project_machimo.alert.dto.AlertVO;
import com.example.project_machimo.alert.service.AlertService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
@Slf4j
@ControllerAdvice
public class GlobalControllerAdvice {

    private final AlertService service;

    @Autowired
    public GlobalControllerAdvice(AlertService service) {
        this.service = service;
    }


    @ModelAttribute("alertResult")
    public Integer alertCount(HttpSession session) {

        //나중에 바꾸기
        Integer userId = (Integer) session.getAttribute("user");

        if (userId == null) {
            return null;
        }

        System.out.println("@#!#@# userId" + userId);
        return service.alertResult(userId);
    }

    @ModelAttribute("alList")
    public List<AlertVO> alList(HttpSession session) {
        Integer userId = (Integer) session.getAttribute("user");
        if (userId == null) {
            return null;
        }

        List<AlertVO> alertVOS = service.alList(userId);
        if (alertVOS.isEmpty()) System.out.println("값이 비어있음 @#!");
        log.info("@#!#@# 유저아이디" + userId);


        return alertVOS;
    }

    @ModelAttribute("hasSession")
    public boolean hasSession(HttpSession session) {
        return session.getAttribute("user") != null;


    }



}
