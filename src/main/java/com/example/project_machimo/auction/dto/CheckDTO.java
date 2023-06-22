package com.example.project_machimo.auction.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CheckDTO {

    @NotNull(message = "입찰가격을 입력해주세요")
    Integer bids;
    int productId;
    Boolean bidsHistory;
    Integer firstPrice;
}
