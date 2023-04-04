package com.runningmate.domain.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.runningmate.domain.manage.dto.Review;
import com.runningmate.domain.mate.dto.Mate;

@Mapper
public interface ReviewMapper {
	public void thumbsUp();
	public void thumbsDown();
	public void nAppearance();
	public void newReview(Review memberMannage);
	public List<Review> findByEmail(String email);
	
}
