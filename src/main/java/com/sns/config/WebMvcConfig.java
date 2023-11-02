package com.sns.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sns.common.FileManager;

@Configuration   //  설정을 위한 spring bean
public class WebMvcConfig implements WebMvcConfigurer {
	
	// 웹 이미지 path와 서버에 업로드 된 이미지와 매핑 설정
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
		.addResourceHandler("/images/**")    // web image path
		.addResourceLocations("file:///" + FileManager.FILE_UPLOAD_PATH);  //실제파일위치
	}
	
}
