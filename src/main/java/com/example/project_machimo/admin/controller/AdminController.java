package com.example.project_machimo.admin.controller;

import com.example.project_machimo.admin.dto.BoardDto;
import com.example.project_machimo.admin.dto.Criteria;
import com.example.project_machimo.admin.dto.PageDto;
import com.example.project_machimo.admin.dto.UsersDto;
import com.example.project_machimo.admin.service.AdminService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService service;

    //사용자관리//
    @RequestMapping("/adminList")
    public String adminList(Criteria cri, Model model){
        log.info("@# controller adminList start");
        model.addAttribute("adminList",service.adminList(cri));
        int total = service.getTotalCount();
        model.addAttribute("pageMaker",new PageDto(total, cri));
        return "admin/adminList";
    }

    @GetMapping("/adminDelete/{user_id}")
    public String adminDelete(@PathVariable int user_id){
        service.adminDelete(user_id);
        return "redirect:/admin/adminList";
    }
    @RequestMapping("/adminModify/{user_id}")
    public String adminModify(@PathVariable int user_id){
        System.out.println("@# controller adminModify user_id = "+ user_id );
        service.adminModify(user_id);
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
    public String boardList(@RequestParam HashMap<String,Object>param, Criteria cri, Model model, HttpSession session){
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
    public String boardView(@RequestParam int board_id, Model model, HttpSession session){
        session.setAttribute("user_id",1);
//        Integer boardId = Integer.parseInt(String.valueOf(param.get("board_id")));
        service.updateHits(board_id);

        BoardDto dto = service.boardView(board_id);
        model.addAttribute("boardView",dto);
//        System.out.println("@# category ==> "+dto.getB_category());
        System.out.println("@# board_id ==> "+board_id);

        return "admin/boardView";
    }

    @RequestMapping(value = "/boardModify", method = RequestMethod.GET)
    public String boardModifyView(@RequestParam int board_id, Model model){
        System.out.println("@# boardModify start");
        BoardDto dto = service.boardView(board_id);
        model.addAttribute("boardView",dto);
        return "admin/boardModify";
    }

    @RequestMapping(value = "/boardModify", method = RequestMethod.POST)
    public String boardModify(BoardDto dto, Model model){
        System.out.println("@# boardModify");
        System.out.println("@# b_category ="+dto.getB_category());
        System.out.println("@# qna ="+dto.getInquiry_category());

        service.boardModify(dto);
        return "redirect:admin/boardModify?board_id="+dto.getBoard_id();
    }

//    @RequestMapping(value = "/boardModify/{board_id}", method = RequestMethod.GET)
//    public String boardModify(@PathVariable int board_id, Model model, HttpSession session){
//         service.boardModify(board_id);
//
//    }

//    @RequestMapping("/adminModify/{user_id}")
//    public String adminModify(@PathVariable int user_id){
//        System.out.println("@# controller adminModify user_id = "+ user_id );
//        service.adminModify(user_id);
//        return "redirect:/admin/adminList";
//    }


//    @RequestMapping("/boardView")
//    public String boardView(@RequestParam HashMap<String,Object>param, Model model, HttpSession session){
//        System.out.println("@# boardId = "+param.get("board_id"));
//
//        //admin 계정활성화
//        session.setAttribute("admin",1);
//
//
//        BoardDto dto = service.boardView(param);
//        model.addAttribute("boardView",dto);
//        model.addAttribute("pageMaker",param);
//        return "admin/boardView";
//    }

    //게시글 작성
    @RequestMapping(value = "/boardWrite", method = RequestMethod.POST)
    public String boardWrite(BoardDto dto, HttpSession session){
        session.setAttribute("user_id",1);
        service.boardWrite(dto);
        return "/admin/boardWrite";
    }


//    @RequestMapping("/boardWrite")
//    public String boardWrite(@RequestParam HashMap<String, Object>param, HttpSession session){
//        System.out.println("@# controller boardWrite");
//        System.out.println("@# controller inquiryType " + param.get("inquiryType"));
//        System.out.println("@# controller b_category " + param.get("b_category"));
//        System.out.println("@# controller b_title " + param.get("b_title"));
//        System.out.println("@# controller b_content " + param.get("b_content"));
//        System.out.println("@# controller user_id " + session.getAttribute("user_id"));
//        String user_id = (String) session.getAttribute("user_id");
//
//
//        //admin 계정활성화
//        session.setAttribute("admin",1);
//
////        session.setAttribute("dto",new UsersDto());
////        UsersDto dto = (UsersDto) session.getAttribute("dto");
////        int userId = dto.getUser_id();
//
//
////        //세션에 값을 저장하는 Map 객체 생성
////        Map<String, Integer> sessionData = new HashMap<>();
////        sessionData.put("admin_id", 1); //admin
////        sessionData.put("user_id", 0); //user
////        // 세션에 Map 객체를 저장
////        session.setAttribute("user_id", sessionData);
//
//        service.boardWrite(param);
//        return "/admin/boardList";
//    }

//    @RequestMapping("/boardModify/{board_id}")
//    public String boardModify(@PathVariable int board_id,
//                              HttpServletRequest request
//                              @RequestParam HashMap<String, Object> param,
//                              @ModelAttribute("cri") Criteria cri,
//                              RedirectAttributes rttr){
//
////        쿼리스트링,post 값 넘겨줄때 사용
//        String parameter = request.getParameter(String.valueOf(board_id));
//
////        값만 넘겨줄때 PathVariable
//
//
//        System.out.println("@# controller boardModify");
//        service.boardModify("board_id");
//        rttr.addAttribute("pageNum",cri.getPageNum());
//        rttr.addAttribute("amount",cri.getAmount());
//
//        return "redirect:admin/boardList";



//    }

    //어드민 공지사항
//    @RequestMapping("/boardMgmt")
//    public String boardMgmt(Criteria cri, Model model, HttpSession session){
//        System.out.println("@# controller boardList");
//
//        //admin 계정활성화
//        session.setAttribute("admin",1);
//
//        model.addAttribute("boardList",service.boardList(cri));
//        int total = service.getTotalCount();
//        model.addAttribute("pageMaker",new PageDto(total,cri));
//        return "admin/boardMgmt";
//    }
}
