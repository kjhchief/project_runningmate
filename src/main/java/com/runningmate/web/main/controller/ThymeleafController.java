package com.runningmate.web.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/tl")
@Slf4j
public class ThymeleafController {

	
	
	@GetMapping("/index")
	public String index() {
		return "index_example";
	}
	
	@GetMapping("/main")
	public String main() {
		return "main";
	}
	
	@GetMapping("/boardWrite")
	public String boardWrite() {
		return "boardWrite";
	}
	
	@GetMapping("/yourpage")
	public String yourpage() {
		return "yourpage";
	}
	
	@GetMapping("/faq")
	public String faq() {
		return "faq";
	}
	
	@GetMapping("/FQDetail")
	public String FQDetail() {
		return "FQDetail";
	}
	
	@GetMapping("/board")
	public String board() {
		return "board";
	}
	
	@GetMapping("/joinmembership")
	public String joinmembership() {
		return "joinmembership";
	}
	
//	@GetMapping("/createcrew")
//	public String createcrew() {
//		return "createcrew";
//	}
	
//	@GetMapping("/crewJoin")
//	public String crewJoin() {
//		return "crew/crewJoin";
//	}
	
	@GetMapping("/crewList")
	public String crewList() {
		return "crew/crewList";
	}
	
	@GetMapping("/levelMatchingPage")
	public String levelMatchingPage() {
		return "levelMatchingPage";
	}
	
	@GetMapping("/list")
	public String list() {
		return "list";
	}
	
	@GetMapping("/crewJoinAfter")
	public String crewJoinAfter() {
		return "crew/crewJoinAfter";
	}
	
//	@GetMapping("/login")
//	public String login() {
//		return "login";
//	}
	
	@GetMapping("/memberDetail")
	public String memberDetail() {
		return "memberDetail";
	}
	
	@GetMapping("/memberDetailChange")
	public String memberDetailChange() {
		return "memberDetailChange";
	}
	
	@GetMapping("/mypage")
	public String mypage() {
		return "mypage";
	}
	
	@GetMapping("/noticeDetail")
	public String noticeDetail() {
		return "noticeDetail";
	}
	
	@GetMapping("/questionsDetail")
	public String questionsDetail() {
		return "questionsDetail";
	}
	@GetMapping("/wishlist")
	public String wishlist() {
		return "crew/wishlist";
	}
	@GetMapping("/writeReview")
	public String writeReview() {
		return "writeReview";
	}
	
	
	
	

}
