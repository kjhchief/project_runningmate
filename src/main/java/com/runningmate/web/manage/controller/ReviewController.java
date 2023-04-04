package com.runningmate.web.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.runningmate.domain.manage.mapper.ReviewMapper;


@Controller
public class ReviewController {

	@Autowired
	ReviewMapper reviewMapper;
	
	@PostMapping("modal/save")
	public void saveAction(@RequestParam("mate") String mate,
			@RequestParam("options") List<String> selectedOptions, 
			@RequestParam("comment") String comment){
		for (String option : selectedOptions) {
			switch (option) {
			case "good":
				reviewMapper.thumbsUp();
				break;
			case "bad":
				break;
			case "noApp":
				break;
			default: 
				break;
			}
		}
	}
}
