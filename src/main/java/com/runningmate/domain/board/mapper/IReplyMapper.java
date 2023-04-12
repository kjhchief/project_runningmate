package com.runningmate.domain.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.runningmate.domain.board.dto.ReplyDto;



@Mapper
public interface IReplyMapper {
    public List<ReplyDto> replyList(String replyNoticeIndex);
    
    public int replyWrite(String replyContent, int replyNoticeIndex);
    
    public int replyDeleteDto(int replyId);
    
    public int getReplyCount(String replyNoticeIndex);
    
    	
}
