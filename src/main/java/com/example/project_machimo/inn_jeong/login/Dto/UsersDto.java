package com.example.project_machimo.inn_jeong.login.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDto {
    private Integer userId;
    private String uId;
    private String uSocial;
    private String uPassword;
    private String uEmail;
    private String uName;
    private String uJumin;
    private String uPhone;
    private Integer uAddrPostcode;
    private String uAddress;
    private String uAddressSub;
    private String uNickname;
    private String uAccount;
    private Integer uPoint;
    private Timestamp uCreatedAt;
    private Timestamp uUpdatedAt;
    private Timestamp uSuspension;
    private Integer uRole;
    private String uSignout;
}


//추가 컬럼
//U_CREATED_AT
//U_UPDATED_AT
//U_SIGNOUT