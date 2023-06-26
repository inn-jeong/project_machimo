package com.example.project_machimo.review.controller;

import com.example.project_machimo.review.dao.ReviewDao;
import com.example.project_machimo.review.dto.Criteria;
import com.example.project_machimo.review.dto.PageDTO;
import com.example.project_machimo.review.dto.ReviewDto;
import com.example.project_machimo.review.service.ReviewService;
import com.example.project_machimo.utils.UploadFileUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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
                        MultipartFile file) throws Exception {

        // 파일 업로드 로직
        String imgUploadPath = uploadPath + File.separator + "imgUpload";
        String ymdPath = UploadFileUtils.calcPath(imgUploadPath);


        String buffer = "";
        buffer = uploadPath + "/imgUpload/" + ymdPath ;
        log.info("@@@ uploadPath + ymdPath => " + buffer);

        File uploadPath = new File(buffer);
        if ( uploadPath.exists() == false ) {
           uploadPath.mkdirs();
           log.info("경로 없어서 직접 만듬");
        }





        String fileName = null;
        log.info("fileName"+file.getOriginalFilename());

        if (file != null && !file.isEmpty()) {
            fileName = UploadFileUtils.fileUpload(imgUploadPath, file.getOriginalFilename(), file.getBytes(), ymdPath);
        } else {
            fileName = uploadPath + File.separator + "images" + File.separator + "none.png";
        }

        // DTO나 엔티티 객체에 파일 경로 설정
        param.put("reviewImg", File.separator + "imgUpload" + ymdPath + File.separator + fileName);
        param.put("reviewThum", File.separator + "imgUpload" + ymdPath + File.separator + "s" + File.separator + "s_" + fileName);

        service.write(param);
        return "redirect:list";
    }



    @RequestMapping("/content_view")
    public String contentView(@RequestParam HashMap<String, String> param, Model model) {
        log.info("@# contentView");
        ReviewDto dto = service.contentView(param);
        model.addAttribute("content_view", dto);
        int hit = service.updateCount();
        model.addAttribute("hit", hit);
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
}
