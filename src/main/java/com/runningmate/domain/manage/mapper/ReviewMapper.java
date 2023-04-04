package com.runningmate.domain.manage.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.runningmate.domain.manage.dto.Review;
import com.runningmate.domain.mate.dto.Mate;

@Mapper
public interface ReviewMapper {
	public void thumbsUp(Mate mate);
	public void thumbsDown(Mate mate);
	public void nAppearance(Mate mate);
	public void newReview(Review memberMannage);
}
