package com.runningmate.domain.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runningmate.domain.board.dto.ReplyDto;
import com.runningmate.domain.board.mapper.IReplyMapper;




@Service
public class ReplyServiceImpl implements IReplyService {
	
	
	
	@Autowired
	IReplyMapper ireplydao;
	

	@Override
	public List<ReplyDto> replyList(String replyNoticeIndex) {
	    return ireplydao.replyList(replyNoticeIndex);
	}

	@Override
	public int replyWrite(String email, String replyContent, int replyNoticeIndex) {
	    int result = ireplydao.replyWrite(email,replyContent, replyNoticeIndex);
	    return result > 0 ? 1 : 0;
	}

	@Override
	public int replyDeleteDto(int replyId) {
	    int result = ireplydao.replyDeleteDto(replyId);
	    if (result == 1) {
	        System.out.println("댓글 삭제 성공");
	    } else {
	        System.out.println("댓글 삭제 실패");
	    }
	    return result;
	}

	@Override
	public int getReplyCount(String replyNoticeIndex) {
		return ireplydao.getReplyCount(replyNoticeIndex);
	}
	
	

}
