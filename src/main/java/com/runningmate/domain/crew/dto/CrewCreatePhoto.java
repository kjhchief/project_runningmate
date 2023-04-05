package com.runningmate.domain.crew.dto;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
// 사진 테이블용 dto
public class CrewCreatePhoto {
	private String crewId;
	private List<MultipartFile> uploadfiles;
	private String photoName;
	
	
//	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
}










