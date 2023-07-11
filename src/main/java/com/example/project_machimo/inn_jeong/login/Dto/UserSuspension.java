package com.example.project_machimo.inn_jeong.login.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//정지여부를 조회하기 위한 Dto
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSuspension {
    private String uSuspension;
    private Integer userId;
}
