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
import com.runningmate.domain.mate.dto.Mate;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class NoticeController {

	@Autowired
	INoticeService inoticeservice;

	@Autowired
	IReplyService ireplyservice;

	@Autowired
	private HttpServletRequest request;

	
	// 리스트 표시 + 페이징처리 + 옵션별 서치

	@RequestMapping("/listForm")
	public String listForm(@RequestParam(name = "page", defaultValue = "1") int currentPage,
			@RequestParam(name = "searchType", required = false) String searchType,
			@RequestParam(name = "searchKeyword", required = false) String searchKeyword, Model model,
			HttpSession session) {

		Mate mate = (Mate) session.getAttribute("mate");
		model.addAttribute("mate", mate);

		final int POSTS_PER_PAGE = 10;

		List<NoticeDto> list;
		int totalPosts;
		if (searchType != null && searchKeyword != null && !searchKeyword.trim().isEmpty()) {
			list = inoticeservice.searchNotices(searchType, searchKeyword, currentPage, POSTS_PER_PAGE);
			totalPosts = inoticeservice.getSearchTotalPosts(searchType, searchKeyword);
		} else {
			list = inoticeservice.getNotices(currentPage, POSTS_PER_PAGE);
			totalPosts = inoticeservice.getTotalPosts();
		}

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
	public String writeForm(HttpSession session, Model model) {
		Mate mate = (Mate) session.getAttribute("mate");
		model.addAttribute("mate", mate);
		log.info("Mate : {}", mate);

		if (mate == null) {
			// 로그인 화면 이동
			return "/mate/login";
		} else {
			model.addAttribute("mate", mate);
			return "board/writeForm";
		}
	}

	@RequestMapping("/writeAction")
	@ResponseBody
	public String writeAction(@RequestParam("noticeTitle") String noticeTitle,
			@RequestParam("noticeContent") String noticeContent, RedirectAttributes redirectAttributes,
			HttpServletRequest request) {
		int result = inoticeservice.write(noticeTitle, noticeContent);
		if (result == 1) {
			return "<script>alert('글쓰기 성공!'); location.href= '/listForm'; </script>";
		} else {
			return "<script>alert('글쓰기 실패!');location.href='/writeForm';</script>";
		}
	}

	// 댓글쓰기 화면
	@RequestMapping("/contentForm")
	public String contentForm(@RequestParam("noticeId") String noticeId, Model model, HttpServletRequest request,
			HttpSession session) {

		// 세션 추가
		Mate mate = (Mate) session.getAttribute("mate");
		model.addAttribute("mate", mate);

		// 조회수 증가
		inoticeservice.hit(noticeId);

		// 공지사항 상세정보
		NoticeDto dto = inoticeservice.viewDto(noticeId);

		// 공지사항에 대한 댓글 목록
		List<ReplyDto> replyList = ireplyservice.replyList(noticeId);
		log.info("댓글 목록 : {}", replyList);

		// 댓글 수 가져오기
		int replyCount = ireplyservice.getReplyCount(noticeId);

		// 이전 글과 다음 글의 noticeId 가져오기
		String prevNoticeId = inoticeservice.getPrevNoticeId(noticeId);
		String nextNoticeId = inoticeservice.getNextNoticeId(noticeId);

		model.addAttribute("replyList", replyList);
		model.addAttribute("dto", dto);
		model.addAttribute("replyCount", replyCount);
		model.addAttribute("prevNoticeId", prevNoticeId);
		model.addAttribute("nextNoticeId", nextNoticeId);

		return "board/contentForm";

	}

	// 수정 폼으로 이동
	@RequestMapping("/updateForm")
	public String updateForm(@RequestParam("noticeId") String noticeId, Model model, HttpSession session) {
		Mate mate = (Mate) session.getAttribute("mate");
		model.addAttribute("mate", mate);
		NoticeDto dto = inoticeservice.viewDto(noticeId);
		model.addAttribute("dto", dto);
		return "board/updateForm";
	}

	// 게시글 수정 폼 구형
	@RequestMapping("/updateAction")
	@ResponseBody

	public String updateAction(@RequestParam("noticeId") String noticeId,
			@RequestParam("noticeTitle") String noticeTitle, @RequestParam("noticeContent") String noticeContent,
			HttpServletRequest request) {

		int result = inoticeservice.updateDto(noticeId, noticeTitle, noticeContent);
		if (result == 1) {
			return "<script>alert('글수정 성공!'); location.href= '/listForm'; </script>";

		} else {
			return "<script>alert('글수정 실패!');location.href= '/contentForm?noticeId'" + noticeId + "';'; </script>";

		}

	}

	// 게시글 삭제 구현
	@RequestMapping("/deleteAction")
	@ResponseBody
	public String deleteAction(@RequestParam("noticeId") String noticeId, HttpServletRequest request) {
		int result = inoticeservice.deleteDto(noticeId);
		if (result == 1) {
			return "<script>alert('글삭제 성공!'); location.href= '/listForm'; </script>";

		} else {
			return "<script>alert('글삭제 실패!');location.href= '/contentForm?notice_id'" + noticeId + "';'; </script>";
		}
	}

	// 댓글 작성 구현
	@RequestMapping("/writeReplyAction")
	public String writeReplyAction(@RequestParam("replyContent") String replyContent,
			@RequestParam("replyNoticeIndex") int replyNoticeIndex, Model model, HttpSession session) {

		Mate mate = (Mate) session.getAttribute("mate");

		// mate 객체가 null인 경우 로그인 페이지로 이동
		if (mate == null) {
			return "redirect:/mate/login";
		}

		String email = mate.getEmail();
		log.info("이메일 : {}", email);

		// 이메일을 사용하여 댓글 작성 처리
		int result = ireplyservice.replyWrite(email, replyContent, replyNoticeIndex);

		// 댓글 추가 후 댓글 갯수 다시 조회해서 전달
		String replyNoticeIndexString = String.valueOf(replyNoticeIndex);
		int replyCount = ireplyservice.getReplyCount(replyNoticeIndexString);
		model.addAttribute("replyCount", replyCount);

		model.addAttribute("mate", mate);

		if (result == 1) {
			return "redirect:/contentForm?noticeId=" + replyNoticeIndex;
		} else {
			return "redirect:/contentForm?noticeId=" + replyNoticeIndex;
		}
	}

	@RequestMapping("/deleteReplyAction")
	@ResponseBody
	public String deleteReplyAction(@RequestParam("replyId") int replyId, @RequestParam("noticeId") int noticeId,
			HttpServletRequest request) {
		int result = ireplyservice.replyDeleteDto(replyId);
		if (result == 1) {

			return "<script>alert('댓글삭제 성공!!');location.href= '/contentForm?noticeId=" + noticeId + "';</script>";

		} else {
			return "<script>alert('댓글삭제 실패..');location.href= '/contentForm?noticeId=" + noticeId + "';</script>";
		}
	}

}