package com.runningmate.domain.crew.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.runningmate.domain.crew.dto.CrewCreate;
import com.runningmate.domain.crew.dto.CrewCreatePhoto;
import com.runningmate.web.crew.controller.CrewMates;

@Mapper
public interface CrewMapper {
	
	public CrewCreate findById(String id);
	// 모임 등록
	public void createCrew(CrewCreate crewCreate);
	// 사진 등록
	public void createPhoto(CrewCreate crewCreate);
	
	public void update(CrewCreate crewCreate);
	
	public List<CrewMates> findByAll(String id);
	
	
}
