package com.runningmate.domain.mate.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.runningmate.domain.mate.dto.Mate;


@Mapper
public interface MateMapper {
	//회원 가입
	public void create(Mate mate);
	
	// 회원 상세 조회
	public String findByEmail(String email);
	
	
}
