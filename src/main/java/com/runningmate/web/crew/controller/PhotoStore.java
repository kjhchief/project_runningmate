package com.runningmate.web.crew.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

//업로드 된 파일 저장 및 관리
@Component
public class PhotoStore {

	@Value("${file.dir}")
	private String location;

	public String getFullPath(String filename) {
		return location + filename;
	}

	public List<UploadPhoto> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
		List<UploadPhoto> storeFileResult = new ArrayList<UploadPhoto>();
		for (MultipartFile multipartFile : multipartFiles) {
			if (!multipartFile.isEmpty()) {
				// 업로드 파일 저장
				UploadPhoto uploadFile = storeFile(multipartFile);
				storeFileResult.add(uploadFile);
			}
		}
		return storeFileResult;
	}

	public UploadPhoto storeFile(MultipartFile multipartFile) throws IOException {
		if (multipartFile == null || multipartFile.isEmpty()) {
			return null;
		}
		String originalFilename = multipartFile.getOriginalFilename();
		String storeFileName = createStoreFileName(originalFilename);
		multipartFile.transferTo(new File(getFullPath(storeFileName)));
		return new UploadPhoto(originalFilename, storeFileName);
	}

	private String createStoreFileName(String originalFilename) {
		String ext = extractExt(originalFilename);
		String uuid = UUID.randomUUID().toString();
		return uuid + "." + ext;
	}

	private String extractExt(String originalFilename) {
		int pos = originalFilename.lastIndexOf(".");
		return originalFilename.substring(pos + 1);
	}
}
