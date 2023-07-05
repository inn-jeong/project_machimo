package com.example.project_machimo.report.dto;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data

public class ReportDTO {
    private Integer userId;
    private Integer productId;
    private String reportContent;

}
