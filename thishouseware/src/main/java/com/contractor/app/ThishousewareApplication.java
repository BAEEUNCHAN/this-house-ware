package com.contractor.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.contractor.app.**.mapper")
public class ThishousewareApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThishousewareApplication.class, args);
	}

}
