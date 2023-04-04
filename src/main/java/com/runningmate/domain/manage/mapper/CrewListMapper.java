package com.runningmate.domain.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.runningmate.domain.crew.dto.Crew;
import com.runningmate.domain.manage.dto.Review;
import com.runningmate.domain.mate.dto.Mate;

@Mapper
public interface CrewListMapper {
	public List<Crew> findByEmail(String email);
	public Crew findBytypes();
}
