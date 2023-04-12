package com.runningmate.domain.board.service;

import java.util.List;

import com.runningmate.domain.board.dto.ReplyDto;

public interface IReplyService {
	
    public List<ReplyDto> replyList(String replyNoticeIndex);
    
    public int replyWrite(String email, String replyContent, int replyNoticeIndex);
    
    public int replyDeleteDto(int replyId);
    
    public int getReplyCount(String replyNoticeIndex);
}