package com.runningmate.domain.manage.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.runningmate.domain.manage.dto.Review;
import com.runningmate.domain.mate.dto.Mate;

@Mapper
public interface ReviewMapper {
	public void thumbsUp(String email);
	public void thumbsDown(String email);
	public void nAppearance(String email);
	public void newReview(Review review);
	public List<Review> getReviews();
}
