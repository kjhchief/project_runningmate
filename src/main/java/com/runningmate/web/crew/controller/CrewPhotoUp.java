package com.runningmate.web.crew.controller;

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
	private String title;
	//  필요한 파라메터 추가
	private List<MultipartFile> uploadfiles;
}










