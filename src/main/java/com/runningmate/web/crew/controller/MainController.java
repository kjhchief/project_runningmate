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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	private String forButtonDate(int i) {
		DayOfweeks dayOfweeks = new DayOfweeks();
		dayOfweeks = crewService.calculDay(i);
		return dayOfweeks.getMonthDay();
	}
	
	// 모임 리스트 정보 보여주기
	@GetMapping("/runlist")
	public String ajaxCrewList(HttpSession httpSession, Model model) {
		crewListDays(model);
		
		// 모든 모임 리스트
		List<CrewCreate> crewCreate = crewService.allCrew(); 
		for (CrewCreate crewCreate2 : crewCreate) {
			String originDate = crewCreate2.getCrewdate();  
			String end = originDate.substring(11,16);
			crewCreate2.setCrewdate(end);
		}
		model.addAttribute("crew", crewCreate);
		
		// 특정 날짜 모임 리스트
		List<CrewCreate> dateCrews = crewService.findBydate("04/07");
		model.addAttribute("dateCrews", dateCrews);
		
//		특정 날짜 모임 리스트 => 매개변수 못 불러옴 순서가 잘 못 된듯.
//		String endItem = item.substring(item.length(), item.length()-5);
//		List<CrewCreate> dateCrews = crewService.findBydate(endItem);
//		model.addAttribute("dateCrew", dateCrews);
		
		//이건 뭐더라...
		List<CrewCreate> onlyDays = crewService.allCrew(); 
		for (CrewCreate crewCreate2 : onlyDays) {
			String onlyDaysOriginDate = crewCreate2.getCrewdate();  
			String onlyDaysEnd = onlyDaysOriginDate.substring(6,10);
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
