package com.runningmate.domain.manage.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class MemberMannage {
	private String review_id;
	private String author;
	private String member_id;
	private String content;
	private Date writeDate;
}
