package com.runningmate.domain.board.dto;

import java.util.Date;

import lombok.Data;


@Data

public class QuestionDto {
    private int questionId;
    private String questionTitle;
    private String questionContent;
    private String category; // 카테고리 필드 추가

    
}
