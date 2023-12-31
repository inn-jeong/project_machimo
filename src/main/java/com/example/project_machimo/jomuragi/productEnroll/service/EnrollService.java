package com.example.project_machimo.jomuragi.productEnroll.service;

import com.example.project_machimo.jomuragi.productEnroll.dao.EnrollMapper;
import com.example.project_machimo.jomuragi.productEnroll.dto.CategoryDto;
import com.example.project_machimo.jomuragi.productEnroll.dto.ProductDto;
import com.example.project_machimo.jomuragi.productEnroll.dto.ProductImageVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
//        ShopDao dao = sqlSession.getMapper(ShopDao.class);
        EnrollMapper dao = sqlSession.getMapper(EnrollMapper.class);
        ArrayList<CategoryDto> categories = dao.getCategories();
        return categories;
    }

    public ArrayList<CategoryDto> getSubCategories(Integer cId2) {
        log.info("@# getSubCategories");
//        ShopDao dao = sqlSession.getMapper(ShopDao.class);
        EnrollMapper dao = sqlSession.getMapper(EnrollMapper.class);
        ArrayList<CategoryDto> subCategories = dao.getSubCategories(cId2);
        return subCategories;
    }

//    public void write(HashMap<String, String> param) {
    public void write(HashMap<String, String> param) {
        log.info("@# EnrollService.write() start");
        ProductDto dto = new ProductDto();
        log.info("param.get(\"category1\")"+param.get("category1"));
        log.info("param.get(\"category2\")"+param.get("category2"));
        dto.setCId(Integer.parseInt(param.get("category1")));

        dto.setCId2(Integer.parseInt(param.get("category2")));
        dto.setUserId(Integer.parseInt(param.get("userId")));
        dto.setPName(param.get("pName"));
        dto.setPInfo(param.get("pInfo"));
        dto.setPSaleType(Integer.parseInt(param.get("pSaleType")));

        int pSaleType = Integer.parseInt(param.get("pSaleType"));

        if (pSaleType == 0) {
            dto.setPBPrice(Integer.parseInt(param.get("pBPrice")));
            dto.setPDurDate(Integer.parseInt(param.get("pDurDate")));
        } else if (pSaleType == 1) {
            dto.setPDirect(Integer.parseInt(param.get("pDirect")));
        }

        dto.setPAccount(param.get("pAccount"));
        dto.setPAddress(param.get("pAddress"));
        dto.setPBank(param.get("pBank"));
        dto.setPAddressSub(param.get("pAddressSub"));
        dto.setPAddrPostcode(Integer.parseInt(param.get("pAddrPostcode")));

        mapper.write(dto);
        log.info("@# EnrollService.write() end");
    }
//    public void write(HashMap<String, String> dto) {
//        log.info("@# EnrollService.write() start");
//        EnrollMapper mapper = sqlSession.getMapper(EnrollMapper.class);
//        mapper.write(dto);
//        log.info("@# EnrollService.write() end");
//    }


    public void imageEnroll(ProductImageVO vo) {
        log.info("iImage @@@@@@@@@@@@@@@@@@@@@@@@@@@@" + vo.getIImage());

        mapper.imageEnroll(vo);
    }

    public int getProductId(){
        return mapper.getProductId();
    };

    public void deleteFile(int productId){
        mapper.deleteFile(productId);
    };

    public List<ProductImageVO> getAttachList(int productId){
        log.info("getAttachList.........");
       return mapper.getAttachList(productId);
    };
}
