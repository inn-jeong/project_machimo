package com.example.project_machimo.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDto {
    private int board_id;
    private int user_id;
    private String b_category;
    private String inquiry_category;
    private String b_title;
    private String b_writer;
    private String b_content;
    private Timestamp b_date;
    private Integer b_hit;
    private int b_pwd;
}
//	"board_id"	NUMBER		NOT NULL,
//            "user_id"	NUMBER		NOT NULL,
//            "b_category"	VARCHAR2(10)		NOT NULL,
//            "inquiry_category"	VARCHAR2(10)		NOT NULL,
//            "b_title"	VARCHAR2(50)		NULL,
//            "b_writer"	VARCHAR2(15)		NOT NULL,
//            "b_content"	VARCHAR2(1000)		NULL,
//            "b_date"	TIMESTAMP	DEFAULT sysdate	NOT NULL,
//            "b_hit"	NUMBER	DEFAULT 0	NOT NULL,
//            "b_pwd"	NUMBER		NULL