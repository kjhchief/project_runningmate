package com.runningmate.domain.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runningmate.domain.member.dto.Members;
import com.runningmate.domain.member.mapper.MembersMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MembersMapper memberMapper;
	
	@Override
	public void create(Members member) {
		memberMapper.create(member);
	}

	@Override
	public boolean existEmail(String email) {
		if(memberMapper.findByEmail(email) != null) {
			return true;
		}
		return false;
	}
	
	
}
