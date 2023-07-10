package com.example.project_machimo.jolocal.admin.controller;

import com.example.project_machimo.jolocal.admin.dto.BoardDto;
import com.example.project_machimo.jolocal.admin.dto.Criteria;
import com.example.project_machimo.jolocal.admin.dto.LocalPageDto;
import com.example.project_machimo.jolocal.admin.dto.UsersDto1;
import com.example.project_machimo.jolocal.admin.service.AdminService;
import com.example.project_machimo.jolocal.admin.service.HomeService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private HomeService service;
    @Autowired
    private AdminService aservice;

    //유저가보는 공지//
    @GetMapping("/userBoardList")
    public String boardList(Criteria cri, Model model, HttpSession session){
        System.out.println("@# ==> home boardList start");

        UsersDto1 user = new UsersDto1();
        user.setUserId(1);
        user.setUNickname("admin");
        user.setURole(1);

        session.setAttribute("user",user);
        int total = service.getTotalCount();
        model.addAttribute("boardList",service.userBoardList(cri));
        model.addAttribute("pageMaker",new LocalPageDto(total, cri));
        return "home/userBoardList";
    }


    //게시글보기
    @RequestMapping(value = "/boardView", method = RequestMethod.GET)
    public String boardView(@RequestParam int boardId, Model model, HttpSession session){
        System.out.println("@# home boardView start");

        service.updateHits(boardId);
        int userId = aservice.loginUser(boardId);

        //로그인한 user 체크
        model.addAttribute("loginUser", aservice.loginUser(boardId));
        System.out.println("@# ===> 게시글작성자"+userId);

        BoardDto dto = aservice.boardView(boardId);
        model.addAttribute("boardView",dto);
        System.out.println("@# board_id ==> "+boardId);

        return "home/boardView";
    }

    //게시글 작성 뷰
    @RequestMapping(value = "/boardWrite", method = RequestMethod.GET)
    public String boardWrite(Criteria cri, Model model){
        System.out.println("@# boaradWriteView start");

        int total = service.getTotalCount();
        model.addAttribute("pageMaker",new LocalPageDto(total, cri));

        return "home/boardWrite";
    }

    //게시글 작성
    @PostMapping("/boardWrite")
    @ResponseBody
    public ResponseEntity<? extends Object> boardWrite(BoardDto dto, Model model){
        System.out.println("@# controller boardWrite"+ dto.getBCategory());
        System.out.println("@# boardWrite start");
        aservice.boardWrite(dto);

        //관리자인지 유저인지 확인후 분기처리
        Integer userId = dto.getUserId();
        String page;
        if(userId==1){
            page="admin";
        }else{
            page="user";
        }
        return ResponseEntity.ok().body(page);
    }

    @GetMapping("/boardModifyView/{boardId}")
    public String boardModifyView(@PathVariable int boardId, Model model){
        model.addAttribute("boardView",aservice.boardView(boardId));
        return "home/boardModify";
    }

    @PostMapping("/boardModify")
    public ResponseEntity<? extends Object> boardModify(BoardDto dto, HttpSession session){
        CustomRes cRes = new CustomRes();

        System.out.println("@# Controller boardModify");

        //게시글 수정
        aservice.boardModify(dto);

        String category = dto.getBCategory();
        System.out.println("카테고리" + category);
        int userId = dto.getUserId();
        UsersDto1 userId1 = (UsersDto1) session.getAttribute("user");

        log.info("유우우우저 {}",userId);
        String page;
        if(userId==1){
            if (category.equals("공지")){
                page="gongadmin";
            }else{
                page="monadmin";
            }
        }else{
            page="user";
        }
        cRes.setMessage(page);
        return ResponseEntity.ok().body(cRes);
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class CustomRes {
        private String message;
    }


    //유저가보는 문의//
//    @GetMapping("/userQnAList")
//    public String userQnaAList(Criteria cri, Model model){
//        System.out.println("@# ==> home boardList start");
//
//        int total = service.getTotalCount();
//        model.addAttribute("boardList",service.boardList(cri));
//        model.addAttribute("pageMaker",new PageDto(total, cri));
//        return "home/userQnAList";
//    }

//    @GetMapping("/규리지앵")
//    public String 규리지앵(){
//        return"home/규리지앵";
//    }
}
