package com.runningmate.domain.crew.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CrewPhoto {
	private String photoId;
	private String photoName;
	private String crewId;

}
