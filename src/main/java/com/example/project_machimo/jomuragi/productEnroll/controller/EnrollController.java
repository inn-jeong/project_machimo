package com.example.project_machimo.jomuragi.productEnroll.controller;


import com.example.project_machimo.jomuragi.productEnroll.dto.ProductImageVO;
import com.example.project_machimo.jomuragi.productEnroll.service.EnrollService;
import com.example.project_machimo.jomuragi.review.dao.AttachMapper;
import com.example.project_machimo.jomuragi.shop.Dto.CategoryDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@Slf4j
@RequestMapping("/productEnroll")
public class EnrollController {

    @Autowired
    private EnrollService service;

    @RequestMapping("/enroll_form")
    public String enroll_form(Model model){
        //        // 모든 카테고리와 그에 해당하는 하위 카테고리를 가져옴
        ArrayList<CategoryDto> categories = service.getCategories();
        Map<Integer, ArrayList<CategoryDto>> subcategory = new HashMap<>();
        for (CategoryDto category : categories) {
            ArrayList<CategoryDto> subCategories = service.getSubCategories(category.getCId());
            subcategory.put(category.getCId(), subCategories);
        }

        //        카테고리 값을 model에 저장 하고 넘어감
        model.addAttribute("categories", categories);

//        Map안에 배열을 담은 형태
        model.addAttribute("subcategory", subcategory);
//        log.info("@# category ===>"+categories.get(0).getCId());
//        log.info("@# category ===>"+model.getAttribute("categories"));

//        이미지 등록시 productId필요하므로 넘겨줌
        model.addAttribute("getProductId",service.getProductId());

        return "productEnroll/soldForm";
    }

//    @RequestMapping("/enroll_form")
//    public String enroll_form( , @RequestParam(name = "category2", required = false) Integer cId
//            ,@RequestParam(name = "category1", required = false) Integer cId2){
//        return "productEnroll/soldForm";
//    }

    @RequestMapping("/enroll")
    public String enroll(@RequestParam HashMap<String, String> param){
        
//        //        카테고리 값을 model에 저장 하고 넘어감
//        model.addAttribute("categories", categories);
//        model.addAttribute("subcategory", subcategory);

        service.write(param);
        return "productEnroll/success";
    }


    @RequestMapping("/jusoPopup")
    public String jusoPopup(HttpServletRequest request, Model model){
        //request.setCharacterEncoding("UTF-8");  //한글깨지면 주석제거
        //request.setCharacterEncoding("EUC-KR");  //해당시스템의 인코딩타입이 EUC-KR일경우에
        log.info("@# =========juso init============");
        String inputYn = request.getParameter("inputYn");
        String roadFullAddr = request.getParameter("roadFullAddr");
        String roadAddrPart1 = request.getParameter("roadAddrPart1");

        String roadAddrPart2 = request.getParameter("roadAddrPart2");
        String engAddr = request.getParameter("engAddr");
        String jibunAddr = request.getParameter("jibunAddr");
        String zipNo = request.getParameter("zipNo");
        String addrDetail = request.getParameter("addrDetail");
        String admCd    = request.getParameter("admCd");
        String rnMgtSn = request.getParameter("rnMgtSn");
        String bdMgtSn  = request.getParameter("bdMgtSn");
        /** API 서비스 제공항목 확대 (2017.02) **/
        String detBdNmList  = request.getParameter("detBdNmList");
        String bdNm  = request.getParameter("bdNm");
        String bdKdcd  = request.getParameter("bdKdcd");
        String siNm  = request.getParameter("siNm");
        String sggNm  = request.getParameter("sggNm");
        String emdNm  = request.getParameter("emdNm");
        String liNm  = request.getParameter("liNm");
        String rn  = request.getParameter("rn");
        String udrtYn  = request.getParameter("udrtYn");
        String buldMnnm  = request.getParameter("buldMnnm");
        String buldSlno  = request.getParameter("buldSlno");
        String mtYn  = request.getParameter("mtYn");
        String lnbrMnnm  = request.getParameter("lnbrMnnm");
        String lnbrSlno  = request.getParameter("lnbrSlno");
        String emdNo  = request.getParameter("emdNo");

        log.info("@# juso input ===> "+inputYn);
        log.info("@# juso part1 ===> "+roadAddrPart1);

        model.addAttribute("inputYn",inputYn);
        model.addAttribute("roadAddrPart1",roadAddrPart1);
        model.addAttribute("addrDetail",addrDetail);
        model.addAttribute("zipNo",zipNo);


        return "productEnroll/jusoPopup";
    }



