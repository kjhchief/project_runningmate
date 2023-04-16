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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.runningmate.domain.crew.dto.CrewCreate;
import com.runningmate.domain.crew.dto.DayOfweeks;
import com.runningmate.domain.crew.service.CrewService;
import com.runningmate.domain.mate.dto.Mate;

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
		List<DayOfweeks> dayOfweeks = new ArrayList<>(); 
		for (int i = 0; i < 7; i++) {
			DayOfweeks dayOfweeks2 = crewService.calculDay(i);
			dayOfweeks.add(i, dayOfweeks2);
		}
		model.addAttribute("days", dayOfweeks);
		//로그인 회원 정보
		Mate mate = (Mate)httpSession.getAttribute("mate");
		model.addAttribute("mate", mate);
		
		//카카오로그인 회원 정보
		Mate kakaoMate = (Mate)httpSession.getAttribute("kakaoMate");
		model.addAttribute("kakaoMate", kakaoMate);
		log.info("kakaoMate : {}", kakaoMate);
		
		// 모든 모임 리스트
		CrewCreate Crew = new CrewCreate();
		model.addAttribute("Crew", Crew);
		
		return "main";
	}
}
