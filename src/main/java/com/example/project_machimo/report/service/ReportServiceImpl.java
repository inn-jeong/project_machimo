package com.example.project_machimo.report.service;

import com.example.project_machimo.report.dao.ReportDAO;
import com.example.project_machimo.report.dto.ReportDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl  implements ReportService {

    private final ReportDAO reportDAO;

    @Autowired
    public ReportServiceImpl(ReportDAO reportDAO) {
        this.reportDAO = reportDAO;
    }


    @Override
    public ResponseEntity<? extends Object> response(ReportDTO reportDTO) {

        boolean duplicateReporting = isDuplicateReporting(reportDTO);
        int i = insertReport(reportDTO);
        String message;
        if (i!=1){
            message = "저장중 문제가 발생하였습니다.";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }

        if (isDuplicateReporting(reportDTO)){
            message = "이미 신고한 게시글입니다.";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }




        return ResponseEntity.ok().build();
    }

    public boolean isDuplicateReporting(ReportDTO reportDTO) {
        List<Integer> result = reportDAO.duplicateReportingCheck(reportDTO);

        return !result.isEmpty();
    }


    public int insertReport(ReportDTO reportDTO) {
        return reportDAO.insertReport(reportDTO);

    }





}



