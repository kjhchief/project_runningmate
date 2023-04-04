package com.runningmate.domain.manage.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class Review {
	private String reviewId;
	private String author;
	private Date writeDate;
	private String email;
	private String content;
}
