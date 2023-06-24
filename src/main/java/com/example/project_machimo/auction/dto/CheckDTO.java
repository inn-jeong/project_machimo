package com.example.project_machimo.auction.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component

@NoArgsConstructor
@Data
public class CheckDTO {


    @NotEmpty(message = "입찰가격을 입력해주세요")
    Long bids;
    int productId;
    Boolean bidsHistory;
    Long firstPrice;

    public CheckDTO(Long bids, int productId, Boolean bidsHistory, Long firstPrice) {
        this.bids = bids;
        this.productId = productId;
        this.bidsHistory = bidsHistory;
        this.firstPrice = firstPrice;
    }
}
