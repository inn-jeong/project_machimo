package com.example.project_machimo.jomuragi.productEnroll.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDto {
    private Integer userId;//사용자 번호
    private String uId;//사용자 아이디
    private String uPassword;//사용자 패스워드
    private String uEmail;//사용자 이메일
    private String uName;//사용자 이름
    private String uJumin;//사용자 주민번호
    private String uPhone;//사용자 폰 번호
    private String uAddress;//사용자 주소
    private String uNickname;//사용자 닉네임
    private Integer uPoint;//사용자 적립금
    private Timestamp uCreated_at;//사용자 회원 생성날짜
    private Timestamp uUpdated_at;//사용자 회원 수정날짜
    private Integer uRole;//사용자 권한
    private String uSignout;//사용자 탈퇴여부
}
