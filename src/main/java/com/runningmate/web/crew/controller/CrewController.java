package com.runningmate.web.crew.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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
import com.runningmate.domain.crew.dto.CrewCreatePhoto;
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
	public String register(@ModelAttribute CrewCreate crewCreate) throws IOException {
		
		List<UploadFile> uploadFiles = fileStore.storeFiles(crewCreate.getUploadfiles());
		
		for(UploadFile photoName : uploadFiles){
			crewCreate.setPhotoName(photoName.getStoreFileName());
			log.info("photoNames= {}", photoName.getStoreFileName());
		}
		
		crewService.createCrew(crewCreate);// 여기서 이미 디비 갔다 와버림.
		
		log.info("crew= {}", crewCreate);
//		photoUp(crewCreatePhoto);
		return "redirect:/crew/result";
	}
	
	// 사진 등록 헬퍼 메소드
	/*
	private CrewCreatePhoto photoUp(CrewCreatePhoto crewCreatePhoto) throws IOException {
		log.info("crewCreatePhoto= {}", crewCreatePhoto);
		List<UploadFile> uploadFiles = fileStore.storeFiles(crewCreatePhoto.getUploadfiles());
		
		for(UploadFile photoName : uploadFiles){
			crewCreatePhoto.setPhotoName(photoName.getStoreFileName());
			log.info("photoNames= {}", photoName.getStoreFileName());
		}
		
		crewService.createCrewPhoto(crewCreatePhoto);
		
		return crewCreatePhoto;
	}
	*/
	
	
	
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
	
	
	
	// 모임 만들기 결과에 대한 메소드
	@GetMapping("/result")
	public String registerResult() {
		return "crew/crew-result";		
	}
	
	// 특정 모임의 모임 참석 화면 보여주기
	@GetMapping("/{crewId}")
	public String JoinCrew(@PathVariable String crewId, Model model) {
		CrewCreate crewCreate = crewService.getCrew(crewId);
		model.addAttribute("crewCreate", crewCreate);
		log.info("crewCreate= {}", crewCreate);
		
		List<CrewMates> crewMates = crewService.getCrews(crewId);
		model.addAttribute("crewMates", crewMates);
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
