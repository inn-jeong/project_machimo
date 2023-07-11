package com.example.project_machimo.gyuha.report.controller;


import com.example.project_machimo.gyuha.aop.LoginCheck;
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
    service에서 가져온 결과값에 따라서 리스폰을 내림
    if문에 충족하지 못할경우 ok가 리턴됨
    충족할 경우 message에 담아서 body에 보냄

  */
    @PostMapping("/send")
    @LoginCheck
    public ResponseEntity<? extends Object> sendResponse(@RequestBody ReportDTO reportDTO, HttpSession session) {
        System.out.println(reportDTO.getUserId()+"윱아");
        System.out.println(reportDTO.getProductId()+"프아");
        String message;
        boolean duplicateReporting = reportService.isDuplicateReporting(reportDTO);
        if (duplicateReporting) {
            message = "이미 신고한 게시글입니다.";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }

        int i = reportService.insertReport(reportDTO);
        if (i != 1) {
            message = "저장중 문제가 발생하였습니다.";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }


        return ResponseEntity.ok().build();
    }


}
