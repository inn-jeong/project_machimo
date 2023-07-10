package com.example.project_machimo.jolocal.admin.service;

import com.example.project_machimo.jolocal.admin.dto.CommentVO;

import java.util.List;

public interface CommentService {
    List<CommentVO> commentListService(int bno);
    int commentInsertService(CommentVO comment);
    int commentUpdateService(CommentVO comment);
    int commentDeleteService(int cno);

}
