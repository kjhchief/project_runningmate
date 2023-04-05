package com.runningmate;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.runningmate.domain.crew.dto.Crew;
import com.runningmate.domain.crew.dto.CrewCreate;
import com.runningmate.domain.crew.dto.CrewCreatePhoto;
import com.runningmate.domain.crew.mapper.CrewMapper;
import com.runningmate.web.crew.controller.CrewMates;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class CrewMapperTest {
	
	@Autowired
	private CrewMapper crewMapper;
	
	@Test
//	@Disabled
	CrewCreate createTest() {
		CrewCreate crew = new CrewCreate();
		crew.setTitle("크루ID null?");
		crew.setCrewdate("2020-3-4"+"T"+"12:50");
		crew.setMateCount(5);
		crew.setCrewLocation("어디구 어디동 어디 공원");
		crew.setCrewLocationDt("상세 지역 정보");
		crew.setCrewLevel("다소 강한 러닝");
		crew.setCourseLeng(20);
		crew.setCourseIntro("이것은 코스 인트로 입니다. 첫 줄에 나오는 인트로.");
		crew.setWeatherIntro("이것은 날씨 인트로. 오늘 날씨가 어쩌고 저쩌고");
		crew.setEtcIntro("기타 머시기가 저시기 인트로");
		crew.setDescription("메인 설명. 아주 긴 설명. 사실 그렇게 길지는 않지만 그렇다고 짧지는 않은 꽤 긴 설명");
		crew.setAwaiterCount(3);
		crewMapper.createCrew(crew);
		log.info("모임 등록 완료 : {}", crew.getCrewId());
		return crew;
	}
	
	@Test
//	@Disabled
	void createPhotoTest() {
		CrewCreatePhoto crewCreatePhoto = new CrewCreatePhoto(); 
		crewCreatePhoto.setCrewId(createTest().getCrewId());
		crewCreatePhoto.setPhotoName("xxxx.jpg");
		crewMapper.createPhoto(crewCreatePhoto);
		log.info("사진 등록 완료 : {}", crewCreatePhoto);
	}
	
	
	@Test
	@Disabled
	public CrewCreate getCrew() {
		CrewCreate crewCreate = new CrewCreate();
		crewCreate.getTitle();
		log.info("모임 정보: {}", crewCreate);
		return crewCreate;
		
	};
	
	@Test
	@Disabled
	public void findAllTest() {
		List<CrewMates> list = crewMapper.findByAll("64");
		log.info("모임 정보: {}", list);
	};
	
	
	
}
