package com.runningmate.domain.member.service;

import com.runningmate.domain.member.dto.Members;

/**
 * 회원관련 비즈니스 메소드 선언
 * @author thdx4
 *
 */
public interface MemberService {
	//회원가입
	public void create(Members member);
	
	public boolean existEmail(String email);
	
}
