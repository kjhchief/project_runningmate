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

	// 메인화면 보여주기 메소드
	@GetMapping
	public String mainView(HttpSession httpSession, Model model) {
		crewListDays(model);
		
		return "main";
	}
	
	// 모임 리스트 정보 보여주기
	@GetMapping("/runlist")
	public String ajaxCrewList(HttpSession httpSession, Model model) {
		crewListDays(model);
		
		List<CrewCreate> crewCreate = crewService.allCrew(); 
		for (CrewCreate crewCreate2 : crewCreate) {
			String originDate = crewCreate2.getCrewdate();  
			String end = originDate.substring(11,16);
			crewCreate2.setCrewdate(end);
		}
		model.addAttribute("crew", crewCreate);
		
		List<CrewCreate> onlyDays = crewService.allCrew(); 
		for (CrewCreate crewCreate2 : onlyDays) {
			String onlyDaysOriginDate = crewCreate2.getCrewdate();  
			String onlyDaysEnd = onlyDaysOriginDate.substring(8,10);
			crewCreate2.setCrewdate(onlyDaysEnd);
		}
		model.addAttribute("dates", onlyDays);
		
		log.info("crews= {}", crewCreate);
		
		return "runList";
	}

	
	
	// 헬퍼메소드: 날짜별 모임 리스트 보여주기 기능
	private void crewListDays(Model model) {
		List<DayOfweeks> dayOfweeks = new ArrayList<>(); 
		for (int i = 0; i < 7; i++) {
			DayOfweeks dayOfweeks2 = crewService.calculDay(i);
			dayOfweeks.add(i, dayOfweeks2);
		}
		log.info("날짜, 요일 배열: {}", dayOfweeks);
		model.addAttribute("days", dayOfweeks);
	}

}
