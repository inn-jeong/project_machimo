package com.example.project_machimo.gyuha.report.controller;


import com.example.project_machimo.gyuha.report.dto.ReportDTO;
import com.example.project_machimo.gyuha.report.service.ReportService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/*
-최규하
유저가 신고기능을 사용할때 처리결과를 리턴하는 컨트롤러*/
@RestController
@RequestMapping("/report")
public class ReportRestController {


    private final ReportService reportService;

    @Autowired
    public ReportRestController(ReportService reportService) {
        this.reportService = reportService;
    }
    /*
    -최규하
    유저 세션이 null이라면 badRequset와함께 body에 이유를 담아서 보냄
    세션이 있다면 service에서 처리한 결과를 리턴함*/
    @PostMapping("/send")
    public ResponseEntity<? extends Object> sendResponse(@RequestBody ReportDTO reportDTO, HttpSession session){
        if (session.getAttribute("userId")==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("신고는 회원만 가능합니다.");
        }
        reportDTO.setUserId((Integer) session.getAttribute("userId"));
        return reportService.response(reportDTO);
    }


}
