package com.runningmate.web.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.runningmate.domain.crew.dto.CrewMates;
import com.runningmate.domain.crew.service.CrewService;
import com.runningmate.domain.manage.dto.Review;
import com.runningmate.domain.manage.dto.goodBad;
import com.runningmate.domain.manage.mapper.ReviewMapper;
import com.runningmate.web.mate.controller.MateController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/{crewId}/modal")
public class ReviewController {

	@Autowired
	private ReviewMapper reviewMapper;
	@Autowired
	private CrewService crewService;
	
	@GetMapping()
	public Model showModal(@PathVariable String crewId, Model model) {
		List<CrewMates> crewMates = crewService.getCrews(crewId);
		model.addAttribute("crewMates", crewMates);
		return model;
	}
	
	
	@PostMapping("comment/save")
	public String commentSave(@RequestBody Review review){
		reviewMapper.newReview(review);
		return "코멘트 저장 완료!";
	}
	
	
	@PostMapping("/save")
	public void saveAction(@RequestBody List<goodBad> memberEv) {
		for (goodBad json : memberEv) {
			String value = json.getValue();
			String email = json.getEmail();
			switch (value) {
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
