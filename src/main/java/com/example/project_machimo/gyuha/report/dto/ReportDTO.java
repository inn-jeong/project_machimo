package com.example.project_machimo.gyuha.report.dto;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
-최규하
신고내용을 담는 DTO
*/
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportDTO {
    private Integer userId;
    private Integer productId;
    private String reportContent;
    private Integer sellerUserId;
}
