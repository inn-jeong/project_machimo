package com.example.project_machimo.gyuha.report.dao;

import com.example.project_machimo.gyuha.report.dto.ReportDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReportDAO {

    /*-
    최규하
    유저가 신고한 게시글이 이미 신고했어서  DB에 데이터가 있는지 확인하는 DAO
    데이터가 있다면 1를 리턴하고 아니면 null를 리턴함*/
    Integer duplicateReportingCheck(ReportDTO reportDTO);
    /*
    -최규하
    신고한 내용을 DB에 Insert하는 DAO 인서트에 성공하면 1를 리턴해줌
    */
    int insertReport(ReportDTO reportDTO);


}
