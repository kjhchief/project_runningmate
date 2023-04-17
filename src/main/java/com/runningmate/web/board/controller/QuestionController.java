package com.runningmate.web.board.controller;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.runningmate.domain.board.dto.QuestionDto;
import com.runningmate.domain.board.service.IQuestionService;
import com.runningmate.domain.mate.dto.Mate;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j

public class QuestionController {

	@Autowired
	IQuestionService iquestionservice;

	@RequestMapping("/qdwriteForm")
	public String fqDetail(HttpSession session, Model model) {
		Mate mate = (Mate) session.getAttribute("mate");
		model.addAttribute("mate", mate);

		return "board/qdwriteForm";

	}

	@RequestMapping("/qdwriteAction")
	@ResponseBody
	public String qdwriteAction(@RequestParam("questionTitle") String questionTitle,
			@RequestParam("questionContent") String questionContent, @RequestParam("category") String category, // 추가
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		QuestionDto questionDto = new QuestionDto();
		questionDto.setQuestionTitle(questionTitle);
		questionDto.setQuestionContent(questionContent);
		questionDto.setCategory(category); // 설정

		int result = iquestionservice.write2(questionDto);

		if (result == 1) {
			return "<script>alert('글쓰기 성공!'); location.href= '/qdlistForm'; </script>";
		} else {
			return "<script>alert('글쓰기 실패!');location.href='/qdwriteForm';</script>";
		}
	}

	@RequestMapping("/qdlistForm")
	public String questionForm(Model model, HttpSession session) {
		List<QuestionDto> list = iquestionservice.list();
		List<QuestionDto> frequentQuestions = iquestionservice.listByCategory("자주묻는질문"); // 자주 묻는 질문 글 불러오기

		Mate mate = (Mate) session.getAttribute("mate");
		model.addAttribute("mate", mate);

		model.addAttribute("list", list);
		model.addAttribute("frequentQuestions", frequentQuestions); // 자주 묻는 질문 글을 뷰에 전달

		return "board/qdlistForm";
	}

	@RequestMapping("/qdlistForm/{category}")
	public String questionForm(@PathVariable("category") String category, Model model, HttpSession session) {
		List<QuestionDto> list = iquestionservice.listByCategory(category);
		Mate mate = (Mate) session.getAttribute("mate");
		model.addAttribute("mate", mate);
		model.addAttribute("list", list);
		model.addAttribute("category", category); // 카테고리를 모델에 추가
		return "board/qdlistForm2";
	}

	// 카테고리 qdcontenForm
	@GetMapping("/qdcontentForm/{questionId}")
	public String qdcontentForm(@PathVariable("questionId") String questionId, Model model, HttpSession session) {
		QuestionDto question = iquestionservice.viewDto(questionId);
		model.addAttribute("question", question);

		Mate mate = (Mate) session.getAttribute("mate");
		model.addAttribute("mate", mate);
		return "board/qdcontentForm";
	}

//   자주묻는 질문
//    컨트롤러 코드
	@RequestMapping("/qdcontentForm")
	public String qdcontentForm(@RequestParam("questionId") String questionId, Model model, HttpServletRequest request,
			HttpSession session) {

		// 세션 추가
		Mate mate = (Mate) session.getAttribute("mate");
		model.addAttribute("mate", mate);

		// 공지사항 상세정보
		QuestionDto dto = iquestionservice.viewDto(questionId);
		model.addAttribute("dto", dto); // 'question'을 'dto'로 변경

		return "board/qdcontentForm";

	}

	@RequestMapping("/qddeleteAction")
	@ResponseBody
	public String deleteAction(@RequestParam("questionId") String questionId,
			@RequestParam(value = "category", required = false) String category, HttpServletRequest request) {
		int result = iquestionservice.deleteDto(questionId);

		System.out.println("Question ID: " + questionId);

		if (result == 1) {
			if (category != null) {
				return "<script>alert('글삭제 성공!'); location.href= '/qdlistForm/" + category + "'; </script>";
			} else {
				return "<script>alert('글삭제 성공!'); location.href= '/qdlistForm'; </script>";
			}
		} else {
			if (category != null) {
				return "<script>alert('글삭제 실패!');location.href= '/qdcontentForm?questionId=" + questionId + "&category="
						+ category + "'; </script>";
			} else {
				return "<script>alert('글삭제 실패!');location.href= '/qdcontentForm?questionId=" + questionId
						+ "'; </script>";
			}
		}
	}

//	@RequestMapping(value = "/qdlistForm2", method = RequestMethod.POST)
//	public String redirectToQdlistForm2(@RequestParam("category") String category, HttpSession session, Model model) {
//		String encodedCategory = URLEncoder.encode(category, StandardCharsets.UTF_8);
//		Mate mate = (Mate) session.getAttribute("mate");
//		model.addAttribute("mate", mate);
//		return "redirect:/qdlistForm/" + encodedCategory;
//	}

	@GetMapping("/search")
	public String search(@RequestParam("keyword") String keyword, Model model, HttpSession session) {
		List<QuestionDto> searchResults = iquestionservice.searchQuestions(keyword);
		Mate mate = (Mate) session.getAttribute("mate");
		model.addAttribute("mate", mate);
		model.addAttribute("searchResults", searchResults);
		model.addAttribute("keyword", keyword);
		return "board/searchForm";
	}

}