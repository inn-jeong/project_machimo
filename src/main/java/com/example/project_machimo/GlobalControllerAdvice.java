package com.example.project_machimo;

import com.example.project_machimo.alert.dto.AlertVO;
import com.example.project_machimo.alert.service.AlertService;
import com.example.project_machimo.order.dto.OrderDTO;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Map;

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
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            return null;
        }

        System.out.println("@#!#@# userId" + userId);
        return service.alertResult(userId);
    }

    @ModelAttribute("alList")
    public List<AlertVO> alList(HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
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
        return session.getAttribute("userId") != null;


    }
    @ModelAttribute("session")
    public Integer session(HttpSession session){
        return (Integer)session.getAttribute("userId");
    }



}
