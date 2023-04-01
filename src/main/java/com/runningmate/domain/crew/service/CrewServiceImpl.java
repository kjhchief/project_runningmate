package com.runningmate.domain.crew.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runningmate.domain.crew.dto.Crew;
import com.runningmate.domain.crew.mapper.CrewMapper;

@Service
public class CrewServiceImpl implements CrewService {
	
	@Autowired
	private CrewMapper crewMapper;

	@Override
	public void createCrew(Crew crew) {
		crewMapper.create(crew);
	}

}
