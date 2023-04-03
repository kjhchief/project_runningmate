package com.runningmate.web.crew.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.runningmate.domain.crew.service.CrewService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/crew")
@Slf4j
public class CrewController {
	
	// 파일 업로드 저장 경로
	@Value("${file.dir}")
	private String location;

	@Autowired
	private CrewService crewService;
	
	@Autowired
	private FileStore fileStore;

	// 모임 만들기 화면 요청 처리 메소드
	@GetMapping
	public String createcrew() {
		return "crew/createcrew";
	}
	
	// 모임 등록
	@PostMapping
	public String register(@ModelAttribute("crewPhotoUp") CrewCreate crewCreate) throws IOException {
		log.info("crew= {}", crewCreate);
		
		// 파라메터는 DB에 저장
		// 파일은 특정 디렉토리에 저장
		List<UploadFile> uploadFiles = fileStore.storeFiles(crewCreate.getUploadfiles());
		
		crewService.createCrew(crewCreate);
		return "redirect:/crew/result";
	}
	
	
	
	// 모임 만들기 결과에 대한 메소드
	@GetMapping("/result")
	public String registerResult() {
		return "crew/crew-result";		
	}


}
