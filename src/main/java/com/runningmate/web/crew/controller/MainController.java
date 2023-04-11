package com.runningmate.web.crew.controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.runningmate.domain.crew.dto.CrewCreate;
import com.runningmate.domain.crew.dto.DayOfweeks;
import com.runningmate.domain.crew.service.CrewService;

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
		
		// 날짜별 모임 리스트 보여주기 기능
		List<DayOfweeks> dayOfweeks = new ArrayList<>(); 
		for (int i = 0; i < 7; i++) {
			DayOfweeks dayOfweeks2 = crewService.calculDay(i);
			dayOfweeks.add(i, dayOfweeks2);
		}
		log.info("날짜, 요일 배열: {}", dayOfweeks);
		model.addAttribute("days", dayOfweeks);


		return "main";
	}


}
