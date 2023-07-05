package com.example.project_machimo.admin.controller;

import com.example.project_machimo.admin.dto.*;
import com.example.project_machimo.admin.service.AdminService;
import com.example.project_machimo.admin.service.ProductStatusService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;


@Controller
@Slf4j
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService service;
    @Autowired
    private HttpSession session;

    private final ProductStatusService productStatusService;

    @Autowired
    public AdminController(ProductStatusService productStatusService) {
        this.productStatusService = productStatusService;
    }

    //사용자관리//

    @RequestMapping("/index")
    public String main(){
        return "admin/index";
    }


    @RequestMapping("/adminList")
    public String adminList(Criteria cri, Model model, HttpSession session){
        System.out.println("@# adminList start");
        //admin session
        UsersDto user = new UsersDto();
        user.setUserId(1); //admin
        user.setUNickname("admin");
        session.setAttribute("user",user);

        model.addAttribute("adminList",service.adminList(cri));
        int total = service.getTotalCount();
        model.addAttribute("pageMaker",new PageDto(total, cri));
        return "admin/adminList";
    }


    @GetMapping("/adminDelete/{userId}")
    public String adminDelete(@PathVariable int userId){
        service.adminDelete(userId);
        return "redirect:/admin/adminList";
    }
    @RequestMapping("/adminModify/{userId}")
    public ResponseEntity<?> adminModify(@PathVariable Integer userId){
        System.out.println("@# controller adminModify userId = "+ userId );
        service.adminModify(userId);
        return ResponseEntity.ok().build();
    }
//    @RequestMapping("/adminModify/{userId}")
//    public String adminModify(@PathVariable String userId){
//        System.out.println("@# controller adminModify userId = "+ userId );
//        int uId = Integer.parseInt(userId);
//        service.adminModify(uId);
//        return "redirect:/admin/adminList";
//    }


//    @RequestMapping("/userView")
    @GetMapping("/userView")
    public String userView(@RequestParam int userId, Model model){
        System.out.println("@# adminList userView");

//        UsersDto dto = service.userView(userId);
        model.addAttribute("userView",service.userView(userId));
//        model.addAttribute("pageMaker",param);
        return "admin/userView";
    }



    //신고삭제
    @PostMapping("/removeReport")
    public String removeReport(@RequestParam int reportId,
                               @RequestParam int userId,
                               @RequestParam int productId){
        System.out.println("@# ==> removeReport");
        service.removeReport(reportId, userId, productId);
        return "";
    }

    //회원차단
    @PostMapping("/blockUser/{userId}")
    public String blockUser(@RequestParam int userId, @RequestParam int productId){
        return "";
    }



    //공지.문의//
    @RequestMapping("/boardList")
    public String boardList(@RequestParam HashMap<String,Object>param, Criteria cri, Model model, HttpSession session){
        System.out.println("@# controller boardList");

        UsersDto user = new UsersDto();
        user.setUserId(1); //admin
        user.setUNickname("admin");
        session.setAttribute("user",user);

        model.addAttribute("boardList",service.boardList(cri));
        int total = service.getTotalCount();
        model.addAttribute("pageMaker",new PageDto(total,cri));

        return "admin/boardList";
    }

    @RequestMapping(value = "/boardView", method = RequestMethod.GET)
    public String boardView(@RequestParam int boardId, Model model){
        System.out.println("@# boardView start");

        service.updateHits(boardId);

        BoardDto dto = service.boardView(boardId);
        model.addAttribute("boardView",dto);
        System.out.println("@# board_id ==> "+boardId);

        return "admin/boardView";
    }

    //게시글 작성 뷰
    @RequestMapping(value = "/boardWriteView", method = RequestMethod.GET)
    public String boardWriteView(){
        System.out.println("@# boaradWriteView start");
        UsersDto dto = new UsersDto();
        session.setAttribute("user",dto);

        return "/admin/boardWrite";
    }

    //게시글 작성
    @PostMapping("/boardWrite")
    @ResponseBody
    public String boardWrite(BoardDto dto, Model model){
        System.out.println("@# controller boardWrite"+ dto.getBCategory());
        System.out.println("@# boardWrite start");
        service.boardWrite(dto);
        return "writeOk";
    }
    @GetMapping("/boardModifyView/{boardId}")
    public String boardModifyView(@PathVariable int boardId, Model model){
        model.addAttribute("boardView",service.boardView(boardId));
        return "admin/boardModify";
    }

    @PostMapping("/boardModify/{boardId}")
    public String boardModify(@PathVariable int boardId, @ModelAttribute BoardDto dto){
        System.out.println("@# Controller boardModify");
        UsersDto user = new UsersDto();
        user.setUserId(1); //admin
        user.setUNickname("admin");
        session.setAttribute("user",user);

        dto.setBoardId(boardId);

        service.boardModify(dto);
        return "redirect:/admin/boardList";
    }

    @PostMapping("/boardDelete")
    @ResponseBody
    public String boardDelete(@RequestParam String boardId){
        System.out.println("@# controller boardDelete start");
        int bId = Integer.parseInt(boardId);
        service.boardDelete(bId);
        return "deleteOk";
    }


    /////////제품관리/////////
    @RequestMapping(value = "productList", method = RequestMethod.GET)
    public String productList(Criteria cri, Model model, HttpSession session){

        System.out.println("@# pL start");
        ArrayList<ProductDto> dtos = service.pList(cri);
        model.addAttribute("pList",dtos);
        int total = service.getTotalCount();
        model.addAttribute("pageMaker",new PageDto(total,cri));
        return "admin/productList";
    }

    @PostMapping("/status")
    @ResponseBody
    public ResponseEntity<?> status(@RequestBody StatusDto dto ){
        System.out.println("@# status start");
        log.info("dto=={}",dto);
        int update = productStatusService.update(dto);

        if(update == 1){ //업데이트 정상작동 여부 (성공이면 1)
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().body("판매 승인에 실패했습니다.");
        }

    }

    @PostMapping("/productDelete")
    @ResponseBody
    public String productDelete(@RequestParam String ProductId){
        System.out.println("@# productDelte start");
        int pId = Integer.parseInt(ProductId);
        service.productDelete(pId);
        return "ok";
    }

}
