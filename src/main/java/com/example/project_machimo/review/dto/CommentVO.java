package com.example.project_machimo.review.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class CommentVO {
//    private int id;                       // 댓글 번호 (PK)
//    private int reviewId;                   // 게시글 번호 (FK)
//    private String content;                // 내용
//    private String writer;                 // 작성자
//    private int deleteYn;              // 삭제 여부
//    private LocalDateTime createdDate;     // 생성일시
//    private LocalDateTime modifiedDate;    // 최종 수정일시

    private int cno;
    private int bno;
    private String content;
    private String writer;
    private Timestamp reg_date;
}
