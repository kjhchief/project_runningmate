package com.runningmate.domain.board.dto;

import java.util.Date;

import lombok.Data;

 
@Data

public class ReplyDto {
	private int replyId;
	private String replyContent;
	private String replyName;
    private Date replyDate;
    private int replyNoticeId;
    private String email; // 이메일 필드 추가

    
    public ReplyDto() {}
    
	public ReplyDto(int replyId, String replyContent, Date replyDate, int replyNoticeId) {
		this.replyId = replyId;
		this.replyContent = replyContent;
		this.replyDate = replyDate;
		this.replyNoticeId = replyNoticeId;
	}
	
	public ReplyDto(String replyName) {
		this.replyName = replyName;
	}

	public int getReplyId() {
		return replyId;
	}

	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}
	

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public Date getReplyDate() {
		return replyDate;
	}

	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
	}

	public int getReplyNoticeId() {
		return replyNoticeId;
	}

	public void setReplyNoticeId(int replyNoticeId) {
		this.replyNoticeId = replyNoticeId;
	}

	public String getReplyName() {
		return replyName;
	}

	public void setReplyName(String replyName) {
		this.replyName = replyName;
	}
	
	
	
}