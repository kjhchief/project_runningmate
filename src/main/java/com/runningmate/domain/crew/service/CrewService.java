package com.runningmate.domain.crew.service;

import java.util.List;

import com.runningmate.domain.crew.dto.CrewCreate;
import com.runningmate.domain.crew.dto.CrewMates;
import com.runningmate.domain.crew.dto.CrewPhoto;
import com.runningmate.domain.manage.dto.CrewList;


public interface CrewService {
	public void createCrew(CrewCreate crewCreate);
	public CrewCreate getCrew(String id);
	public void joinCrew(CrewList crewList);
	public List<CrewMates> getCrews(String id);
	public List<CrewPhoto> getPhotos(String id);
	public CrewList sessionMate(String email);
}
