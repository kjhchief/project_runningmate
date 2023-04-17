package com.runningmate.domain.board.service;

import java.util.List;

import com.runningmate.domain.board.dto.QuestionDto;

public interface IQuestionService {

	public List<QuestionDto> list();

	public QuestionDto viewDto(String questionId);

	public List<QuestionDto> listByCategory(String category);

	public int write2(QuestionDto questionDto);

	public int deleteDto(String questionId);

	List<QuestionDto> searchQuestions(String keyword);

}
