package com.example.project_machimo.review.service;

import com.example.project_machimo.review.dao.CommentMapper;
import com.example.project_machimo.review.dto.CommentRequest;
import com.example.project_machimo.review.dto.CommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentMapper commentMapper;

    /**
     * 댓글 저장
     * @param params - 댓글 정보
     * @return Generated PK
     */
    @Transactional
    public int saveComment(final CommentRequest params) {
        commentMapper.save(params);
        return params.getId();
    }

    /**
     * 댓글 상세정보 조회
     * @param id - PK
     * @return 댓글 상세정보
     */
    public CommentResponse findCommentById(final int id) {
        return commentMapper.findById(id);
    }

    /**
     * 댓글 수정
     *
     * @param params - 댓글 정보
     * @return PK
     */
    @Transactional
    public int updateComment(final CommentRequest params) {
        commentMapper.update(params);
        return params.getId();
    }

    /**
     * 댓글 삭제
     * @param id - PK
     * @return PK
     */
    @Transactional
    public Long deleteComment(final Long id) {
        commentMapper.deleteById(id);
        return id;
    }

    /**
     * 댓글 리스트 조회
     * @param postId - 게시글 번호 (FK)
     * @return 특정 게시글에 등록된 댓글 리스트
     */
    public List<CommentResponse> findAllComment(final Long postId) {
        return commentMapper.findAll(postId);
    }
}
