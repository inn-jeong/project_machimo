package com.example.project_machimo.Park_gi_hyeon.main.Dao;



import com.example.project_machimo.Park_gi_hyeon.main.Dto.ItemDto;

import java.util.ArrayList;

public interface MainDao {
    public ArrayList<ItemDto> newestItem();
    public ArrayList<ItemDto> popularItem();

    public ArrayList<ItemDto> figureItem();

    public ArrayList<ItemDto> goodsItem();
}
