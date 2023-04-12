package com.runningmate.domain.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.runningmate.domain.board.dto.NoticeDto;
import com.runningmate.domain.board.mapper.INoticeMapper;


@Service
@Transactional
public class NoticeServiceImpl implements INoticeService {
    
    @Autowired
    INoticeMapper noticemapper;
    
    @Override
    public List<NoticeDto> list() {
        return noticemapper.list();
    }

    @Override
    public int write(String noticeTitle, String noticeContent) {
        return noticemapper.write(noticeTitle, noticeContent);
    }

    @Override
    public NoticeDto viewDto(String noticeId) {
        return noticemapper.viewDto(noticeId);
    }

    @Override
    public int updateDto(String noticeId, String noticeTitle, String noticeContent) {
        return noticemapper.updateDto(noticeId, noticeTitle, noticeContent);
    }

    @Override
    public int deleteDto(String noticeId) {
        return noticemapper.deleteDto(noticeId);
    }

    @Override
    public int hit(String noticeId) {
        return noticemapper.hit(noticeId);
    }

    @Override
    public String getPrevNoticeId(String noticeId) {
        return noticemapper.getPrevNoticeId(noticeId);
    }

    @Override
    public String getNextNoticeId(String noticeId) {
        return noticemapper.getNextNoticeId(noticeId);
    }

    @Override
    public int getTotalCount() {
        return noticemapper.getTotalCount();
    }


	@Override
	public int countNotices() {
		return 0;
	}


	
	
	 @Override
	    public List<NoticeDto> getNotices(int currentPage, int pageSize) {
	        int offset = (currentPage - 1) * pageSize;
	        return noticemapper.findAllWithPaging(offset, pageSize);
	    }

	    @Override
	    public int getTotalPages(int pageSize) {
	        int totalCount = getTotalPosts();
	        return (int) Math.ceil((double) totalCount / pageSize);
	    }
	   
	    @Override
		public int getTotalPosts() {
		    return noticemapper.getTotalCount(); // 전체 게시물 수 리턴
		}
	
	//서치창
	    

	    @Override
	    public List<NoticeDto> searchNotices(String searchType, String searchKeyword, int currentPage, int pageSize) {
	        int offset = (currentPage - 1) * pageSize;
	        return noticemapper.searchWithPaging(searchType, searchKeyword, offset, pageSize);
	    }

	    @Override
	    public int getSearchTotalPosts(String searchType, String searchKeyword) {
	        return noticemapper.getSearchCount(searchType, searchKeyword);
	    }
	

 
    
}
