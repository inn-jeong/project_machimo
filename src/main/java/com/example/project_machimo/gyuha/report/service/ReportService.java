package com.example.project_machimo.gyuha.report.service;

import com.example.project_machimo.gyuha.report.dto.ReportDTO;
import org.springframework.http.ResponseEntity;

public interface ReportService {

    boolean isDuplicateReporting(ReportDTO reportDTO);
    int insertReport(ReportDTO reportDTO);


}
