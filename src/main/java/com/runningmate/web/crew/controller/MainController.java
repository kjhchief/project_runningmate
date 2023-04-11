package com.runningmate.web.crew.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.runningmate.domain.crew.dto.CrewCreate;
import com.runningmate.domain.crew.service.CrewService;
import com.runningmate.domain.manage.dto.CrewList;
import com.runningmate.domain.mate.dto.Mate;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/")
@Slf4j
public class MainController {


	@Autowired
	private CrewService crewService;

	@GetMapping
	public String mainView(HttpSession httpSession, Model model) {
		// 모임 정보 보여주기
		List<CrewCreate> crewCreate = crewService.allCrew(); 
		for (CrewCreate crewCreate2 : crewCreate) {
			String originDate = crewCreate2.getCrewdate();  
			String end = originDate.substring(11,16);
			crewCreate2.setCrewdate(end);
		}
		
		model.addAttribute("crew", crewCreate);
		log.info("crews= {}", crewCreate);
		
		

		return "main";
	}


}
