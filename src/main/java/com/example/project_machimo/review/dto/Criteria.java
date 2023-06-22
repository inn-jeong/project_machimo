package com.example.project_machimo.review.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Criteria {
    private int pageNum;//페이지 번호
    private int amount;//페이지당 글 갯수
    public Criteria(){
//        초기페이지는 1이고, 10개씩 출력
        this(1,10);//1~10
//        2:11~ , 3:21~, 4:31~
//        this(5,10);//41~50
    }
}
