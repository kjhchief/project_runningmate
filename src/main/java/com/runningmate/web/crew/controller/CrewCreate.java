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
public class CrewCreate {
	private String title;
	private String crewdate;
	private int memberCount;
	private String crewLocation;
	private String crewLevel;
	private int courseLeng;
	private String courseIntro;
	private String weatherIntro;
	private String etcIntro;
	private String description;
	private int awaiterCount;
	private List<MultipartFile> uploadfiles;
}










