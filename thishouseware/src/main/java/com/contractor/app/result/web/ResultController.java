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
	public ResultController(ComplainService complainService, ReplyService replyService, ResultService resultService,
			EmployeeService employeeService) {
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
	public String resultInfo(ComplainsVO complainVO, Model model, ReplysVO replyVO, DepartmentVO departmentVO,
			EmployeeVO employeeVO, ResultsVO resultVO, int complainNo) {
		ComplainsVO findVO = complainService.complainInfo(complainVO);
		model.addAttribute("complain", findVO);
		ResultsVO findVO2 = resultService.resultInfo(resultVO);
		model.addAttribute("result", findVO2);
		List<DepartmentVO> listDept = employeeService.getDepartmentList();
		List<EmployeeVO> listEmp = employeeService.getEmployees();
		List<ComplainsVO> list = complainService.complainDeptInfo(complainVO);
		List<ComplainsVO> listRes = complainService.resultList(complainVO);
		model.addAttribute("replys", list);
		model.addAttribute("results", listRes);
		model.addAttribute("depts", listDept);
		model.addAttribute("emps", listEmp);
		return "result/resultInfo";
	}

	@PostMapping("resultInfo")
	public String resultInfoInsert(ComplainsVO complainVO, ResultsVO resultVO) {

		if (resultVO.getResultSolution() == "") {
			// 처리과정업데이트
			complainService.updateComplainProgress(complainVO);
		}else {
			// 무조건 보고완료로 업데이트
			complainService.updateComplainProgress(complainVO);
			// 솔루션, 피드백 등록
			int complainNo = resultService.insertResult(resultVO);
		}

		return "redirect:result/resultInfo?complainNo=" + resultVO.getComplainNo();
	}
	
	// 보고완료 수정 페이지
	@GetMapping("resultUpdate")
	public String resultUpdateForm(ComplainsVO complainVO, ResultsVO resultVO, Model model) {
		ComplainsVO findVO = complainService.complainInfo(complainVO);
		model.addAttribute("complain", findVO);
		ResultsVO findVO2 = resultService.resultInfo(resultVO);
		model.addAttribute("result", findVO2);
		List<DepartmentVO> listDept = employeeService.getDepartmentList();
		List<EmployeeVO> listEmp = employeeService.getEmployees();
		List<ComplainsVO> list = complainService.complainDeptInfo(complainVO);
		List<ComplainsVO> listRes = complainService.resultList(complainVO);
		model.addAttribute("replys", list);
		model.addAttribute("results", listRes);
		model.addAttribute("depts", listDept);
		model.addAttribute("emps", listEmp);
		return "result/resultUpdate";
	}
	
	// 보고완료 수정 처리
	@PostMapping("resultUpdate")
	public String resultUpdateProcess(ResultsVO resultVO, ComplainsVO complainVO) {
		
		if (resultVO.getResultSolution() == "") {
			// 처리과정업데이트
			complainService.updateComplainProgress(complainVO);
		}else {
			// 처리과정업데이트
			complainService.updateComplainProgress(complainVO);
			// result 문의완료부분 update
			resultService.updateResult(resultVO);
			
		}
		
		return "redirect:resultInfo?complainNo="+resultVO.getComplainNo();
	}

}
