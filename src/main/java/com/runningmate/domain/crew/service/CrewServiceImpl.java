package com.runningmate.domain.crew.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runningmate.domain.crew.dto.Crew;
import com.runningmate.domain.crew.mapper.CrewMapper;
import com.runningmate.web.crew.controller.CrewCreate;

@Service
public class CrewServiceImpl implements CrewService { 
	
	@Autowired
	private CrewMapper crewMapper; 

	@Override
	public void createCrew(CrewCreate crewCreate) {
		crewMapper.create(crewCreate);
	}

}
