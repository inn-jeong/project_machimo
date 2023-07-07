package com.example.project_machimo.main.Service;



import com.example.project_machimo.main.Dao.MainDao;
import com.example.project_machimo.main.Dto.ItemDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Slf4j
@Service("MainService")

public class MainServiceImpl implements  MainService{
    @Autowired
    private SqlSession sqlSession;

    @Override
    public ArrayList<ItemDto> newestItem() {
        log.info("@# newestItem start");
        MainDao dao = sqlSession.getMapper(MainDao.class);
        ArrayList<ItemDto> newestItem = dao.newestItem();
        log.info("@# newestItem end");
        return newestItem;
    }

    @Override
    public ArrayList<ItemDto> popularItem() {
        log.info("@# popularItem start");
        MainDao dao = sqlSession.getMapper(MainDao.class);
        ArrayList<ItemDto> popularItem = dao.popularItem();
        log.info("@# popularItem end");
        return popularItem;
    }

    @Override
    public ArrayList<ItemDto> figureItem() {
        log.info("@# figureItem start");
        MainDao dao = sqlSession.getMapper(MainDao.class);
        ArrayList<ItemDto> figureItem = dao.figureItem();
        log.info("@# figureItem end");
        return figureItem;
    }

    @Override
    public ArrayList<ItemDto> goodsItem() {
        log.info("@# goodsItem start");
        MainDao dao = sqlSession.getMapper(MainDao.class);
        ArrayList<ItemDto> goodsItem = dao.goodsItem();
        log.info("@# goodsItem end");
        return goodsItem;
    }
}
