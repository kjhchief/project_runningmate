package com.runningmate;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.runningmate.domain.crew.dto.CrewCreate;
import com.runningmate.domain.crew.service.CrewService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class CrewServiceTest {
	
	@Autowired
	private CrewService crewService;
	
	@Test
	@Disabled
	void createCrewTest () {
		CrewCreate crew = new CrewCreate();
		crew.setTitle("테스트 모임333333333333333");
		crew.setCrewdate("2020-3-4"+"T"+" 12:50");
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
//		crew.setPhotoName("tttt.jp");
		
		crewService.createCrew(crew);
		
		log.info("모임 및 사진 등록 완료 : {}", crew);
	}
	
}
