package com.runningmate.domain.crew.service;

import java.util.List;

import com.runningmate.domain.crew.dto.CrewCreate;
import com.runningmate.domain.crew.dto.CrewMates;
import com.runningmate.domain.crew.dto.CrewPhoto;


public interface CrewService {
	public void createCrew(CrewCreate crewCreate);
	public CrewCreate getCrew(String id);
	public List<CrewMates> getCrews(String id);
	public List<CrewPhoto> getPhotos(String id);
}
