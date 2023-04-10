package com.runningmate.web.crew.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runningmate.domain.crew.dto.CrewCreate;
import com.runningmate.domain.crew.dto.CrewMates;
import com.runningmate.domain.crew.dto.CrewPhoto;
import com.runningmate.domain.crew.service.CrewService;
import com.runningmate.domain.manage.dto.CrewList;
import com.runningmate.domain.mate.dto.Mate;

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
	public String register(@ModelAttribute CrewCreate crewCreate) throws IOException {
		
		
		crewService.createCrew(crewCreate);
		
		
		log.info("crew= {}", crewCreate);
		return "redirect:/crew/result";
	}
	// 모임 등록 결과에 대한 메소드
	@GetMapping("/result")
	public String registerResult() {
		return "crew/crew-result";		
	}
	
	// 모임 신청
	@PostMapping("/{crewId}")
	public String joinCrew(@PathVariable String crewId, HttpSession httpSession, Model model) {
		Mate mate = (Mate)httpSession.getAttribute("mate");
		model.addAttribute("mateEmail", mate.getEmail());
	    log.info("모임신청mate : {}", mate);
	    
	    List<CrewMates> crewMates = crewService.getCrews(crewId);
	    
		CrewList crewList = new CrewList();
		crewList.setCrewId(crewId);
		crewList.setEmail(mate.getEmail());
		log.info("crewList: {}", crewList);
		crewService.joinCrew(crewList);
		
		
		
		return "redirect:/crew/{crewId}";
	}
	// 모임 신청 결과에 대한 메소드 (작동 안 됨)
	@GetMapping("/joincrew")
	public String joinCrewResult() {
		return "crew/join";
	}
	
	
	//이미지 출력
	@GetMapping("/images/{name}")
	@ResponseBody
	public ResponseEntity<Resource> showImage(@PathVariable String name) throws IOException {
		Path path = Paths.get(location + "/" + name);
		String contentType = Files.probeContentType(path);
		Resource resource = new FileSystemResource(path);
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, contentType);
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}
	
	
	

	
	// 특정 모임의 모임 참석 화면 보여주기
	@GetMapping("/{crewId}")
	public String JoinCrew(@PathVariable String crewId, HttpSession httpSession, Model model) {
		Mate mate = (Mate)httpSession.getAttribute("mate");
		if(mate==null) {
			return "mate/login";
		}
		log.info("재훈모임mate= {}", mate);
		model.addAttribute("crewId", crewId);
		log.info("crewId= {}", crewId);
		CrewCreate crewCreate = crewService.getCrew(crewId);
		LocalDateTime localDateTime = LocalDateTime.parse(crewCreate.getCrewdate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"));
		model.addAttribute("crewCreate", crewCreate);
		model.addAttribute("crewdate", localDateTime);
		
		List<CrewPhoto> crewPhotos = crewService.getPhotos(crewId);
		model.addAttribute("photos", crewPhotos);
		
		List<CrewMates> crewMates = crewService.getCrews(crewId);
		model.addAttribute("crewMates", crewMates);
		
		CrewMates sessionMate = crewService.sessionMate(mate.getEmail()); // 지금 mate.getEmail로 로그인된 사용자의 email을 못 가져옴(null로 가져옴). 왜????
		log.info("sessionMate= {}", sessionMate);
		if(sessionMate != null) {
			model.addAttribute("sessionMateCrewId", sessionMate.getCrewId()); 
		}
		
		
		
		log.info("crewCreate= {}", crewCreate);
		log.info("photo= {}", crewPhotos);
		log.info("crewMates= {}", crewMates);
		
		return "crew/crewJoin";
		
	}
	
	// 특정 모임의 회원 리스트 보여주기
//	@GetMapping
//	public String mates(Model model) {
//		// 서비스 객체를 이용한 회원 목록
//		
//		return "member/list";
//	}


}
