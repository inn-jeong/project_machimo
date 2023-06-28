package com.example.project_machimo.review.controller;

import com.example.project_machimo.review.dao.ReviewDao;
import com.example.project_machimo.review.dto.Criteria;
import com.example.project_machimo.review.dto.PageDTO;
import com.example.project_machimo.review.dto.ReplyDto;
import com.example.project_machimo.review.dto.ReviewDto;
import com.example.project_machimo.review.service.ReviewService;
import com.example.project_machimo.utils.UploadFileUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Controller
//@RestController
//@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    private ReviewService service;

    @Value("${uploadPath}")
    private String uploadPath;

    @RequestMapping("/list_old")
    public String list(Model model) {
        log.info("@# list");
        ArrayList<ReviewDto> list = service.list();
        model.addAttribute("list",list);
        return "review/list";
    }

    @RequestMapping("/list")
    public String list(Criteria cri, Model model) {
        log.info("@# list");
        log.info("@#cri =======>"+cri);
        log.info("@#getPageNum() =======>"+cri.getPageNum());
//        ArrayList<ReviewDto> list = service.list();
        model.addAttribute("list",service.list(cri));
        int total = service.getTotalCount();
        model.addAttribute("pageMaker", new PageDTO(total, cri));
        return "review/list";
    }
    @RequestMapping("/write_view")
    public String writeView() {
        log.info("@# writeView");
        return "review/write_view";
    }

//    @RequestMapping("/write")
//    public String write(@RequestParam HashMap<String, String> param) {
//        log.info("@# write");
//        service.write(param);
//        return "redirect:list";
//    }

    @RequestMapping("/write")
    public String write(@RequestParam HashMap<String, String> param,
                        @RequestParam("file")  MultipartFile file) throws Exception {

        // 파일 업로드 로직
//        String imgUploadPath = uploadPath + File.separator + "imgUpload";
//        String imgUploadPath = uploadPath;
//        String ymdPath = UploadFileUtils.calcPath(imgUploadPath);
        String ymdPath = UploadFileUtils.calcPath(uploadPath);


//        String buffer = "";
////        buffer = uploadPath + "/imgUpload/" + ymdPath ;
//        buffer = uploadPath + ymdPath ;
//        log.info("@@@ uploadPath + ymdPath => " + buffer);
//
//        File uploadPath = new File(buffer);
//        if ( uploadPath.exists() == false ) {
//           uploadPath.mkdirs();
//           log.info("경로 없어서 직접 만듦");
//        }
//

        String fileName = null;
//        log.info("fileName"+file.getOriginalFilename());

        if (file != null && !file.isEmpty()) {
//            fileName = UploadFileUtils.fileUpload(imgUploadPath, file.getOriginalFilename(), file.getBytes(), ymdPath);
            fileName = UploadFileUtils.fileUpload(uploadPath, file.getOriginalFilename(), file.getBytes(), ymdPath);
        } else {
            fileName = uploadPath + File.separator + "images" + File.separator + "none.png";
        }

        // DTO나 엔티티 객체에 파일 경로 설정
//        param.put("reviewImg", File.separator + "imgUpload" + ymdPath + File.separator + fileName);
//        param.put("reviewImg", ymdPath+ File.separator +fileName);
        param.put("reviewImg",uploadPath+ File.separator+ ymdPath+ File.separator +fileName);



//        param.put("reviewThum", uploadPath + File.separator + "imgUpload" + ymdPath + File.separator + "s" + File.separator + "s_" + fileName);
//        param.put("reviewThum", ymdPath + File.separator + "s" + File.separator + "s_" + fileName);
        param.put("reviewThum",uploadPath+ File.separator+ ymdPath + File.separator + "s" + File.separator + "s_" + fileName);



        service.write(param);
        return "redirect:list";
    }

    @GetMapping("/display")
    @ResponseBody
    public ResponseEntity<byte[]> getFile(String fileName) {


        // 리턴용 객체와 파일 조회용 객체생성
        ResponseEntity<byte[]> result = null;
        File file = new File(fileName);


        try {

            // 화면에 무슨 타입으로 보여줄지 + 해당 타입으로 뭘 보여줄지 작성
            HttpHeaders header = new HttpHeaders();
            header.add("Content-Type", Files.probeContentType(file.toPath()));
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);


            // 오류발생했을땐 해당 메시지 + null 값 리턴
        } catch (Exception e) { e.printStackTrace();}
        return result;
    }

    @RequestMapping("/content_view")
    public String contentView(@RequestParam HashMap<String, String> param, Model model) {
        log.info("@# contentView");
        ReviewDto dto = service.contentView(param);
        model.addAttribute("content_view", dto);
        service.updateCount(dto.getReviewId());
//        model.addAttribute("hit", hit);
//        content_view.jsp 에서 pageMaker를 가지고 페이징 처리
        model.addAttribute("pageMaker", param);
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




    /////////////////////////////////////댓글관련//////////////////////////////////

    @RequestMapping(value="/replyList", method=RequestMethod.GET)
    @ResponseBody
    public List<ReplyDto> replyList(@RequestParam("reviewId")int reviewId){
        return service.getReply(reviewId);
    }
    @RequestMapping(value="/writeReply", method=RequestMethod.POST)
    public String writeReply(
            @RequestParam("id")int id,
            @RequestParam("replyid")int replyid,
            @RequestParam("contents")String contents) {
//
//    public String writeReply(@RequestParam HashMap<String,String> param,
//                             @RequestParam("reviewId")int reviewId) {

//        ReviewDto dto = service.addReply(param);
//        model.addAttribute("content_view", dto);
//        service.addReply(new ReplyDto(param));
        return "redirect:content_view?id=" +id;
    }




}
