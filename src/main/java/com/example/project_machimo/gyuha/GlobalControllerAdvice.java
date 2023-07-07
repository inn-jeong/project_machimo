package com.example.project_machimo.gyuha;

import com.example.project_machimo.gyuha.alert.dto.AlertVO;
import com.example.project_machimo.gyuha.alert.service.AlertService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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



    // 안읽은 알림 갯수
    @ModelAttribute("alertResult")
    public Integer alertCount(HttpSession session) {

        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            return null;
        }

        System.out.println("@#!#@# userId" + userId);
        return service.alertResult(userId);
    }

    //알림창 리스트 세션이 없다면 아무것도 안 나오게함 - 최규하
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

    //세션이 있다면 null을 true를 반환함 - 최규하
    @ModelAttribute("hasSession")
    public boolean hasSession(HttpSession session) {
        return session.getAttribute("userId") != null;


    }
    @ModelAttribute("session")
    public Integer session(HttpSession session){
        return (Integer)session.getAttribute("userId");
    }



}
