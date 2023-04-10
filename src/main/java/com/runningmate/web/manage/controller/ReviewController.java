package com.runningmate.web.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.runningmate.domain.manage.dto.Review;
import com.runningmate.domain.manage.mapper.ReviewMapper;


@Controller
public class ReviewController {

	@Autowired
	ReviewMapper reviewMapper;
	
	@PostMapping("comment/save")
	public String commentSave(@RequestBody Review review){
		reviewMapper.newReview(review);
		return "코멘트 저장 완료!";
	}
	
	@PostMapping("modal/save")
	public void saveAction(@RequestParam("email") String email,
			@RequestParam("options") List<String> selectedOptions) {
		for (String option : selectedOptions) {
			switch (option) {
			case "good":
				reviewMapper.thumbsUp(email);
				break;
			case "bad":
				reviewMapper.thumbsDown(email);
				break;
			case "noApp":
				reviewMapper.nAppearance(email);
				break;
			default: 
				break;
			}
		}
	}
}
