package com.runningmate.domain.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.runningmate.domain.board.dto.QuestionDto;
import com.runningmate.domain.board.mapper.IQuestionMapper;

@Service
@Transactional
public class QuestionServiceImpl implements IQuestionService {

	@Autowired
	IQuestionMapper questionmapper;

	@Override
	public List<QuestionDto> list() {
		return questionmapper.list();
	}

	@Override
	public List<QuestionDto> listByCategory(String category) {
		return questionmapper.listByCategory(category);
	}

	@Override
	public int write2(QuestionDto questionDto) {
		return questionmapper.write2(questionDto);

	}

	@Override
	public QuestionDto viewDto(String questionId) {
		return questionmapper.viewDto(questionId);

	}

	@Override
	public int deleteDto(String questionId) {
		return questionmapper.deleteDto(questionId);
	}

	@Override
	public List<QuestionDto> searchQuestions(String keyword) {
		return questionmapper.searchQuestions(keyword);
	}
}
