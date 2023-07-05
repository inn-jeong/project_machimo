package com.example.project_machimo.main.Dao;



import com.example.project_machimo.main.Dto.ItemDto;

import java.util.ArrayList;

public interface MainDao {
    public ArrayList<ItemDto> newestItem();
    public ArrayList<ItemDto> popularItem();
}