    //    첨부파일 업로드
//    produce 속성 :전송되는 json데이터 인코딩해서 이미지 파일이름 한글이어도 안깨지도록 해줌
    @RequestMapping (value="/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    반환타입이 ResponseEntity 객체ㅡ http의 body 에 추가될 데이터는 List<ProductImageVO> 임
//    public ResponseEntity<List<ProductImageVO>> uploadajaxAction(@RequestParam("reviewId") int reviewId, MultipartFile[] uploadFile){
    public ResponseEntity<List<ProductImageVO>> uploadajaxAction(MultipartFile[] uploadFile){

        log.info("uploadAjaxAction");

        /* 이미지 파일 체크 */
        for(MultipartFile multipartFile: uploadFile) {

            File checkfile = new File(multipartFile.getOriginalFilename());
            String type = null;

            try {
//                probeContentType  : 파라미터로 전달받은 파일의 MIME TYPE을 문자열 반환
//                toPath() :File객체를 Path객체로 만들어줌
                type = Files.probeContentType(checkfile.toPath());
                log.info("MIME TYPE:"+ type);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if(!type.startsWith("image")) {

                List<ProductImageVO> list = null;
                return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);

            }

        }

//      String uploadFolder = "C:\\upload";
//        String uploadFolder = new File("src/main/resources/static/upload").getAbsolutePath();
        String uploadFolder = new File("src/main/resources/static/upload").getAbsolutePath();

//        날짜 폴더 경로
        LocalDate currentDate = LocalDate.now();
        String datePath = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).replace("-", File.separator);


        /* 폴더 생성 */
        File uploadPath = new File(uploadFolder, datePath);

        if(uploadPath.exists() == false) {
            uploadPath.mkdirs();
        }

        /* 이미저 정보 담는 객체 */
        List<ProductImageVO> list = new ArrayList();

        for(MultipartFile multipartFile : uploadFile) {

//            이미지 정보 객체
            ProductImageVO vo = new ProductImageVO();


            /* 파일 이름 */
            String uploadFileName = multipartFile.getOriginalFilename();
            vo.setIImage(uploadFileName);
            System.out.println("uploadFileName = " + uploadFileName);

            vo.setUploadPath(datePath);

            System.out.println("datePath = " + datePath);

//            String baseUrl = "http://localhost:8090/upload/"; // 기본 URL
//            String imageUrl = baseUrl + uploadPath.toString().replace("\\", "/") + "/" + uploadFileName; // URL 조합
//            String imageUrl = uploadPath.toString().replace("\\", "/") + "/" + uploadFileName; // URL 조합
            String imageUrl = uploadPath.toString();
            vo.setUrl(imageUrl); // url 멤버 변수 설정
//            vo.setReviewId(reviewId);
            System.out.println("imageUrl = " + imageUrl);
            /* uuid 적용 파일 이름 */
            String uuid = UUID.randomUUID().toString();
            vo.setUuid(uuid);

            System.out.println("uuid = " + uuid);

            uploadFileName = uuid + "_" + uploadFileName;

            /* 파일 위치, 파일 이름을 합친 File 객체 */
            File saveFile = new File(uploadPath, uploadFileName);

//            /* 파일 저장 */
//            try {
//                multipartFile.transferTo(saveFile);
//
////                thumnailator사용
//                File thumbnailFile = new File(uploadPath, "s_" + uploadFileName);
//
//                BufferedImage bo_image = ImageIO.read(saveFile);
//
//                //비율
//                double ratio = 3;
//                //넓이 높이
//                int width = (int) (bo_image.getWidth() / ratio);
//                int height = (int) (bo_image.getHeight() / ratio);
//
//                Thumbnails.of(saveFile)
//                        .size(width, height)
//                        .toFile(thumbnailFile);
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
            service.imageEnroll(vo);
            list.add(vo);
        }//for
//        http의 body에 추가될 데이터는 List<ProductImageVO>이고, 상태코드가 Ok(200)인 ResponseEntity 객체 생성
        ResponseEntity<List<ProductImageVO>> result = new ResponseEntity<>(list, HttpStatus.OK);
        return result;
    }


    //    admin만이 이미지 파일 삭제 수행해야함. 나중에 언니거 받아서 admincontroller에 작성
    /* 이미지 파일 삭제 */
    @PostMapping("/deleteFile")
    public ResponseEntity<String> deleteFile(int productId,  String fileName){

        log.info("deleteFile........" + fileName);
        File file = null;
        try {
//            인코딩된 파일 이름을 다시 디코드해야 파일 이름 지정 가능
//            썸네일 파일 삭제(미리보기 파일이 썸네일 파일이므로)
//            file = new File("c:\\upload\\" + URLDecoder.decode(fileName, "UTF-8"));
            file = new File(URLDecoder.decode(fileName, "UTF-8"));
            log.info("deleteFile........" + file);

//            File 클래스의 기본 메서드, 서버에 있는 파일 삭제

            file.delete();

            /* 원본 파일 삭제 */
//            String originFileName = file.getAbsolutePath().replace("s_", "");
            String originFileName = file.toString().replace("s_", "");

            log.info("originFileName : " + originFileName);

            file = new File(originFileName);

            file.delete();

            service.deleteFile(productId);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("fail", HttpStatus.NOT_IMPLEMENTED);
        }
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
