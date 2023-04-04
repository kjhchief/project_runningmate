package com.runningmate.domain.crew.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runningmate.domain.crew.dto.Crew;
import com.runningmate.domain.crew.mapper.CrewMapper;
import com.runningmate.web.crew.controller.CrewCreate;
import com.runningmate.web.crew.controller.CrewMates;

@Service
public class CrewServiceImpl implements CrewService { 
	
	@Autowired
	private CrewMapper crewMapper; 

	@Override
	public void createCrew(CrewCreate crewCreate) {
		crewMapper.create(crewCreate);
	}

	@Override
	public CrewCreate getCrew(String id) {
		return crewMapper.findById(id);
	}

	@Override
	public List<CrewMates> getCrews(String id) {
		return crewMapper.findByAll(id);
	}


}
