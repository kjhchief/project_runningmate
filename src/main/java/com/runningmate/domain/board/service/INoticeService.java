package com.runningmate.domain.board.service;

import java.util.List;

import com.runningmate.domain.board.dto.NoticeDto;

public interface INoticeService {
    public List<NoticeDto> list();
    
    public int write(String noticeTitle, String noticeContent);
    
    public NoticeDto viewDto(String noticeId);
    
    public int updateDto(String noticeId, String noticeTitle, String noticeContent);
    
    public int deleteDto(String noticeId);
    
    public int hit(String noticeId);
    
    String getPrevNoticeId(String noticeId);
    
    String getNextNoticeId(String noticeId);
    
    public int getTotalCount();

	public int countNotices();
	
	 List<NoticeDto> getNotices(int currentPage, int pageSize);
	    int getTotalPages(int pageSize);
	    int getTotalPosts();
	    
	    
	    // 서치창!
	    List<NoticeDto> searchNotices(String searchType, String searchKeyword, int currentPage, int pageSize);
	    int getSearchTotalPosts(String searchType, String searchKeyword);
	
	

	
	    
	    
   
    
   
    
}
