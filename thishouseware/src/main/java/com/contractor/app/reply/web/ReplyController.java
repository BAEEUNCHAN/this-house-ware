package com.contractor.app.reply.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@GetMapping("reply/replyList")
	public String replyList(ComplainsVO complainVO, Model model) {
		//List<ComplainsVO> list = complainService.complainList();
		List<ComplainsVO> list = complainService.complainList0to2();
		model.addAttribute("complains", list);
		ComplainsVO findVO = complainService.complainInfo(complainVO);
		model.addAttribute("complain", findVO);
		return "reply/replyList";
	}
	
	// reply 단건 + 문의답변
	// employees 테이블 List / reply + employees 등록
	@GetMapping("reply/replyInfo")
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
	
	@PostMapping("reply/replyInfo")
	public String replyProcess(ComplainsVO complainVO, ReplysVO replyVO) {
		
		if(replyVO.getReplyContent() == "") {
			// 처리과정업데이트
			complainService.updateComplainProgress(complainVO);
		}else {
			complainService.updateComplainProgress(complainVO);
			// 댓글등록
			int complainNo = replyService.insertReply(replyVO);
		}
		return "redirect:/reply/replyInfo?complainNo="+replyVO.getComplainNo();
	}
	
	
	// 삭제
	@ResponseBody
	@DeleteMapping("reply/replyDelete")
	public String replyDelete(@RequestParam Integer replyNo) {
		ReplysVO replyVO = new ReplysVO();
		replyService.replyDelete(replyNo);
		return "redirect:reply/replyInfo?complainNo=" + replyVO.getComplainNo();
	} 
	
	// 댓글수정
	@ResponseBody
	@PostMapping("reply/replyUpdate")
	public String replyUpdate(@RequestParam(value = "complainNo", required = false) Integer complainNo, ReplysVO replyVO) {
		replyService.replyUpdate(replyVO);
		return "success";
	}
	

	

	
}