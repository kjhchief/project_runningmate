package com.runningmate.domain.member.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.runningmate.domain.member.dto.Members;


@Mapper
public interface MembersMapper {
	//회원 가입
	public void create(Members member);
	
	// 회원 상세 조회
	public String findByEmail(String email);
	
	
}
