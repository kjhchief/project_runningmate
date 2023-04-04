package com.runningmate.domain.mate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runningmate.domain.mate.dto.Mate;
import com.runningmate.domain.mate.mapper.MateMapper;

@Service
public class MateServiceImpl implements MateService {

	@Autowired
	MateMapper mateMapper;
	
	@Override
	public void create(Mate mate) {
		mateMapper.create(mate);
	}

	@Override
	public boolean existEmail(String email) {
		
		if(mateMapper.findByEmail(email) != null) {
			return true;
		}else {
			return false;
		}
	}
	
	
}
