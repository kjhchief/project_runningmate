package com.runningmate.domain.crew.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Crew {
	private String crewId;
	private String title;
	private String crewdate;
	private int mateCount;
	private String crewLocation;
	private String crewLevel;
	private int courseLeng;
	private String courseIntro;
	private String weatherIntro;
	private String etcIntro;
	private String description;
	private int awaiterCount;

}
