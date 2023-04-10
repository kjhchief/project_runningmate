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
	
	public Review() {
		//no
	}
	
	public Review (String author, String email, String content) {
		this.author = author;
		this.email = email;
		this.content = content;

	}

}