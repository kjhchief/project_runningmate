package com.runningmate;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.runningmate.domain.crew.dto.Crew;
import com.runningmate.domain.crew.mapper.CrewMapper;
import com.runningmate.web.crew.controller.CrewCreate;
import com.runningmate.web.crew.controller.CrewMates;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class CrewMapperTest {
	
	@Autowired
	private CrewMapper crewMapper;
	
	@Test
	@Disabled
	void createTest() {
		Crew crew = new Crew();
		crew.setCrewId("1");
		crew.setTitle("안녕 모임 호호호");
		crew.setCrewdate("23/3/2");
		crew.setMateCount(5);
		crew.setCrewLocation("어디구 어디동 어디 공원");
		crew.setCrewLevel("다소 강한 러닝");
		crew.setCourseLeng(20);
		crew.setCourseIntro("이것은 코스 인트로 입니다. 첫 줄에 나오는 인트로.");
		crew.setWeatherIntro("이것은 날씨 인트로. 오늘 날씨가 어쩌고 저쩌고");
		crew.setEtcIntro("기타 머시기가 저시기 인트로");
		crew.setDescription("메인 설명. 아주 긴 설명. 사실 그렇게 길지는 않지만 그렇다고 짧지는 않은 꽤 긴 설명");
		crew.setAwaiterCount(3);
//		crewMapper.create(crew);
		log.info("모임 등록 완료 : {}", crew);
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
	public void findAllTest() {
		List<CrewMates> list = crewMapper.findByAll("64");
		log.info("모임 정보: {}", list);
	};
	
	
	
}
