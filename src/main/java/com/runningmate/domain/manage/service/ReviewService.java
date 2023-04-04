package com.runningmate.domain.manage.service;

import org.apache.ibatis.annotations.Mapper;
import com.runningmate.domain.manage.dto.Review;

@Mapper
public interface ReviewService {
	public void thumbsUP();
	public void thumbsDown();
	public void addComment();
}
