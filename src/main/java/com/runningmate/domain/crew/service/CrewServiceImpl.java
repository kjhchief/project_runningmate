package com.runningmate.domain.crew.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.runningmate.domain.crew.dto.CrewCreate;
import com.runningmate.domain.crew.dto.CrewMates;
import com.runningmate.domain.crew.dto.CrewPhoto;
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




	


}
