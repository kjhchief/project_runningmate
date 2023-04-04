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
	
	public boolean existEmail(String email);
	
}
