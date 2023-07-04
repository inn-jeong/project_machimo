package com.example.project_machimo.review.dao;

import com.example.project_machimo.review.dto.AttachImageVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AttachMapper {

    /* 이미지 데이터 반환 */
    public List<AttachImageVO> getAttachList(int reviewId);
}
