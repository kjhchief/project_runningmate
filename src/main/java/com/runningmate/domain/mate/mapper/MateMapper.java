package com.runningmate.domain.mate.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.runningmate.domain.mate.dto.Mate;


@Mapper
public interface MateMapper {
	//회원 가입
	public void create(Mate mate);
	
	// 이메일 유효성 체크
	public String findByEmail(String email);
	
	// 비밀번호 확인
	public String findByPassword(@Param("email") String email, @Param("password")String  password);
	
	//로그인
	public Mate findByEmailAndPassword(@Param("email") String email, @Param("password")String  password);	
	
	//회원 정보 수정
	public void update(Mate mate);
}
