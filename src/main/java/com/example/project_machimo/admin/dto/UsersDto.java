package com.example.project_machimo.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDto {
    private int usersId;
    private Timestamp createdAt;
    private String email;
    private String password;
    private String name;
    private String phoneNumber;
    private int point;
    private String address;
    private Timestamp updatedAt;
    private String userLoginId;
    private String nickName;
    private String registNumber;
    private String signout;
}
