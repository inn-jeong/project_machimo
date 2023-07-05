package com.example.project_machimo.report.dao;

import com.example.project_machimo.report.dto.ReportDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReportDAO {


    Integer duplicateReportingCheck(ReportDTO reportDTO);
    int insertReport(ReportDTO reportDTO);


}
