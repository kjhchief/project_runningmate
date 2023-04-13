package com.runningmate.domain.mate.service;

import org.apache.ibatis.annotations.Param;

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
	//비밀번호 유효성
	public boolean existPassword(String email, String password);
	//로그인 회원 정보
	public Mate getLoginInfo(String email, String password);
	//회원 정보 수정
	public Mate update(Mate mate);
	//회원 정보 삭제
	public boolean isDelete(String email);
	//아이디 찾기
	public String findEmail(String name, String  password);
	//비밀번호 찾기
	public String findPassword(String name, String  email);
	
}
