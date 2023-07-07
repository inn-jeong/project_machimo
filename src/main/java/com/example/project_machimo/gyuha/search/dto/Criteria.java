package com.example.project_machimo.gyuha.search.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


//페이징 크리테리아
@AllArgsConstructor
public class Criteria {
    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getSearchOption() {
        return searchOption;
    }

    public void setSearchOption(String searchOption) {
        this.searchOption = searchOption;
    }

    private int pageNum;
    private int amount;

    private String keyword;
    private String searchOption;
    public Criteria(){
        this(1,10);
    }

    public Criteria(int pageNum,int amount){
        this.amount = amount;
        this.pageNum = pageNum;

    }
}
