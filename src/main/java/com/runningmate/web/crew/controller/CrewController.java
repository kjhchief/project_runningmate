package com.runningmate.web.crew.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.runningmate.domain.crew.dto.Crew;
import com.runningmate.domain.crew.service.CrewService;
import com.runningmate.domain.member.dto.Members;
import com.runningmate.web.member.controller.MembersController;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/crew")
@Slf4j
public class CrewController {

	@Autowired
	private CrewService crewService;

	// 모임 만들기 요청 처리 메소드
	@GetMapping
	public String createcrew() {
		return "createcrew";
	}
	
	@PostMapping
	public String register(@ModelAttribute("crew") Crew crew) {
		log.info("crew= {}", crew);
		crewService.createCrew(crew);
		return "redirect:/crew/result";
	}
	
	// 모임 만들기 결과에 대한 메소드
	@GetMapping("/result")
	public String registerResult() {
		return "crew-result";		
	}


}
