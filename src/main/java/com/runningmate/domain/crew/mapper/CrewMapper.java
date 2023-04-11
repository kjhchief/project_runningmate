package com.runningmate.domain.crew.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.runningmate.domain.crew.dto.CrewCreate;
import com.runningmate.domain.crew.dto.CrewMates;
import com.runningmate.domain.crew.dto.CrewPhoto;
import com.runningmate.domain.manage.dto.CrewList;

@Mapper
public interface CrewMapper {
	
	// 크루 ID로 CrewDreate DTO 정보 불러오기. 참석 화면 보여주는 용도
	public CrewCreate findById(String id);
	// 모임 등록
	public void createCrew(CrewCreate crewCreate);
	// 사진 등록
	public void createPhoto(CrewCreate crewCreate);
	// 모임 가입
	public void joinCrew(CrewList crewList);
	// 이메일로 회원의 이메일,이름,크루아이디,참여리스트타입 조회 (세션 회원이 모임에 참여중인 회원인지 여부 알기 위함)
	public CrewMates sessionMate(String email);
	// 모임 신청 취소
	public void leaveCrew(String email);
	// 등록된 모든 모임 불러오기
	public List<CrewCreate> allCrew();
	
	public void update(CrewCreate crewCreate);
	
	public List<CrewMates> findByAll(String id);
	
	public List<CrewPhoto> getPhotos(String id);
	
}
