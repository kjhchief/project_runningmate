package com.runningmate.domain.crew.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class CrewPhotoUp {
//	private String uploader;
//	private String description;
	private MultipartFile uploadfile;
	private List<MultipartFile> uploadfiles;
}










