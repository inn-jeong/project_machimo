package com.example.project_machimo.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
//    product
    private int productId;
    private int cId;
    private int cId2;
    private int userId;
    private String pName;
    private String pInfo;
    private int pDirect;
    private Timestamp pDur;
    private int pBPrice;
    private Timestamp pCreatedAt;
    private Timestamp pUpdatedAt;
    private int pHit;
    private int pSalesStatus;
    private int pSaleType;
    private String pAccount;
    private String pAddress;
    private String pBank;

//    productImg
//    private int productId;
    private int iId;  // 파일 첨부 여부(첨부1, 미첨부0)
    private String iImage; // 원본 파일 이름
    private String IsubImg; // 서버 저장용 파일 이름
    private MultipartFile boardFile; //뷰->컨트롤러 파일을 담는 용도
//    private int fileStatue; // 파일 첨부 여부(첨부1, 미첨부0)

//    category
//    private int cId;
//    private int cId2;
    private String cName;
    private String cName2;


}
