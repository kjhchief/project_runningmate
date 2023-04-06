package com.runningmate.domain.mate.service;

import com.runningmate.domain.mate.dto.Mate;

/**
 * 회원관련 비즈니스 메소드 선언
 * @author thdx4
 *
 */
public interface MateService {
	//회원가입
	public void create(Mate mate);
	//이메일 유효성
	public boolean existEmail(String email);
	//로그인 회원 정보
	public Mate getLoginInfo(Mate mate);
	
}
