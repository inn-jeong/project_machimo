package com.example.project_machimo.review.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDto {
    private int cId;
    private int reviewId;
    private String cWriter;
    private Timestamp cDate;
    private String contents;

}
