package com.example.project_machimo.jomuragi.review.controller;

import com.example.project_machimo.inn_jeong.login.Dto.UsersDto;
import com.example.project_machimo.jomuragi.review.dto.AttachImageVO;
import com.example.project_machimo.jomuragi.review.dto.Criteria;
import com.example.project_machimo.jomuragi.review.dto.PageDTO;
import com.example.project_machimo.jomuragi.review.dto.ReviewDto;
import com.example.project_machimo.jomuragi.review.service.AttachImageService;
import com.example.project_machimo.jomuragi.review.service.ReviewService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Slf4j
@Controller
//@RestController
//@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    private ReviewService service;

    @Autowired
    private AttachImageService imageService;


//    @Autowired
//    private AttachImageService imageService;

//    @Value("${uploadPath}")
//    private String uploadPath;


    @RequestMapping("/list_old")
    public String list(Model model) {
        log.info("@# list");
        ArrayList<ReviewDto> list = service.list();
        model.addAttribute("list",list);
        return "review/list";
    }

    @RequestMapping("/list")
//    public String list(Criteria cri, Model model) {
    public String list(Criteria cri, Model model, HttpSession session) {
        log.info("@# list");
        log.info("@#cri =======>"+cri);
        log.info("@#getPageNum() =======>"+cri.getPageNum());

//        UsersDto user = new UsersDto();
//        user.setUserId(1); //admin
//        user.setUNickname("admin");
//        session.setAttribute("user",user);

//        ArrayList<ReviewDto> list = service.list();
        model.addAttribute("list",service.list(cri));
        int total = service.getTotalCount();
        model.addAttribute("pageMaker", new PageDTO(total, cri));
        return "review/list";
    }
    @RequestMapping("/write_view/{productId}")
    public String writeView(@PathVariable int productId, Model model) {
        log.info("@# writeView");
        model.addAttribute("productId",productId);
        model.addAttribute("getReviewId",imageService.getReviewId());
        return "review/write_view";
    }

    @RequestMapping("/write")
    public String write(@RequestParam HashMap<String, String> param, Model model) {
        log.info("@# write");

        service.write(param);
        return "redirect:list";
    }


    @RequestMapping("/content_view")
    public String contentView(@RequestParam HashMap<String, String> param, Model model, HttpSession session) {
        log.info("@# contentView");
        ReviewDto dto = service.contentView(param);

        //이미지 불러오기
        List<AttachImageVO> images = service.getAttachList(Integer.parseInt(param.get("reviewId")));
//        List<AttachImageVO> images = service.getAttachList(Integer.parseInt(param.get("bno")));
        List<String> imagePaths = new ArrayList<>();

        for (AttachImageVO image : images) {
//            String imagePath = "/upload/" +image.getUploadPath() + "/s_" + image.getUuid() + "_" + image.getFileName();
            String imagePath = image.getUploadPath() + "/s_" + image.getUuid() + "_" + image.getFileName();

            String replacedPath = imagePath.replaceAll("\\\\", "/");
//            imagePaths.add(imagePath);
            imagePaths.add(replacedPath);

            System.out.println("imagePath = " + replacedPath);
        }

//        String str =  imagePaths.get(0);
//        System.out.println("imagePath = " + str);
//        model.addAttribute("imagePaths", str);
        model.addAttribute("imagePaths", imagePaths);


        model.addAttribute("content_view", dto);
        service.updateCount(dto.getReviewId());
//        model.addAttribute("hit", hit);
//        content_view.jsp 에서 pageMaker를 가지고 페이징 처리
        model.addAttribute("pageMaker", param);
//        model.addAttribute("images", imageService.getAttachList(dto.getReviewId()));


        return "review/content_view";
    }





    //    수정완료후 list 로 이동
    @RequestMapping("/modify")
//    public String modify(@RequestParam HashMap<String, String> param) {
    public String modify(@RequestParam HashMap<String, String> param, @ModelAttribute("cri") Criteria cri,
                         RedirectAttributes rttr) {
        log.info("@# modify");
//        System.out.println("param.get(\"reviewContent\") = " + param.get("reviewContent"));
        service.modify(param);
        rttr.addAttribute("pageNum",cri.getPageNum());
        rttr.addAttribute("amount",cri.getAmount());
        return "redirect:list";
    }

//    reviewId값으로 전의 값 받아와서 수정폼으로 넘어감
    @RequestMapping("/modify_view")
    public String modify_view(@RequestParam("reviewId") String reviewId ,Model model) {
//        public String modify_view(@RequestParam("reviewId") String reviewId ,Model model, @ModelAttribute("cri") Criteria cri,
//                RedirectAttributes rttr) {
        ReviewDto reviewDto = service.modify_view(reviewId);
        model.addAttribute("content_view",reviewDto);
//        rttr.addAttribute("pageNum",cri.getPageNum());
//        rttr.addAttribute("amount",cri.getAmount());
        return "review/modify_view";
    }

    @RequestMapping("/delete")
//    public String delete(@RequestParam("reviewId") HashMap<String, String> param) {
//    public String delete(@RequestParam("reviewId") String reviewId) {
    public String delete(@RequestParam("reviewId") String reviewId, @ModelAttribute("cri") Criteria cri,
                         RedirectAttributes rttr) {
        log.info("@# delete");

        service.delete(reviewId);
        rttr.addAttribute("pageNum",cri.getPageNum());
        rttr.addAttribute("amount",cri.getAmount());
        return "redirect:list";
    }

//    첨부파일 업로드
//    produce 속성 :전송되는 json데이터 인코딩해서 이미지 파일이름 한글이어도 안깨지도록 해줌
    @RequestMapping (value="/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    반환타입이 ResponseEntity 객체ㅡ http의 body 에 추가될 데이터는 List<AttachImageVO> 임
//    public ResponseEntity<List<AttachImageVO>> uploadajaxAction(@RequestParam("reviewId") int reviewId, MultipartFile[] uploadFile){
    public ResponseEntity<List<AttachImageVO>> uploadajaxAction(MultipartFile[] uploadFile){

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

                List<AttachImageVO> list = null;
                return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);

            }

        }

//      String uploadFolder = "C:\\upload";
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
        List<AttachImageVO> list = new ArrayList();

        for(MultipartFile multipartFile : uploadFile) {

//            이미지 정보 객체
            AttachImageVO vo = new AttachImageVO();


            /* 파일 이름 */
            String uploadFileName = multipartFile.getOriginalFilename();
            vo.setFileName(uploadFileName);

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

            /* 파일 저장 */
            try {
                multipartFile.transferTo(saveFile);

//                thumnailator사용
                File thumbnailFile = new File(uploadPath, "s_" + uploadFileName);

                BufferedImage bo_image = ImageIO.read(saveFile);

                //비율
                double ratio = 3;
                //넓이 높이
                int width = (int) (bo_image.getWidth() / ratio);
                int height = (int) (bo_image.getHeight() / ratio);

                Thumbnails.of(saveFile)
                        .size(width, height)
                        .toFile(thumbnailFile);

            } catch (Exception e) {
                e.printStackTrace();
            }
            service.imageEnroll(vo);
            list.add(vo);
        }//for
//        http의 body에 추가될 데이터는 List<AttachImageVO>이고, 상태코드가 Ok(200)인 ResponseEntity 객체 생성
        ResponseEntity<List<AttachImageVO>> result = new ResponseEntity<>(list, HttpStatus.OK);
        return result;
    }



    /////////////////////////////////////댓글관련//////////////////////////////////



}
