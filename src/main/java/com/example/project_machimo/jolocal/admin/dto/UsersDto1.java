package com.example.project_machimo.jolocal.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UsersDto1 {
    private int userId;
    private String uId;
    private String uPassword;
    private String uEmail;
    private String uName;
    private String uJumin;
    private String uPhone;
    private String uAddress;
    private String uAddressSub;
    private String uNickname;
    private String uSocial;
    private int uPoint;
    private Timestamp uCreatedAt;
    private Timestamp uUpdatedAt;
    private int uRole;
    private String uSignout;
    private int uAddrPostcode;
    private String uAccount;
    //신고
    private int reportId;
//    private int userId;
    private int sellerUserId;
    private int productsId;
    private String reportContent;
    private Timestamp reportDate;
    private int reportCount;

}