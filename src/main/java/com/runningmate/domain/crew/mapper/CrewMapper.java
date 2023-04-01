package com.runningmate.domain.crew.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.runningmate.domain.crew.dto.Crew;

@Mapper
public interface CrewMapper {
	public void create(Crew crew);
	public void update(Crew crew);
	public List<Crew> findByAll();
	
	
}
