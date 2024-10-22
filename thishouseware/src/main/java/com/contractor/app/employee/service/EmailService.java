package com.contractor.app.employee.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;
    
    public String sendMail(EmailVO emailVO, String type) {
    	String authenticationNum = createCode();
    	
    	MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    	
    	try {
    		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,false, "UTF-8" );
    		mimeMessageHelper.setTo(emailVO.getTo());
    		mimeMessageHelper.setSubject(emailVO.getSubject()); // 메일 제목
            mimeMessageHelper.setText(setContext(authenticationNum, type), true); // 메일 본문 내용, HTML 여부
            javaMailSender.send(mimeMessage);
            return authenticationNum;
    	}catch(MessagingException e) {
    		return "failed";
    	}
    }
    
    // 인증번호 및 임시 비밀번호 생성 메서드
    public String createCode() {
        Random random = new Random();
        StringBuffer key = new StringBuffer();

        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(4);

            switch (index) {
                case 0: key.append((char) ((int) random.nextInt(26) + 97)); break;
                case 1: key.append((char) ((int) random.nextInt(26) + 65)); break;
                default: key.append(random.nextInt(9));
            }
        }
        return key.toString();
    }
    
    // thymeleaf를 통한 html 적용
    public String setContext(String code, String type) {
        Context context = new Context();
        context.setVariable("code", code);
        return templateEngine.process(type, context);
    }
}
