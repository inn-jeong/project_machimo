package com.example.project_machimo.gyuha.report.service;

import com.example.project_machimo.gyuha.report.dao.ReportDAO;
import com.example.project_machimo.gyuha.report.dto.ReportDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


/*
-최규하
신고 게시글을 dao에서 처리하고 처리 결과를 컨트롤러로 리턴해주는 Service
*/

@Service
@Slf4j


public class ReportServiceImpl  implements ReportService {

    private final ReportDAO reportDAO;

    @Autowired
    public ReportServiceImpl(ReportDAO reportDAO) {
        this.reportDAO = reportDAO;
    }


 
    
// 현재 보고 있는 게시글을 신고한 이력이 있는지 없는지 확인하는 dao에 결과값을 받아오는 메소드
    @Override
    public boolean isDuplicateReporting(ReportDTO reportDTO) {
        Integer result = reportDAO.duplicateReportingCheck(reportDTO);
        return result != null;
    }
    
//    dao에 insert하는 결과값을 받는 메소드 메소드
    @Override
    public int insertReport(ReportDTO reportDTO) {
        return reportDAO.insertReport(reportDTO);

    }





}



