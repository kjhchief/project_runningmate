package com.runningmate.web.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.runningmate.domain.manage.service.ReviewService;
import com.runningmate.domain.mate.dto.Mate;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	@GetMapping("")
	public String crewJoinForm(Model model) {
		return "crew/crewJoin";
	}
	
//	@GetMapping("/{userId}")
//	public String crewUser (@PathVariable String  userId, Model model) {
//		Mate mate;
//		return "crew/crewList";
//	}
}
