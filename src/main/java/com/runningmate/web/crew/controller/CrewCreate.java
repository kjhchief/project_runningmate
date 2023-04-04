package com.runningmate.web.crew.controller;

import java.util.Date;
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
public class CrewCreate {
	private String title;
	private Date crewdate;
	private int mateCount;
	private String crewLocation;
	private String crewLocationDt;
	private String crewLevel;
	private int courseLeng;
	private String courseIntro;
	private String weatherIntro;
	private String etcIntro;
	private String description;
	private int awaiterCount;
	// 사진 정보
	private List<MultipartFile> uploadfiles;
	private String name;
}










