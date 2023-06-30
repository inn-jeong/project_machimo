package com.example.project_machimo.admin.controller;

import com.example.project_machimo.admin.dto.*;
import com.example.project_machimo.admin.service.AdminService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService service;
    @Autowired
    private HttpSession session;

    //사용자관리//
    @RequestMapping("/adminList")
    public String adminList(Criteria cri, Model model){
        log.info("@# controller adminList start");
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
    public String adminModify(@PathVariable int userId){
        System.out.println("@# controller adminModify userId = "+ userId );
        service.adminModify(userId);
        return "redirect:/admin/adminList";
    }

    @RequestMapping("/userView")
    public String userView(@RequestParam HashMap<String, String> param ,Model model){
        UsersDto dto = service.userView(param);
        model.addAttribute("userView",dto);
        model.addAttribute("pageMaker",param);
        return "admin/userView";
    }




    //공지.문의//
    @RequestMapping("/boardList")
    public String boardList(@RequestParam HashMap<String,Object>param, Criteria cri, Model model){
        System.out.println("@# controller boardList");
        System.out.println("@# cri" + cri);

        //admin 계정활성화
        session.setAttribute("admin",1);

        model.addAttribute("boardList",service.boardList(cri));
        int total = service.getTotalCount();
        model.addAttribute("pageMaker",new PageDto(total,cri));
        return "admin/boardList";
    }

    @RequestMapping(value = "/boardView", method = RequestMethod.GET)
    public String boardView(@RequestParam int boardId, Model model){
        session.setAttribute("userId",1);

//        Integer boardId = Integer.parseInt(String.valueOf(param.get("board_id")));
        service.updateHits(boardId);

        BoardDto dto = service.boardView(boardId);
        model.addAttribute("boardView",dto);
        System.out.println("@# board_id ==> "+boardId);

        return "admin/boardView";
    }

    //게시글 수정 뷰
    @RequestMapping(value = "/boardModify", method = RequestMethod.GET)
    public void boardModifyView(@RequestParam int boardId, Model model){
        System.out.println("@# boardModify start");
        BoardDto dto = service.boardView(boardId);
        model.addAttribute("boardView",dto);
    }

    //게시글 수정
    @RequestMapping(value = "/boardModify", method = RequestMethod.POST)
    public String boardModify(BoardDto dto, Model model){
        System.out.println("@# boardModify");

        service.boardModify(dto);
        return "redirect:/admin/boardView?board_id="+dto.getBoardId();
    }
    //게시글 작성 뷰
    @RequestMapping(value = "/boardWriteView", method = RequestMethod.GET)
    public String boardWriteView(){
//        session.setAttribute("user_id",1);
//        service.boardWrite(dto);
        UsersDto dto = new UsersDto();
        session.setAttribute("user",dto);

        return "/admin/boardWrite";
    }

    @RequestMapping(value = "/boardWrite", method = RequestMethod.POST)
    public String boardWrite(BoardDto dto, Model model){

        service.boardWrite(dto);
        return "redirect:/admin/boardList";
    }

    @RequestMapping(value = "/boardDelete",method = RequestMethod.GET)
    public String boardDelete(@RequestParam int boardId){
        service.boardDelete(boardId);
        return "redirect:/admin/boardList";
    }

    /////////제품관리/////////
    @RequestMapping(value = "productList", method = RequestMethod.GET)
    public String productList(Criteria cri, Model model){
        System.out.println("@# pL start");
        ArrayList<ProductDto> dtos = service.pList(cri);
        model.addAttribute("pList",dtos);
        int total = service.getTotalCount();
        model.addAttribute("pageMaker",new PageDto(total,cri));
        System.out.println("@# ProductId = "+ dtos.get(0).getProductId() );
        return "admin/productList";
    }

    @PostMapping("/status")
    @ResponseBody //반환하는값이 달라짐 보통은 뷰 반환하는데 반환하는 문자열을 그대로 반환한다.
    public String status(@RequestParam String ProductId, @RequestParam String PSalesStatus ){
        int i = Integer.parseInt(ProductId);
        int j = Integer.parseInt(PSalesStatus);
        service.updateStatus(i, j);
        return "ok";
    }

}
