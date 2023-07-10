package com.example.project_machimo.jolocal.admin.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
public record StatusDto(
        int productId,
        int pSalesStatus,
        int pSaleType) {
}