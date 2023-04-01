package com.runningmate.web.crew.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.runningmate.domain.crew.dto.Crew;
import com.runningmate.domain.crew.service.CrewService;

@Controller
@RequestMapping
public class CrewController {
	
	@Autowired 
	private CrewService crewService;
	
	@PostMapping("/main")
	public void createCrew(Crew crew) {
		crewService.createCrew(crew);
	}
	
	@GetMapping("/createcrew")
	public String createcrew() {
		return "createcrew";
	}
	
	

}
