package com.example.project_machimo.gyuha.auction.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;



@NoArgsConstructor
@Data
public class CheckDTO {



    private Long bids;
    private int productId;
    private Boolean bidsHistory;
    private Long firstPrice;
    private Integer userId;
    public CheckDTO(Long bids, int productId, Boolean bidsHistory, Long firstPrice ,Integer userId) {
        this.bids = bids;
        this.productId = productId;
        this.bidsHistory = bidsHistory;
        this.firstPrice = firstPrice;
        this.userId = userId;
    }
}
