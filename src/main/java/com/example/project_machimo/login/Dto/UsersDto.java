package com.example.project_machimo.login.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDto {
    private Integer userId;
    private String uId;
    private String uPassword;
    private String uName;
    private String uJumin;
    private String uEmail;
    private String uPhone;
    private String uAddress;
    private String uAddress_sub;
    private String uNickname;
    private String uSocial;
    private Integer uPoint;
}


//추가 컬럼
//U_CREATED_AT
//U_UPDATED_AT
//U_SIGNOUT