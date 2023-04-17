package com.runningmate.domain.mate.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.runningmate.domain.mate.dto.Mate;


@Mapper
public interface MateMapper { //다시push할게요dydydyd
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
	
	//업데이트결과 가져오기
	public Mate get(String email);
	
	//회원정보 삭제
	public int mateInfoDelete(String email);
	//아이디 찾기
	public String findEmail(@Param("name") String name, @Param("password")String  password);
	//비밀번호 찾기
	public String findPassword(@Param("name")String  name, @Param("email") String email);
}
