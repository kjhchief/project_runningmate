package com.runningmate.domain.manage.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.runningmate.domain.manage.dto.Review;

@Mapper
public interface ReviewService {
	
	// 받은 코멘트 조회
	public List<Review> getReviews();
}
