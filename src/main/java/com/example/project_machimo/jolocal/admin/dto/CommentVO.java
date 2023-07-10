package com.example.project_machimo.jolocal.admin.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class CommentVO {
    private int cno;
    private int bno;
    private String content;
    private String writer;
    private Timestamp reg_date;
}
