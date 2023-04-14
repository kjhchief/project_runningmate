package com.runningmate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.runningmate.domain.mate.service.MateService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class MateServiceTest {
	
	@Autowired
	private MateService mateService;
	
	@Test
	void findEmailTest () {
		String email = mateService.findEmail("송진호", "1111");
		log.info("찾은 이멜 : {}", email);
	}
	
}
