package com.runningmate.domain.crew.service;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.runningmate.domain.crew.dto.CrewCreate;
import com.runningmate.domain.crew.dto.CrewMates;
import com.runningmate.domain.crew.dto.CrewPhoto;
import com.runningmate.domain.crew.dto.DayOfweeks;
import com.runningmate.domain.crew.mapper.CrewMapper;
import com.runningmate.domain.manage.dto.CrewList;
import com.runningmate.web.crew.controller.FileStore;
import com.runningmate.web.crew.controller.UploadFile;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CrewServiceImpl implements CrewService { 
	
	@Autowired
	private CrewMapper crewMapper; 
	@Autowired
	private FileStore fileStore;

	@Override
	@Transactional
	public void createCrew(CrewCreate crewCreate) {
		// 모임등록
		crewMapper.createCrew(crewCreate);
		// 사진등록
		
		List<UploadFile> uploadFiles = null;
		try {
			uploadFiles = fileStore.storeFiles(crewCreate.getUploadfiles());
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(UploadFile photoName : uploadFiles){
			crewCreate.setPhotoName(photoName.getStoreFileName());
			log.info("photoNames= {}", photoName.getStoreFileName());
			crewMapper.createPhoto(crewCreate);
		}
		
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

	@Override
	public List<CrewPhoto> getPhotos(String id) {
		
		return crewMapper.getPhotos(id);
	}

	@Override
	public void joinCrew(CrewList crewList) {
		crewMapper.joinCrew(crewList);
	}

	@Override
	public CrewMates sessionMate(String email) {
		return crewMapper.sessionMate(email);
	}

	@Override
	public void leaveCrew(String email) {
		crewMapper.leaveCrew(email);
	}

	@Override
	public List<CrewCreate> allCrew() {
		return crewMapper.allCrew();
	}

	@Override
	public DayOfweeks calculDay(int num) {
		// 날짜별 모임 리스트 보여주기 기능
		LocalDateTime now = LocalDateTime.now();
		DayOfWeek dayOfWeek = now.getDayOfWeek();
		int dayOfMonth = now.getDayOfMonth() + num;
		
		LocalDateTime nowPlusDays = now.plusDays(num);
		
		String monthDay = nowPlusDays.format(DateTimeFormatter.ofPattern("MM_dd"));
		
		String day = nowPlusDays.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.KOREAN);
			
		DayOfweeks dayOfweeks = new DayOfweeks();
		dayOfweeks.setDayofMonth(dayOfMonth);
		dayOfweeks.setDayofweek(day);
		dayOfweeks.setMonthDay(monthDay);
		dayOfweeks.setLocalDateTime(nowPlusDays);
		
		return dayOfweeks;
	}

	@Override
	public List<CrewCreate> findBydate(String date) {
		return crewMapper.findBydate(date);
	}

	@Override
	public List<CrewCreate> searchByLocation(String loca) {
		return crewMapper.searchByLocation(loca);
	}

	@Override
	public List<CrewCreate> findBydateCrews(int i) {
		return crewMapper.findBydateCrews(i);
	}

	@Override
	public List<CrewCreate> dates(int i) {
		return crewMapper.dates(i);
	}




	


}
