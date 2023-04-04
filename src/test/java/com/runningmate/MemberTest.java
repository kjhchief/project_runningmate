package com.runningmate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.runningmate.domain.crew.dto.Crew;
import com.runningmate.domain.crew.mapper.CrewMapper;
import com.runningmate.domain.member.dto.Members;
import com.runningmate.domain.member.mapper.MembersMapper;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class MemberTest {
	
	@Autowired
	private MembersMapper membersMapper;
	
	@Test
	void createTest() {
//		log.info("mapeer : {}", membersMapper);
		Members members = new Members();
		members.setEmail("tester@gmail.com");
		members.setName("김기정");
		members.setPassword("1111");
		members.setBirthdate("19990411");
		members.setGender("M");
		members.setPhoneNumber("010.1111.2222");
		membersMapper.create(members);
		log.info("등록 완료");

		
		
		

		
	}
}
