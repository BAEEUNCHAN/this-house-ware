package com.contractor.app.security.handler;

import java.io.IOException;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.contractor.app.schedule.service.AttendanceService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component// 컴포넌트 스캔 대상이 되어야하기에
@RequiredArgsConstructor
public class LoginFailedHandler extends SimpleUrlAuthenticationFailureHandler {

    /**
     * 로그인 실패시 실행된다.
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
    		AuthenticationException exception) throws IOException, ServletException {
        String errorMessage = null;
        if(exception instanceof BadCredentialsException || exception instanceof InternalAuthenticationServiceException){
            errorMessage = "error1";//"아이디와 비밀번호가 맞지 않습니다. 다시 확인해 주십시오";
        }
        else if(exception instanceof LockedException){
            errorMessage = "error2";//"퇴직처리된 계정입니다. 관리자에게 문의하세요.";
        }
        else{
            errorMessage = "error";//"알 수 없는 이유로 로그인에 실패하였습니다. 관리자에게 문의하세요.";
        }
    	
        getRedirectStrategy().sendRedirect(request,response,"/login?errorMessage="+errorMessage);
    }
}
