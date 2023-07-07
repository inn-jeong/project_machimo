package com.example.project_machimo.gyuha.wishlists.dto;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/* - 최규하
찜 JSON을 담는 DTO
스네이크 케이스로 보내주는 데이터를 받기위해 JsonNaming사용*/
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishlistsDTO {
    private Integer wishListId;
    private Integer userId;
    private Integer productId;



}
