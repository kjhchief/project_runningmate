package com.runningmate.domain.member.dto;

import java.util.Calendar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Members {
 private String memberId;
 private String name;
 private String password;
 private String gender;
 private Calendar birthdate;
 private String phoneNumber;
 private String city;
 private String region;
 private String memberClass;
 private String kakaoYn;
 private float managerTemp;
 private int commentCount;
 private int joinCount;
 private int hostCount;
}
