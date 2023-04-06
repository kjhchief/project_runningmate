package com.runningmate.domain.crew.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.runningmate.domain.crew.dto.CrewCreate;
import com.runningmate.domain.crew.mapper.CrewMapper;
import com.runningmate.web.crew.controller.CrewMates;

@Service
public class CrewServiceImpl implements CrewService { 
	
	@Autowired
	private CrewMapper crewMapper; 

	@Override
	@Transactional
	public void createCrew(CrewCreate crewCreate) {
		// 모임등록
		crewMapper.createCrew(crewCreate);
		// 사진등록
		crewMapper.createPhoto(crewCreate);
	}
	/*
	@Override
	public void createCrewPhoto(CrewCreatePhoto crewCreatePhoto) {
		crewMapper.createPhoto(crewCreatePhoto);
		
	}
	*/

	@Override
	public CrewCreate getCrew(String id) {
		return crewMapper.findById(id);
	}

	@Override
	public List<CrewMates> getCrews(String id) {
		return crewMapper.findByAll(id);
	}



	


}
