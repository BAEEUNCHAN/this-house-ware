package com.contractor.app.employee.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.contractor.app.employee.service.EmployeeService;
import com.contractor.app.employee.service.EmployeeVO;
import com.contractor.app.employee.service.impl.EmailServiceImpl;
import com.contractor.app.util.EmpAuthUtil;
import com.contractor.app.util.RandomValueUtil;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class employeeController {
	
	private final EmployeeService employeeService;
	private final PasswordEncoder encoder;
	private final EmailServiceImpl emailService;
	private final EmpAuthUtil empAuthUtil;

	@GetMapping("login")
	public String loginForm() {
		return "employee/login";
	}
	
	@GetMapping("employee/findId")
	public String findIdForm() {
		return "employee/findId";
	}
	
	@PostMapping("employee/findId")
	@ResponseBody
	public String findId(@RequestParam(required = false) String email ) {
		EmployeeVO empVO = new EmployeeVO();
		empVO.setEmail(email);
		
		try {
			empVO = employeeService.getEmployeeByEmail(empVO);
		} catch (Exception e) {
			return "error1";
		}
		
		try {
			emailService.sendEmail(email, "회원님의 아이디입니다.", empVO.getId());
		} catch (Exception e) {
			return "error2";
		}
		
		
		return email;
	}
	
	@GetMapping("employee/findPassword")
	public String findPasswordForm() {
		return "employee/findPassword";
	}
	
	@PostMapping("employee/getAuth")
	@ResponseBody
	public String getAuth(@RequestBody EmployeeVO empVO) {
		EmployeeVO findVO = null;
		String randomValue = RandomValueUtil.getRandomValue();
		
		try {
			findVO = employeeService.getEmployee(empVO);
		} catch (Exception e) {
			return "error1"; // 존재하지 않는 아이디
		}

		if(!(findVO.getEmail().equals(empVO.getEmail()) 
				&& findVO.getId().equals(empVO.getId()))){
			return "error2"; // 이메일과 아이디가 매칭되지 않는다.
		}
		
		try {
			employeeService.modifyAuthentication(empVO.getId(),randomValue);
		}catch (Exception e) {
			return "error3"; // DB 업데이트 실패
		}
		
		try {
			emailService.sendEmail(empVO.getEmail(), "회원님의 인증코드입니다.",randomValue);
		} catch (Exception e) {
			return "error4"; // 이메일 전송 실패
		}
		
		return "success";
	}

	@PostMapping("employee/changePw")
	@ResponseBody
	public String changePw(@RequestBody EmployeeVO empVO) {
		System.out.println(empVO);
		String newPassword = empVO.getPassword();
		newPassword = encoder.encode(newPassword);
		empVO.setPassword(newPassword);
		// 아이디, 새로운비밀번호, 인증 값 을 토대로 pl/sql 함수 실행하기
		String answer = employeeService.canChangePw(empVO.getId(), 
				empVO.getAuthenticationsValue());
		if(answer.equals("-1")) {
			return "error-1"; // 서버 자체적 오류
		}else if(answer.equals("1")) {
			return "error1"; // 인증값 또는 아이디 오류
		}else if(answer.equals("2")) {
			return "error2"; // 인증값 만료됨
		}
		
		try {
			employeeService.modifyPasswordByEmp(empVO);
		} catch (Exception e) {
			return "error-1";
		}
		
		return "success";
	}
	
	@GetMapping("employee/info/{id}")
	public String employeeInfo(@PathVariable String id,EmployeeVO empVO ,Model model , Authentication authentication) {
		// 해당 페이지를 볼 권한이 없는경우
		if(!empAuthUtil.authChek(authentication, id , true)) {
			return "common/error/403";
		}
		
		empVO.setId(id);
		empVO = employeeService.getEmployee(empVO);
		model.addAttribute("employee",empVO);
		return "employee/employeeInfo";
	}
	
	@GetMapping("employee/modify/{id}")
	public String employeeModifyForm(@PathVariable String id,EmployeeVO empVO ,Model model, Authentication authentication) {
		
		// 해당 페이지를 볼 권한이 없는경우
		if(!empAuthUtil.authChek(authentication, id , true)) {
			return "common/error/403";
		}
		
		empVO.setId(id);
		model.addAttribute("employee",empVO);
		return "employee/employeeModify";
	}
	
	@PostMapping("employee/modify/{id}")
	@ResponseBody
	public String employeeModify(@PathVariable String id
			,@RequestParam(required = false) String phone 
			,@RequestParam(required = false) String newPassword
			,@RequestParam(required = false) String checkNewPassword
			,@RequestParam(required = false) String checkPassword
			) {
		EmployeeVO empVO = new EmployeeVO();
		empVO.setId(id);
		empVO = employeeService.getEmployee(empVO);
		
		// 비밀번호로 본인이 맞는지 확인
		if(!encoder.matches(checkPassword, empVO.getPassword()) ) {
			return "error1"; // 확인 비밀번호 불일치
		}
		
		if(phone.length() <=0 && newPassword.length() <=0 ) {
			return "error2"; // 변경되는 정보가 없음을 알림
		}
		
		// 새로운 비밀번호가 있으면 비밀번호 변경작업 실행
		if(newPassword.length() > 0) {
			// 비밀번호 변경작업
			String encodedNewPassword = encoder.encode(newPassword);
			empVO.setPassword(encodedNewPassword);
		}
		
		// 전화번호 정보가 있으면 전화번호 변경작업 실행
		if(phone.length() > 0) {
			empVO.setPhone(phone);
		}
		
		try {
			employeeService.modifyEmployee(empVO);
		} catch (Exception e) {
			return "error3"; // 서버 오류 반환
		}
		
		return "success";
	}
}
