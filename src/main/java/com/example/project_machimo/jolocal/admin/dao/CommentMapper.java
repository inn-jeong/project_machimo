package com.example.project_machimo.jolocal.admin.dao;

import com.example.project_machimo.jolocal.admin.dto.CommentVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface CommentMapper {
    List<CommentVO> commentList(int bno);

    int commentInsert(CommentVO comment);

    int commentUpdate(CommentVO comment);

    int commentDelete(int cno);
}
