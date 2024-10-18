package com.contractor.app.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration// 스프링 설정파일
public class WebMVCConfig implements WebMvcConfigurer{
	@Value("${file.upload.path}")
	private String uploadPath;
	
	// 리소스 핸들링
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
		// * 이 하나만 붙으면 한단계밬에 안된다.
		.addResourceHandler("/upload/**") // URL
		.addResourceLocations("file:///"+uploadPath); // 실제 경로
		// http://localhost:8099/컨텍스트패스/imgs/파일명.jpg 을 입력하여 이미지를 열수 있다.
	}
}
