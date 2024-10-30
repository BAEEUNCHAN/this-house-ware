package com.contractor.app.security.handler;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.contractor.app.schedule.service.AttendanceService;
import com.contractor.app.schedule.service.AttendanceVO;
import com.contractor.app.security.service.LoginUserVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Component// 컴포넌트 스캔 대상이 되어야하기에
@RequiredArgsConstructor
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private final AttendanceService attendanceService;
	
	/**
	 * 로그인 성공시 실행된다.
	 */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
       
        LoginUserVO loginVO = (LoginUserVO) authentication.getPrincipal();
        String id = loginVO.getUsername();

        try {
        	AttendanceVO attendanceVO= attendanceService.getLastAttendanceById(id);
        	// 근태코드를 새션상으로 올린다.(로그아웃시 모든 세션이 제거되므로 따로 제거할 필요 없다.)
        	HttpSession session = request.getSession();
            session.setAttribute("attendance",attendanceVO);
            System.out.println(attendanceVO.getAttendancesCode());
            getRedirectStrategy().sendRedirect(request,response,"/");
            return;
        }catch (Exception e) {
			System.out.println("서버오류");
		}
        AttendanceVO attendanceVO= new AttendanceVO(); 
        attendanceVO.setAttendancesCode("j2");
    	HttpSession session = request.getSession();
        session.setAttribute("attendance",attendanceVO);
        System.out.println(attendanceVO.getAttendancesCode());
        getRedirectStrategy().sendRedirect(request,response,"/");
    }
}
