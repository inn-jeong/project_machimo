package com.example.project_machimo.review.dao;

import com.example.project_machimo.review.dto.CommentRequest;
import com.example.project_machimo.review.dto.CommentResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {


    /**
     * 댓글 저장
     * @param params - 댓글 정보
     */
    void save(CommentRequest params);

    /**
     * 댓글 상세정보 조회
     * @param id - PK
     * @return 댓글 상세정보
     */
    public CommentResponse findById(long id);

    /**
     * 댓글 수정
     * @param params - 댓글 정보
     */
    void update(CommentRequest params);

    /**
     * 댓글 삭제
     * @param id - PK
     */
    void deleteById(long id);

    /**
     * 댓글 리스트 조회
     * @param reviewId - 게시글 번호 (FK)
     * @return 댓글 리스트
     */
    List<CommentResponse> findAll(long reviewId);

    /**
     * 댓글 수 카운팅
     * @param reviewId - 게시글 번호 (FK)
     * @return 댓글 수
     */
//    long count(int reviewId);
}
