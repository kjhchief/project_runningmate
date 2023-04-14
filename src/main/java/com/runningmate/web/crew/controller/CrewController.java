package com.runningmate.web.crew.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runningmate.domain.crew.dto.CrewCreate;
import com.runningmate.domain.crew.dto.CrewMates;
import com.runningmate.domain.crew.dto.CrewPhoto;
import com.runningmate.domain.crew.dto.DayOfweeks;
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
	
	// 헬퍼메소드: 날짜별 모임 리스트 보여주기 기능
	private void crewListDays(Model model) {
		List<DayOfweeks> dayOfweeks = new ArrayList<>(); 
		for (int i = 0; i < 7; i++) {
			DayOfweeks dayOfweeks2 = crewService.calculDay(i);
			dayOfweeks.add(i, dayOfweeks2);
		}
		model.addAttribute("days", dayOfweeks);
	}
	
	//이미지 출력 기능
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

	// 모임 만들기 화면 요청 처리 메소드
	@GetMapping
	public String createcrew(HttpSession httpSession) {
		Mate mate = (Mate)httpSession.getAttribute("mate");
		if(mate==null) {
			return "mate/login";
		}
		
		return "crew/createcrew";
	}
	
	// 모임 등록
	@PostMapping
	public String register(@ModelAttribute CrewCreate crewCreate) throws IOException {
		crewService.createCrew(crewCreate);
		log.info("crew= {}", crewCreate);
		return "redirect:/";
	}
	
	// 모임 등록 결과에 대한 메소드
	@GetMapping("/result")
	public String registerResult() {
		return "crew/crew-result";		
	}
	
	// 모임 신청
	@PostMapping("/{crewId}")
	public String joinCrew(@PathVariable String crewId, HttpSession httpSession, Model model, @RequestParam("submit_button") String submit) {
		// 로그인한 회원 객체 세션에서 받아오기
		Mate mate = (Mate)httpSession.getAttribute("mate");
		model.addAttribute("mateEmail", mate.getEmail());
	    log.info("모임신청mate : {}", mate);
	    
		CrewList crewList = new CrewList();
		crewList.setCrewId(crewId);
		crewList.setEmail(mate.getEmail());
		log.info("crewList: {}", crewList);

		// 신청or취소 버튼 분기
		if(submit.equals("y")) {
		    // 모임 신청
			crewService.joinCrew(crewList);
		}else {
			// 모임 탈퇴
			crewService.leaveCrew(mate.getEmail());			
		}
		
		return "redirect:/crew/{crewId}";
	}
	
	// 모임 신청 결과에 대한 메소드
	@GetMapping("/joincrew")
	public String joinCrewResult() {
		return "crew/join";
	}
	
	// 특정 모임의 모임 참석 화면 보여주기
	@GetMapping("/{crewId}")
	public String JoinCrew(@PathVariable String crewId, HttpSession httpSession, Model model) {
		// 로그인한 회원 객체 세션에서 받아오기
		Mate mate = (Mate)httpSession.getAttribute("mate");
		if(mate==null) {
			return "mate/login";
		}
		log.info("재훈모임mate= {}", mate);
		model.addAttribute("crewId", crewId);
		log.info("crewId= {}", crewId);
		// DB에 등록된 모임 정보를 뷰로 보내기
		CrewCreate crewCreate = crewService.getCrew(crewId);
		LocalDateTime localDateTime = LocalDateTime.parse(crewCreate.getCrewdate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"));
		model.addAttribute("crewCreate", crewCreate);
		model.addAttribute("crewdate", localDateTime);
		// 사진 보여주기
		List<CrewPhoto> crewPhotos = crewService.getPhotos(crewId);
		model.addAttribute("photos", crewPhotos);
		// 모임에 참석한 멤버 리스트
		List<CrewMates> crewMates = crewService.getCrews(crewId);
		model.addAttribute("crewMates", crewMates);
		// 모임에 참석한 회원,안 한 회원 분류하여 신청버튼 보이기,숨기기
		CrewMates sessionMate = crewService.sessionMate(mate.getEmail()); 
		log.info("sessionMate= {}", sessionMate);
		if(sessionMate != null) {
			model.addAttribute("sessionMateCrewId", sessionMate.getCrewId()); 
		}
		
		log.info("crewCreate= {}", crewCreate);
		log.info("photo= {}", crewPhotos);
		log.info("crewMates= {}", crewMates);
		
		return "crew/crewJoin";
//		return "redirect:/crew/{crewId}";
		
	}

	// 모임 리스트 정보 보여주기
	@GetMapping("/runlist/{but}")
	public String ajaxCrewList(@PathVariable String but, HttpSession httpSession, Model model) {
		crewListDays(model);
		
		// 날짜별 모임 리스트 ajax
		String butDate = but.substring(1, but.length());
		log.info("받은 날짜 키워드: {}", butDate);
		List<CrewCreate> dateCrews = crewService.findBydate(butDate);
		for (CrewCreate crewCreate2 : dateCrews) {
			String originDate = crewCreate2.getCrewdate();  
			String end = originDate.substring(11,16);
			crewCreate2.setCrewdate(end);
		}
		model.addAttribute("dateCrews", dateCrews);	
		log.info("날짜별crews= {}", dateCrews);
		
		return "runList";
	}
	
	// 지역별 모임 리스트 ajax
	@GetMapping("/runlist2/{but}")
	public String locationCrewList(@PathVariable String but, HttpSession httpSession, Model model) {
		crewListDays(model);
		
		log.info("받은 지역 키워드: {}", but);
		
		List<CrewCreate> locaCrews = crewService.searchByLocation(but);
		for (CrewCreate crewCreate2 : locaCrews) {
			String originDate = crewCreate2.getCrewdate();  
			String end = originDate.substring(11,16);
			crewCreate2.setCrewdate(end);
		}
		model.addAttribute("locaCrews", locaCrews);
		log.info("지역검색crews= {}", locaCrews);
		
		return "runList2";
	}
	
	// 레벨별 매칭 페이지 화면 요청 처리 메소드
	@GetMapping("/level")
	public String LevelView(HttpSession httpSession, Model model) {
		return "crew/levelMatchingPage";
	}
	
	// 레벨 모임 리스트 ajax
	@GetMapping("/runlist3/{but}")
	public String levelCrewList(@PathVariable String but, HttpSession httpSession, Model model) {
		crewListDays(model);
		but = "b04_13";

		// 날짜별 모임 리스트 ajax
		String butDate = but.substring(1, but.length());
		log.info("butDate: {}", butDate);
		List<CrewCreate> dateCrews = crewService.findBydate(butDate);
		for (CrewCreate crewCreate2 : dateCrews) {
			String originDate = crewCreate2.getCrewdate();  
			String end = originDate.substring(11,16);
			crewCreate2.setCrewdate(end);
		}
		model.addAttribute("dateCrews", dateCrews);	
		log.info("날짜별crews= {}", dateCrews);
		
		return "runList";
	}


}
