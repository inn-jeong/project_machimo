package com.example.project_machimo.report.controller;


import com.example.project_machimo.report.dto.ReportDTO;
import com.example.project_machimo.report.service.ReportService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
public class ReportRestController {


    private final ReportService reportService;

    @Autowired
    public ReportRestController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("/send")
    public ResponseEntity<? extends Object> sendResponse(@RequestBody ReportDTO reportDTO, HttpSession session){
        if (session.getAttribute("user")==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("신고는 회원만 가능합니다.");
        }
        reportDTO.setUserId((Integer) session.getAttribute("user"));
        return reportService.response(reportDTO);
    }


}
