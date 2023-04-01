package com.runningmate.domain.member.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.runningmate.domain.member.dto.Members;

@Mapper
public interface MembersMapper {
	
	public void create(Members member);
	
	
}
