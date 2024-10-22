package com.contractor.app.result.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.contractor.app.company.service.ResultsVO;
import com.contractor.app.complain.service.ComplainService;
import com.contractor.app.complain.service.ComplainsVO;
import com.contractor.app.employee.service.DepartmentVO;
import com.contractor.app.employee.service.EmployeeService;
import com.contractor.app.employee.service.EmployeeVO;
import com.contractor.app.reply.service.ReplyService;
import com.contractor.app.reply.service.ReplysVO;
import com.contractor.app.result.service.ResultService;

@Controller
public class ResultController {
	private ComplainService complainService;
	private ReplyService replyService;
	private ResultService resultService;
	private EmployeeService employeeService;
	
	@Autowired
	public ResultController(ComplainService complainService, ReplyService replyService, ResultService resultService, EmployeeService employeeService) {
		this.complainService = complainService;
		this.replyService = replyService;
		this.resultService = resultService;
		this.employeeService = employeeService;
	}
	
	// 상황완료 전체조회 : URI - resultList / return - result/resultList
	@GetMapping("resultList")
	public String resultList(ComplainsVO complainVO, Model model) {
		List<ComplainsVO> list = complainService.complainResultList();
		model.addAttribute("complains", list);
		ComplainsVO findVO = complainService.complainInfo(complainVO);
		model.addAttribute("complain", findVO);
		return "result/resultList";
	}
	
	// result 단건
	@GetMapping("resultInfo")
	public String resultInfo(ComplainsVO complainVO, Model model, ReplysVO replyVO, DepartmentVO departmentVO, EmployeeVO employeeVO, ResultsVO resultVO, int complainNo) {
		ComplainsVO findVO = complainService.complainInfo(complainVO);
		model.addAttribute("complain", findVO);
		ResultsVO findResultVO = resultService.resultInfo(resultVO);
		model.addAttribute("result", findResultVO);
		List<DepartmentVO> listDept = employeeService.getDepartmentList();
		List<EmployeeVO> listEmp = employeeService.getEmployees();
		List<ComplainsVO> list = complainService.complainDeptInfo(complainVO);
		List<ResultsVO> listRes = resultService.resultList();

		model.addAttribute("replys", list);
		model.addAttribute("depts", listDept);
		model.addAttribute("emps", listEmp);
		model.addAttribute("results", listRes);
		return "result/resultInfo";
	}
	@PostMapping("resultInfo")
	public String resultInfoInsert(ComplainsVO complainVO, ResultsVO resultVO) {
		
		int complainNo = resultService.insertResult(resultVO);
		return "redirect:result/resultInfo?complainNo="+complainNo;
	}
	
}
