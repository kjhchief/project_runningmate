package com.runningmate;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
	
	public String getFullPath(String filename) {
		return location + filename;
	}

	public List<UploadPhoto> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
		List<UploadPhoto> storeFileResult = new ArrayList<UploadPhoto>();
		for (MultipartFile multipartFile : multipartFiles) {
			if (!multipartFile.isEmpty()) {
				// 업로드 파일 저장
				UploadPhoto uploadFile = storeFile(multipartFile);
				storeFileResult.add(uploadFile);
			}
		}
		return storeFileResult;
	}

	public UploadPhoto storeFile(MultipartFile multipartFile) throws IOException {
		if (multipartFile == null || multipartFile.isEmpty()) {
			return null;
		}
		String originalFilename = multipartFile.getOriginalFilename();
		String storeFileName = createStoreFileName(originalFilename);
		multipartFile.transferTo(new File(getFullPath(storeFileName)));
		return new UploadPhoto(originalFilename, storeFileName);
	}

	private String createStoreFileName(String originalFilename) {
		String ext = extractExt(originalFilename);
		String uuid = UUID.randomUUID().toString();
		return uuid + "." + ext;
	}

	private String extractExt(String originalFilename) {
		int pos = originalFilename.lastIndexOf(".");
		return originalFilename.substring(pos + 1);
	}
	
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
