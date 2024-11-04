package com.contractor.app.flutter.web;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.contractor.app.board.service.BoardService;
import com.contractor.app.board.service.PostsVO;
import com.contractor.app.employee.service.EmployeeService;
import com.contractor.app.employee.service.EmployeeVO;

import lombok.RequiredArgsConstructor;

@RestController // 전부 @ResponseBody 가 되며 데이터만 주고 받는다.
@CrossOrigin
@RequiredArgsConstructor
public class FlutterWithController {
	
	private final EmployeeService employeeService;
	private final BoardService boardService;
	private final BCryptPasswordEncoder encoder;
	
	@PostMapping("flutter/login")
	public EmployeeVO login(@RequestBody EmployeeVO empVO) {
		System.out.println(empVO);
		EmployeeVO finVo = new EmployeeVO();
		try {
			finVo = employeeService.getEmployee(empVO);
			// 계정이 퇴직자 일경우
			if(finVo.getResignDt() != null) {
				return null;
			}
		}catch (Exception e) {
			return null;
		}
		
		
		if(!encoder.matches(empVO.getPassword(), finVo.getPassword())) {
			return null;
		}
		
		return finVo;
	}
	
	@GetMapping("flutter/announcements")
	public List<PostsVO> getAnnouncements(){
		PostsVO pVo = new PostsVO();
		pVo.setBoardsNo(1);
		// 게시판 정보만 가져온다.
		List<PostsVO> list = boardService.getAnnouncements();
		
		return list;
	}
	
	@GetMapping("flutter/announcement")
	public PostsVO getAnnouncement(@RequestParam int no) {
		PostsVO postsVO = new PostsVO();
		postsVO.setPostsNo(no);
		PostsVO findVO = boardService.postInfo(postsVO);
		return findVO;
	}
}
