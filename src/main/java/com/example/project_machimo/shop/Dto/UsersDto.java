package com.example.project_machimo.shop.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDto {
    private Integer user_id;//사용자 번호
    private String u_id;//사용자 아이디
    private String u_password;//사용자 패스워드
    private String u_email;//사용자 이메일
    private String u_name;//사용자 이름
    private String u_jumin;//사용자 주민번호
    private String u_phone;//사용자 폰 번호
    private String u_address;//사용자 주소
    private String u_nickname;//사용자 닉네임
    private Integer u_point;//사용자 적립금
    private Timestamp u_created_at;//사용자 회원 생성날짜
    private Timestamp u_updated_at;//사용자 회원 수정날짜
    private Integer u_role;//사용자 권한
    private String u_signout;//사용자 탈퇴여부
}
