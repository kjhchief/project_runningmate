package com.runningmate.domain.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.runningmate.domain.manage.dto.Review;
import com.runningmate.domain.manage.mapper.ReviewMapper;

public class ReviewServiceImpl implements ReviewService{

	@Autowired
	private ReviewMapper reviewMapper;

	@Override
	public List<Review> getReviews() {
		return null;
	}
	

}
