package com.contractor.app.reply.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.contractor.app.complain.service.ComplainService;
import com.contractor.app.complain.service.ComplainsVO;
import com.contractor.app.employee.service.DepartmentVO;
import com.contractor.app.employee.service.EmployeeService;
import com.contractor.app.employee.service.EmployeeVO;
import com.contractor.app.reply.service.ReplyService;
import com.contractor.app.reply.service.ReplysVO;

@Controller
public class ReplyController {
	private ComplainService complainService;
	private ReplyService replyService;
	private EmployeeService employeeService;
	
	@Autowired
	public ReplyController(ComplainService complainService, ReplyService replyService, EmployeeService employeeService) {
		this.complainService = complainService;
		this.replyService = replyService;
		this.employeeService = employeeService;
	}
	
	// 문의 전체조회 : URI - replyList / return - reply/replyList
	@GetMapping("replyList")
	public String replyList(ComplainsVO complainVO, Model model) {
		List<ComplainsVO> list = complainService.complainList();
		model.addAttribute("complains", list);
		ComplainsVO findVO = complainService.complainInfo(complainVO);
		model.addAttribute("complain", findVO);
		return "reply/replyList";
	}
	
	// reply 단건 + 문의답변
	// employees 테이블 List / reply + employees 등록
	@GetMapping("replyInfo")
	public String replyInfo(ComplainsVO complainVO, Model model, ReplysVO replyVO, DepartmentVO departmentVO, EmployeeVO employeeVO, int complainNo) {
		ComplainsVO findVO = complainService.complainInfo(complainVO);
		model.addAttribute("complain", findVO);
		List<DepartmentVO> listDept = employeeService.getDepartmentList();
		List<EmployeeVO> listEmp = employeeService.getEmployees();
		List<ComplainsVO> list = complainService.complainDeptInfo(complainVO);

		model.addAttribute("replys", list);
		model.addAttribute("depts", listDept);
		model.addAttribute("emps", listEmp);
		return "reply/replyInfo";
	}
	
	@PostMapping("replyInfo")
	public String replyProcess(ComplainsVO complainVO, ReplysVO replyVO) {
		// 처리과정업데이트
		complainService.updateComplainProgress(complainVO);
		// 댓글등록
		int complainNo = replyService.insertReply(replyVO);
		return "redirect:replyInfo?complainNo="+replyVO.getComplainNo();
	}
	
	
	
	
}