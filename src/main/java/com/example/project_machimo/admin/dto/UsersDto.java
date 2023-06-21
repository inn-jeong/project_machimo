package com.example.project_machimo.admin.dto;

import lombok.*;

import java.sql.Timestamp;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDto {
    private int user_id;
    private String u_id;
    private String u_password;
    private String u_email;
    private String u_name;
    private String u_jumin;
    private String u_phone;
    private String u_address;
    private String u_nickname;
    private int u_point;
    private Timestamp u_created_at;
    private Timestamp u_updated_at;
    private int u_role;
    private String u_signout;
}
