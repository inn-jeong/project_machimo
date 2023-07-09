package com.example.project_machimo.jomuragi.review.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Criteria {
    private int pageNum;//페이지 번호
    private int amount;//페이지당 글 갯수

    private String type;//검색유형
    private String keyword;//검색키워드
    public Criteria(){
//        초기페이지는 1이고, 10개씩 출력
        this(1,10);//1~10
//        2:11~ , 3:21~, 4:31~
//        this(5,10);//41~50
    }
    public Criteria(int pageNum,int amount){
        this.pageNum = pageNum;
        this.amount = amount;
    }
//    public String[] getTypeArr(String type){
    public String[] getTypeArr(){
        return type == null? new String[]{} : type.split("");
    }

}
