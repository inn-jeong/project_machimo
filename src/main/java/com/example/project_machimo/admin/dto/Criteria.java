package com.example.project_machimo.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Criteria {
    private int pageNum;
    private int amount;

    private String type =""; //검색유형
    private String keyword =""; //검색키워드

    public Criteria() {
        this(1,10);
    }
    public Criteria(int pageNum, int amount) {
        this.pageNum = pageNum;
        this.amount = amount;
    }
}
