package com.runningmate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class DateTimeTest {
	
	@Test
	void timeTest() {
	LocalDateTime localDateTime = LocalDateTime.parse("2023-04-05 16:26:00.0", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"));

	System.out.println("localTime: " + localDateTime);
	}

}
