package com.runningmate.domain.crew.service;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.runningmate.domain.crew.dto.CrewCreate;
import com.runningmate.domain.crew.dto.CrewMates;
import com.runningmate.domain.crew.dto.CrewPhoto;
import com.runningmate.domain.crew.dto.DayOfweeks;
import com.runningmate.domain.manage.dto.CrewList;


public interface CrewService {
	// 모임 만들기
	public void createCrew(CrewCreate crewCreate);
	// id로 회원정보 불러오기(화면에 모임정보 보여주는 용도)
	public CrewCreate getCrew(String id);
	// 모임 가입
	public void joinCrew(CrewList crewList);
	// 모임에 가입된 회원 리스트 불러오기
	public List<CrewMates> getCrews(String id);
	// 모임에 업로드한 사진 리스트 불러오기
	public List<CrewPhoto> getPhotos(String id);
	// 모임에 가입한 회원 안 한 회원 구분 용도
	public CrewMates sessionMate(String email);
	// 모임 탈퇴
	public void leaveCrew(String email);
	// 등록된 모든 모임 불러오기
	public List<CrewCreate> allCrew();
	// 요일 계산
	public DayOfweeks calculDay(int num);
	// 날짜로 모임 리스트 검색
	public List<CrewCreate> findBydate(String date);
	// 지역으로 모임 검색
	public List<CrewCreate> searchByLocation(String loca);
	// 날짜와 수준으로 모임 리스트 불러오기
	public List<CrewCreate> findBydateCrews(int i);
	// 특정 기간 날짜만
	public List<CrewCreate> dates(int i);
}
