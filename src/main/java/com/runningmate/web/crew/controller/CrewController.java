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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.runningmate.domain.crew.dto.Crew;
import com.runningmate.domain.crew.dto.CrewPhotoUp;
import com.runningmate.domain.crew.service.CrewService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/crew")
@Slf4j
public class CrewController {
	
	@Value("${file.dir}")
	private String location;

	@Autowired
	private CrewService crewService;
	
	@Autowired
	private PhotoStore fileStore;

	// 모임 만들기 요청 처리 메소드
	@GetMapping
	public String createcrew() {
		return "crew/createcrew";
	}
	
	@PostMapping
	public String register(@ModelAttribute("crew") Crew crew) {
		log.info("crew= {}", crew);
		crewService.createCrew(crew);
		return "redirect:/crew/result";
	}
	
	@PostMapping("/crew")
	public String uploadFiles(@ModelAttribute CrewPhotoUp crewPhotoUp, RedirectAttributes redirectAttributes)
			throws IOException {
		log.info("업로드 파일: {}", crewPhotoUp.getUploadfile());
		log.info("업로드 파일: {}", crewPhotoUp.getUploadfiles());

		UploadPhoto uploadFile = fileStore.storeFile(crewPhotoUp.getUploadfile());
		List<UploadPhoto> uploadFiles = fileStore.storeFiles(crewPhotoUp.getUploadfiles());
		
		log.info("원본파일명 : {}", uploadFile.getUploadFileName());
		log.info("저장된파일명 : {}", uploadFile.getStoreFileName());

		// 데이터베이스에 오리지날파일명과 저장파일명 저장
//		redirectAttributes.addAttribute("uploader", crewPhotoUp.getUploader());
		redirectAttributes.addAttribute("uploadfile", uploadFile.getUploadFileName());
		return "redirect:/crew/result";
	}

	//동적 이미지의 경우 출력
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


}
