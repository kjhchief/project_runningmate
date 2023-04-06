package com.runningmate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.runningmate.domain.crew.dto.Crew;
import com.runningmate.domain.crew.mapper.CrewMapper;
import com.runningmate.domain.mate.dto.Mate;
import com.runningmate.domain.mate.mapper.MateMapper;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class MateTest {
	
	@Autowired
	private MateMapper mateMapper;
	
	@Test
	void createTest() {
//		log.info("mapper : {}", mateMapper);
		Mate mate = new Mate();
		mate.setEmail("tester@gmail.com");
		mate.setName("김기정");
		mate.setPassword("1111");
		mate.setBirthdate("19990411");
		mate.setGender("M");
		mate.setPhoneNumber("010.1111.2222");
//		mateMapper.create(mate);
		log.info("등록 완료");

		

		
	}

	
}
