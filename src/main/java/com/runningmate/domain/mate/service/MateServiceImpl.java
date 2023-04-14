package com.runningmate.domain.mate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.runningmate.domain.mate.dto.Mate;
import com.runningmate.domain.mate.mapper.MateMapper;


@Service
@Transactional
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
	
	@Override
	public boolean existPassword(String email, String password) {
		if(mateMapper.findByPassword(email, password) != null) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Mate getLoginInfo(String email, String password) {
		return mateMapper.findByEmailAndPassword(email, password);
	}

	@Override
	public Mate update(Mate mate) {
		mateMapper.update(mate);
		return mateMapper.get(mate.getEmail());
	}

	@Override
	public boolean isDelete(String email) {
		if(mateMapper.mateInfoDelete(email) == 1) {
			return true;
		}else {
			return false;
		}
		
	}

	@Override
	public String findEmail(String name, String password) {
		return mateMapper.findEmail(name, password);
		
	}

	@Override
	public String findPassword(String name, String email) {
		return  mateMapper.findPassword(name, email);
	}

  
 
	
	
}
