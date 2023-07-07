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


    /*
    -최규하
    이미 신고한적이 있으면 message에 이미 신고한 게시글입니다 body에 담아서 badrequest를 내림
    인서트에 실패하면 저장중 문제가 발생하였습니다를 담에서 badrequset를 내림
    */
    @Override
    public ResponseEntity<? extends Object> response(ReportDTO reportDTO) {
        log.info("리폿 리스폰으로 들어온 제이슨 ===> {}",reportDTO);

        boolean duplicateReporting = isDuplicateReporting(reportDTO);
        String message;

        if (isDuplicateReporting(reportDTO)){
            message = "이미 신고한 게시글입니다.";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }

        int i = insertReport(reportDTO);
        if (i!=1){
            message = "저장중 문제가 발생하였습니다.";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }



        return ResponseEntity.ok().build();
    }

    public boolean isDuplicateReporting(ReportDTO reportDTO) {
        Integer result = reportDAO.duplicateReportingCheck(reportDTO);
        return result != null;
    }


    public int insertReport(ReportDTO reportDTO) {
        return reportDAO.insertReport(reportDTO);

    }





}



