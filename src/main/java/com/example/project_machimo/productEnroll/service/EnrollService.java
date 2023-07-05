package com.example.project_machimo.productEnroll.service;

import com.example.project_machimo.productEnroll.dao.EnrollMapper;
import com.example.project_machimo.productEnroll.dto.CategoryVO;
import com.example.project_machimo.productEnroll.dto.ProductDto;
import com.example.project_machimo.shop.Dao.ShopDao;
import com.example.project_machimo.shop.Dto.CategoryDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

@Service
@Slf4j
public class EnrollService {

    @Autowired
    private SqlSession sqlSession;



    private final EnrollMapper mapper;
    @Autowired
    public EnrollService(EnrollMapper mapper) {
        this.mapper = mapper;
    }

    public ArrayList<CategoryDto> getCategories() {
        log.info("@# getCategories");
        ShopDao dao = sqlSession.getMapper(ShopDao.class);
        ArrayList<CategoryDto> categories = dao.getCategories();
        return categories;
    }

    public ArrayList<CategoryDto> getSubCategories(Integer cId2) {
        log.info("@# getSubCategories");
        ShopDao dao = sqlSession.getMapper(ShopDao.class);
        ArrayList<CategoryDto> subCategories = dao.getSubCategories(cId2);
        return subCategories;
    }

//    public void write(HashMap<String, String> param) {
    public void write(HashMap<String, String> param) {
        log.info("@# EnrollService.write() start");
        ProductDto dto = new ProductDto();
        dto.setCId(Integer.parseInt(param.get("cId")));
        dto.setUserId(Integer.parseInt(param.get("userId")));
        dto.setPName(param.get("pName"));
        dto.setPInfo(param.get("pInfo"));
//        dto.setPDur(Timestamp.valueOf(param.get("pDur")));
        dto.setPDurDate(Integer.parseInt(param.get("pDurDate")));
        dto.setPSaleType(Integer.parseInt(param.get("pSaleType")));
        dto.setPAccount(param.get("pAccount"));
        dto.setPAddress(param.get("pAddress"));
        dto.setPBank(param.get("pBank"));
        dto.setPAddressSub(param.get("pAddressSub"));
        dto.setPAddrPostcode(Integer.parseInt(param.get("pAddrPostcode")));
        // 나머지 필드도 필요한 경우에 세팅

        mapper.write(dto);
        log.info("@# EnrollService.write() end");
    }
//    public void write(HashMap<String, String> dto) {
//        log.info("@# EnrollService.write() start");
//        EnrollMapper mapper = sqlSession.getMapper(EnrollMapper.class);
//        mapper.write(dto);
//        log.info("@# EnrollService.write() end");
//    }
}
