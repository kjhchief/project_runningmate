package com.runningmate.domain.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.runningmate.domain.board.dto.NoticeDto;


@Mapper
public interface INoticeMapper {
	
    public List<NoticeDto> list();
    
    
    public int write(String noticeTitle, String noticeContent);
    
    public NoticeDto viewDto(String noticeId);
    
    public int updateDto(String noticeId, String noticeTitle, String noticeContent);
    
    public int deleteDto(String noticeId);
    
    public int hit(String noticeId);
    
    
    // contentForm 안에 이전글, 다음글 구현
    String getPrevNoticeId(String noticeId);
    
    String getNextNoticeId(String noticeId);
    
    // 총 게시물 수
    public int getTotalCount();

    // 페이징 처리 구현
    List<NoticeDto> findAllWithPaging(@Param("offset") int offset, @Param("limit") int limit);
    
    
    // 제목, 내용 별 서치!
    List<NoticeDto> searchWithPaging(@Param("searchType") String searchType, @Param("searchKeyword") String searchKeyword, @Param("offset") int offset, @Param("limit") int limit);
    int getSearchCount(@Param("searchType") String searchType, @Param("searchKeyword") String searchKeyword);

	

    

    
    	
}
