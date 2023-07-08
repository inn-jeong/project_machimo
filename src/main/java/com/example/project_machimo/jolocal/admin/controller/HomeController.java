package com.example.project_machimo.jolocal.admin.controller;

import com.example.project_machimo.inn_jeong.login.Dto.UsersDto;
import com.example.project_machimo.jolocal.admin.dto.BoardDto;
import com.example.project_machimo.jolocal.admin.dto.Criteria;
import com.example.project_machimo.jolocal.admin.dto.LocalPageDto;
import com.example.project_machimo.jolocal.admin.service.AdminService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
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
    private AdminService service;


    @GetMapping("/userBoardList")
    public String boardList(Criteria cri, Model model, HttpSession session){
        System.out.println("@# ==> home boardList start");

//        UsersDto user = new UsersDto();
//        user.setUserId(1); // admin 계정
//        user.setUNickname("admin");

//        user.setUserId(0);
//        user.setUNickname("user");
//        user.setUserId(106); // test용 임시 계정
//        UsersDto user = (UsersDto) session.getAttribute("user");
//        session.setAttribute("user",user);

        int total = service.getTotalCount();
        model.addAttribute("boardList",service.boardList(cri));
        model.addAttribute("pageMaker",new LocalPageDto(total, cri));
        return "home/userBoardList";
    }
    @GetMapping("/userQnAList")
    public String userQnaAList(Criteria cri, Model model){
        System.out.println("@# ==> home boardList start");

//        UsersDto user = new UsersDto();
//        user.setUserId(125);
//        user.setUNickname("user");
//        session.setAttribute("user",user);
//        user.setUserId(1); // admin 계정
//        user.setUserId(106); // test용 임시 계정

        int total = service.getTotalCount();
        model.addAttribute("boardList",service.boardList(cri));
        model.addAttribute("pageMaker",new LocalPageDto(total, cri));
        return "home/userQnAList";
    }

    //게시글보기
    @RequestMapping(value = "/boardView", method = RequestMethod.GET)
    public String boardView(@RequestParam int boardId, Model model){
        System.out.println("@# home boardView start");

//        UsersDto user = new UsersDto();
//        user.setUserId(1); //admin
//        session.setAttribute("user",user);
        service.updateHits(boardId);
        int userId = service.loginUser(boardId);
        //로그인한 user 체크
        model.addAttribute("loginUser", service.loginUser(boardId));
        System.out.println("@# ===> 게시글작성자"+userId);

        BoardDto dto = service.boardView(boardId);
        model.addAttribute("boardView",dto);
        System.out.println("@# board_id ==> "+boardId);

        return "home/boardView";
    }


    //게시글 작성 뷰
    @RequestMapping(value = "/boardWriteView", method = RequestMethod.GET)
    public String boardWriteView(Criteria cri, Model model){
        System.out.println("@# boaradWriteView start");

//        UsersDto user = new UsersDto();
//        user.setUserId(1); //admin
//        user.setUNickname("admin");
//        user.setUserId(0); //user
//        user.setUNickname("user");
//        session.setAttribute("user",user);

        int total = service.getTotalCount();
        model.addAttribute("pageMaker",new LocalPageDto(total, cri));

        return "home/boardWrite";
    }
//    //게시글 작성
//    @GetMapping("/boardWrite")
//    public String adminBoardWrite(){
//        System.out.println("@# ==> adminBoardWrite ");
//        UsersDto dto = new UsersDto();
//        session.setAttribute("user",dto);
//        return "home/adminBoardWrite";
//    }
    //게시글 작성
    @PostMapping("/boardWrite")
    @ResponseBody
    public ResponseEntity<? extends Object> boardWrite(BoardDto dto, Model model){
        System.out.println("@# controller boardWrite"+ dto.getBCategory());
        System.out.println("@# boardWrite start");
        service.boardWrite(dto);
        Integer userId = dto.getUserId();
        String bCategory = dto.getBCategory();
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
        model.addAttribute("boardView",service.boardView(boardId));
        return "home/boardModify";
    }
//    @PostMapping("/boardModify/{boardId}")
//    public String boardModify(@PathVariable int boardId, @ModelAttribute BoardDto dto){
//        System.out.println("@# Controller boardModify");
//        UsersDto user = new UsersDto();
//
//        dto.setBoardId(boardId);
//        service.boardModify(dto);
//        String bCategory = dto.getBCategory();
//        int userId = (int) session.getAttribute("userId");
//        System.out.println("@#@# ==> userId "+ userId);
//        System.out.println("@#@# ==> bCategory "+ bCategory);
//        if(userId == 1){ //admin
//            if (bCategory.equals("공지")){
//                return "/home/userBoardList";
//            }else{
//                return "/home/userQnAList";
//            }
//        }else { //사용자
//            return "/home/userQnAList";
//        }
//    }
//

    @PostMapping("/boardModify")
    public ResponseEntity<? extends Object> boardModify(BoardDto dto, HttpSession session){
        CustomRes cRes = new CustomRes();


        System.out.println("@# Controller boardModify");


        //게시글 수정
        service.boardModify(dto);

        String category = dto.getBCategory();
        System.out.println("카테고리" + category);
        int userId = dto.getUserId();
        UsersDto userId1 = (UsersDto) session.getAttribute("user");

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

}
