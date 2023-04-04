package com.runningmate.domain.crew.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.runningmate.domain.crew.dto.Crew;
import com.runningmate.web.crew.controller.CrewCreate;
import com.runningmate.web.crew.controller.CrewMates;

@Mapper
public interface CrewMapper {
	public CrewCreate findById(String id);
	public void create(CrewCreate crewCreate);
	public void update(CrewCreate crewCreate);
	
	public List<CrewMates> findByAll(String id);
	
	
}
