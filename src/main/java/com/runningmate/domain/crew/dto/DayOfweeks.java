package com.runningmate.domain.crew.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class DayOfweeks {
	private int dayofMonth;
	private String dayofweek;
	private String monthDay;
	private LocalDateTime localDateTime;
}










