package com.sns.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManager {
	
	// 실제 업로드가 진행될 곳
	public static final String FILE_UPLOAD_PATH = "D:\\오승환\\5_spring_project\\sns\\workspace\\images/";
	
	// input : userLoginId, file(이미지 파일)    output : 웹 imagePath
		public String saveFile(String loginId, MultipartFile file) {
			// 폴더 생성
			// 예 : aaaa_1230951/sun.png     // 밀리 세컨드
			String directoryName = loginId + "_" + System.currentTimeMillis();
			String filePath = FILE_UPLOAD_PATH + directoryName;
			
			File directory = new File(filePath);
			if(!directory.mkdir()) {
				// 폴더 생성 실패 시 경로 null
				return null;
			};
			
			// 파일 업로드 : byte 단위로 업로드
			try {
				byte[] bytes = file.getBytes();
				//★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★
				//	한글 이름 이미지는 올릴 수 없으므로 나중에 영문자로 바꿔서 올리기 구현까지
				//★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★
				Path path = Paths.get(filePath + "/" + file.getOriginalFilename());   // 디렉토리 경로 + 사용자가 올린 파일 명
				Files.write(path, bytes);
			} catch (IOException e) {
				e.printStackTrace();
				return null;  					// 이미지 업로드 실패 시 null 리턴   =>   이미지는 필수가아님
			}
			
			// 파일 업로드가 성공 => 웹 이미지 url path를 리턴
			// 주소는 이렇게 될 것이다. (예언)
			// http://localhost/images/aaaa_1698922766893/sun.png
			
			return "/images/" + directoryName + "/" + file.getOriginalFilename();
			
		}
	
	
	
}
