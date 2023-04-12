   package com.runningmate.web.board.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.runningmate.domain.board.dto.NoticeDto;
import com.runningmate.domain.board.dto.ReplyDto;
import com.runningmate.domain.board.service.INoticeService;
import com.runningmate.domain.board.service.IReplyService;

@Controller
   public class NoticeController {

   
   @Autowired
  INoticeService inoticeservice;
  
   @Autowired
   IReplyService ireplyservice;
   
   
   @Autowired
   private HttpServletRequest request;

private Object totalPosts;

   
   
   @RequestMapping ("/")
   public String root() {
     return "redirect:board/listForm";
   }
   
   // 페이징처리 + 리스트 표시 

   
   @RequestMapping("/listForm")
   public String listForm(
       @RequestParam(name="page", defaultValue="1") int currentPage,
       @RequestParam(name="searchType", required=false) String searchType,
       @RequestParam(name="searchKeyword", required=false) String searchKeyword,
       Model model, HttpSession session) {

       final int POSTS_PER_PAGE = 10;

       // 검색어 디코딩
       if (searchKeyword != null) {
           try {
               searchKeyword = URLDecoder.decode(searchKeyword, "UTF-8");
           } catch (UnsupportedEncodingException e) {
               e.printStackTrace();
           }
       }

       List<NoticeDto> list;
       int totalPosts;
       if (searchType != null && searchKeyword != null && !searchKeyword.trim().isEmpty()) {
           list = inoticeservice.searchNotices(searchType, searchKeyword, currentPage, POSTS_PER_PAGE);
           totalPosts = inoticeservice.getSearchTotalPosts(searchType, searchKeyword);
       } else {
           list = inoticeservice.getNotices(currentPage, POSTS_PER_PAGE);
           totalPosts = inoticeservice.getTotalPosts();
       }
       
    // 여기에 목록 내용을 출력해봅니다.
       System.out.println("List contents: " + list);
       System.out.println("SearchType: " + searchType);
       System.out.println("SearchKeyword: " + searchKeyword);
       

       int totalPages = inoticeservice.getTotalPages(POSTS_PER_PAGE);
       int startPage = ((currentPage - 1) / 5) * 5 + 1;
       int endPage = startPage + 4;
       if (endPage > totalPages) {
           endPage = totalPages;
       }
       model.addAttribute("list", list);
       model.addAttribute("totalPages", totalPages);
       model.addAttribute("totalPosts", totalPosts); // 전체 게시글 수 추가
       model.addAttribute("startPage", startPage);
       model.addAttribute("endPage", endPage);
       model.addAttribute("currentPage", currentPage);
       model.addAttribute("POSTS_PER_PAGE", POSTS_PER_PAGE);
       model.addAttribute("searchType", searchType);
       model.addAttribute("searchKeyword", searchKeyword);

       return "board/listForm";
   }



   
   
    
   
   
   
   
    
    
    
    @RequestMapping("/writeForm")
    public String writeForm() {
       
       return "board/writeForm"; 
    }
    
    
   
    @RequestMapping("/writeAction")
    @ResponseBody

    public String writeAction(@RequestParam("noticeTitle") String noticeTitle,
                              @RequestParam("noticeContent") String noticeContent,
                              RedirectAttributes redirectAttributes,
                              HttpServletRequest request) {
        int result = inoticeservice.write(noticeTitle, noticeContent);
        if (result == 1) {
            System.out.println("글쓰기 성공했습니다..");
            return "<script>alert('글쓰기 성공!'); location.href= '/listForm'; </script>";
        } else {
            System.out.println("글쓰기 실패했습니다..");
            return "<script>alert('글쓰기 실패!');location.href='/writeForm';</script>";
        }
    }


    
    
    
  @RequestMapping("/contentForm")
  public String contentForm(@RequestParam("noticeId")String noticeId,
        Model model, HttpServletRequest request)
  {
     //조회수 증가
	  inoticeservice.hit(noticeId);
      
     //게시글 보기
     NoticeDto dto = inoticeservice.viewDto(noticeId);
     model.addAttribute("dto", dto);
     
     // 댓글 리스트 가져오기
     List<ReplyDto> replyList = ireplyservice.replyList(noticeId);
     model.addAttribute("replyList", replyList);
     
      // 댓글 수 가져오기
     int replyCount = ireplyservice.getReplyCount(noticeId);
     model.addAttribute("replyCount", replyCount);
     	
     // 이전 글과 다음 글의 noticeId 가져오기
     String prevNoticeId = inoticeservice.getPrevNoticeId(noticeId);
     String nextNoticeId = inoticeservice.getNextNoticeId(noticeId);

     model.addAttribute("prevNoticeId", prevNoticeId);
     model.addAttribute("nextNoticeId", nextNoticeId);
     
     
     
   return  "board/contentForm";
     
   
   
   
   
   
  }
  
  
  
  // 수정 폼으로 이동
  @RequestMapping("/updateForm")
  public String updateForm(@RequestParam("noticeId") String noticeId, Model model) {
    NoticeDto dto = inoticeservice.viewDto(noticeId);
    model.addAttribute("dto", dto);
    return "board/updateForm";
  }
  
  
 //
  @RequestMapping("/updateAction")
  @ResponseBody
  
  public String updateAction(@RequestParam("noticeId")String noticeId,
                           @RequestParam("noticeTitle")String noticeTitle,  
                           @RequestParam("noticeContent")String noticeContent,
                           HttpServletRequest request) {
     
     
   
      
     int result = inoticeservice.updateDto(noticeId , noticeTitle, noticeContent);
           if(result == 1) {
                System.out.println("글수정 성공했습니다..");
                return "<script>alert('글수정 성공!'); location.href= '/listForm'; </script>";
//              return "redirect:basic/listForm;";
                
                
             }else {
                System.out.println("글수정 실패했습니다..");
                return "<script>alert('글수정 실패!');location.href= '/contentForm?noticeId'" + noticeId + "';'; </script>";
//                return "redirect:updateForm?notice_id=" + notice_id; //updateForm으로 ㄱㄱ
     
  }
  
           
           
           

  }
  
  @RequestMapping("/deleteAction")
  @ResponseBody
  public String deleteAction(@RequestParam("noticeId") String noticeId,
                              HttpServletRequest request) {
      int result = inoticeservice.deleteDto(noticeId);
      if (result == 1) {
    	  System.out.println("글삭제 성공했습니다..");
          return "<script>alert('글삭제 성공!'); location.href= '/listForm'; </script>";
//          return "redirect:listForm;";
          
          
       }else {
          System.out.println("글삭제 실패했습니다..");
          return "<script>alert('글삭제 실패!');location.href= '/contentForm?notice_id'" + noticeId + "';'; </script>";
//          return "redirect:updateForm?notice_id=" + notice_id; //updateForm으로 ㄱㄱ
      }
  }
  
  
  
  @RequestMapping("/writeReplyAction")
  @ResponseBody
  public String writeReplyAction(
                            @RequestParam("replyContent")String replyContent,
                            @RequestParam("replyNoticeIndex")int replyNoticeIndex,
                            Model model,
                            HttpServletRequest request)
  {

      int result = ireplyservice.replyWrite(replyContent,replyNoticeIndex);
      String response;

      if(result == 1) {
          System.out.println("댓글달기 성공했습니다..");
          response = "<script>alert('댓글달기 성공!!');location.href= '/contentForm?noticeId=" +  replyNoticeIndex + "';</script>";
      } else {
          System.out.println("댓글달기 실패했습니다..");
          response = "<script>alert('댓글달기 실패');location.href= '/contentForm?noticeId=" +  replyNoticeIndex + "';</script>";
      }
      
      
   // 댓글 추가 후 댓글 갯수 다시 조회해서 전달
      String replyNoticeIndexString = String.valueOf(replyNoticeIndex);
      int replyCount = ireplyservice.getReplyCount(replyNoticeIndexString);
      model.addAttribute("replyCount", replyCount);
      return response;
  }


  

  @RequestMapping("/deleteReplyAction")
  @ResponseBody
  public String deleteReplyAction(@RequestParam("replyId") int replyId,
		                          @RequestParam("noticeId") int noticeId,
                              HttpServletRequest request) 
  {
      int result = ireplyservice.replyDeleteDto(replyId);
      if (result == 1) {
    	  System.out.println("댓글삭제 성공했습니다..");
    	  
          return "<script>alert('댓글삭제 성공!!');location.href= '/contentForm?noticeId=" +  noticeId + "';</script>";
//          return "redirect:listForm;";
          
          
       }else {
          System.out.println("댓글삭제 실패했습니다..");
          return "<script>alert('댓글삭제 성공');location.href= '/contentForm?noticeId=" +  noticeId + "';</script>";
//          return "redirect:updateForm?notice_id=" + notice_id; //updateForm으로 ㄱㄱ
      }
  }
  
  
   


  
  
  
  
  
  
  

}