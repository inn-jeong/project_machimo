package com.example.project_machimo.report.service;

import com.example.project_machimo.report.dto.ReportDTO;
import org.springframework.http.ResponseEntity;

public interface ReportService {
    ResponseEntity<? extends Object> response(ReportDTO reportDTO);



}
