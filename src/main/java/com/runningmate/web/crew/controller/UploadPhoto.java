package com.runningmate.web.crew.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UploadPhoto {
	private String uploadFileName;
	private String storeFileName;
}
