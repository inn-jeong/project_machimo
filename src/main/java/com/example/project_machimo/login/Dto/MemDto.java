package com.example.project_machimo.login.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemDto {
    private String users_id;
    private String u_id;
    private String u_password;
    private String u_name;
    private String u_jumin;
    private String u_email;
    private String u_phone_number;
    private String u_address;
    private String u_nickname;
    private String u_point;
}


//추가 컬럼
//U_CREATED_AT
//U_UPDATED_AT
//U_SIGNOUT