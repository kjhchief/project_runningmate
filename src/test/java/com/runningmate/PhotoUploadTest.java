package com.runningmate;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import com.runningmate.domain.crew.dto.CrewPhotoUp;
import com.runningmate.domain.crew.mapper.CrewMapper;
import com.runningmate.domain.crew.service.CrewService;
import com.runningmate.web.crew.controller.PhotoStore;
import com.runningmate.web.crew.controller.UploadPhoto;


import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class PhotoUploadTest {
	
	@Autowired
	private CrewMapper crewMapper;
	
	@Value("${file.dir}")
	private String location;

	@Autowired
	private CrewService crewService;
	
	@Autowired
	private PhotoStore fileStore;
	
	@Test
	void photoUpTest() throws IOException {
		CrewPhotoUp crewPhotoUp = new CrewPhotoUp();
//		crewPhotoUp.setUploadfile(MultipartFile multipartFile);
		
		log.info("업로드 파일: {}", crewPhotoUp.getUploadfile());
		log.info("업로드 파일: {}", crewPhotoUp.getUploadfiles());

		UploadPhoto uploadFile = fileStore.storeFile(crewPhotoUp.getUploadfile());
		List<UploadPhoto> uploadFiles = fileStore.storeFiles(crewPhotoUp.getUploadfiles());
		
		log.info("원본파일명 : {}", uploadFile.getUploadFileName());
		log.info("저장된파일명 : {}", uploadFile.getStoreFileName());
		
	}
}
