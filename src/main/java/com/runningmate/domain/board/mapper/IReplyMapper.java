package com.runningmate.domain.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.runningmate.domain.board.dto.ReplyDto;



@Mapper
public interface IReplyMapper {
	
	
    public List<ReplyDto> replyList(String replyNoticeIndex);
    
    public int replyWrite(@Param("email") String email, @Param("replyContent") String replyContent, @Param("replyNoticeIndex") int replyNoticeIndex);
    
    
    public int replyDeleteDto(int replyId);
    
    public int getReplyCount(String replyNoticeIndex);
    
    	
}
