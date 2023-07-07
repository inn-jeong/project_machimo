package com.example.project_machimo.main.Service;



import com.example.project_machimo.main.Dto.ItemDto;

import java.util.ArrayList;

public interface MainService {
    public ArrayList<ItemDto> newestItem();
    public ArrayList<ItemDto> popularItem();

    public ArrayList<ItemDto> figureItem();

    public ArrayList<ItemDto> goodsItem();
}
