package com.example.project_machimo.jomuragi.review.controller;

import com.example.project_machimo.jomuragi.productEnroll.dto.UsersDto;
import com.example.project_machimo.jomuragi.review.dto.CommentVO;
import com.example.project_machimo.jomuragi.review.service.CommentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService mCommentService;


        @RequestMapping("/list") //댓글 리스트
        @ResponseBody
        private List<CommentVO> mCommentServiceList(Model model, int bno) throws Exception{
//        private List<CommentVO> mCommentServiceList(Model model) throws Exception{

            return mCommentService.commentListService(bno);
//            return mCommentService.commentListService();
        }

        @RequestMapping("/insert") //댓글 작성
        @ResponseBody
        private int mCommentServiceInsert(@RequestParam int bno, @RequestParam String content, HttpSession session) throws Exception{

            CommentVO comment = new CommentVO();
            comment.setBno(bno);
            comment.setContent(content);
            //로그인 기능을 구현했거나 따로 댓글 작성자를 입력받는 폼이 있다면 입력 받아온 값으로 사용하면 됨
//            comment.setWriter("test");
            comment.setWriter(((UsersDto)session.getAttribute("user")).getUNickname());

            return mCommentService.commentInsertService(comment);
        }

        @RequestMapping("/update") //댓글 수정
        @ResponseBody
        private int mCommentServiceUpdateProc(@RequestParam int cno, @RequestParam String content) throws Exception{

            CommentVO comment = new CommentVO();
            comment.setCno(cno);
            comment.setContent(content);

            return mCommentService.commentUpdateService(comment);
        }

        @RequestMapping("/delete/{cno}") //댓글 삭제
        @ResponseBody
        private int mCommentServiceDelete(@PathVariable int cno) throws Exception{

            return mCommentService.commentDeleteService(cno);
        }
}
