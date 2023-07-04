package com.example.project_machimo.wishlists.dto;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
public class WishlistsDTO {
    private Integer wishListId;
    private Integer userId;
    private Integer productId;



}
