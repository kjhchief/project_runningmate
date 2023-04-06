package com.runningmate.domain.mate.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.runningmate.domain.mate.dto.Mate;


@Mapper
public interface MateMapper {
	//회원 가입
	public void create(Mate mate);
	
	// 이메일 유효성 체크
	public String findByEmail(String email);
	
	//로그인
	public Mate getLoginInfo(Mate mate);
}
