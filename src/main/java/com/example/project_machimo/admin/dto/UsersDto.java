package com.example.project_machimo.admin.dto;

import lombok.*;

import java.sql.Timestamp;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDto {
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
    private int productsId;
    private String reportContent;
    private Timestamp reportDate;
    private int reportCount;

}
