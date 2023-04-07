package com.runningmate.domain.crew.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.runningmate.domain.crew.dto.CrewCreate;
import com.runningmate.domain.crew.dto.CrewMates;
import com.runningmate.domain.crew.dto.CrewPhoto;

@Mapper
public interface CrewMapper {
	
	// 크루 ID로 CrewDreate DTO 정보 불러오기. 참석 화면 보여주는 용도
	public CrewCreate findById(String id);
	// 모임 등록
	public void createCrew(CrewCreate crewCreate);
	// 사진 등록
	public void createPhoto(CrewCreate crewCreate);
	
	public void update(CrewCreate crewCreate);
	
	public List<CrewMates> findByAll(String id);
	
	public List<CrewPhoto> getPhotos(String id);
	
	
}
