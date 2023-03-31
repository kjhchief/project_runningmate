package com.runningmate.web.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
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
	
	
	

}
