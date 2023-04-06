package com.runningmate.domain.crew.service;

import java.util.List;

import com.runningmate.domain.crew.dto.CrewCreate;
import com.runningmate.web.crew.controller.CrewMates;


public interface CrewService {
	public void createCrew(CrewCreate crewCreate);
	public CrewCreate getCrew(String id);
	public List<CrewMates> getCrews(String id);
}
