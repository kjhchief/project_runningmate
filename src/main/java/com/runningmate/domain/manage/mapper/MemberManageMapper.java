package com.runningmate.domain.manage.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberManageMapper {
	public void termUp();
	public void termDown();
	public void newReview();
}
