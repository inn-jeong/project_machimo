package com.example.project_machimo.login.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginDto {
    private String user_id;
    private String user_pw;
}
