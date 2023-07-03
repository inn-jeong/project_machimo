package com.example.project_machimo.review.dao;

import com.example.project_machimo.AttachImageVO;
import com.example.project_machimo.review.dto.Criteria;
import com.example.project_machimo.review.dto.ReviewDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;

@Mapper
public interface ReviewDao {
    ArrayList<ReviewDto> list();
    ArrayList<ReviewDto> listWithPaging(Criteria cri);
    void write(HashMap<String,String> param);
//    ReviewDto write(HashMap<String,String> param);

    ReviewDto getReviewById(int reviewId);
    ReviewDto contentView(HashMap<String,String> param);
    void modify(HashMap<String,String> param);

    ReviewDto modify_view(String reviewId);

    void delete(String param);

    int getTotalCount();

    // 게시글 조회수 1씩증가
   void updateCount(int reviewId);

    /* 이미지 등록 */
    void imageEnroll(AttachImageVO vo);


    /////////////////////////////////댓글/////////////////////////////////

//
//    void insertComment(CommentVO dto);
//
//    List<CommentVO> selectCommentList(CommentVO dto);
}
