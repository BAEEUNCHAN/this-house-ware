package com.contractor.app.doc.wep;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.contractor.app.doc.service.DocJoinVO;
import com.contractor.app.doc.service.DocService;
import com.contractor.app.employee.service.EmployeeVO;
import com.contractor.app.util.EmpAuthUtil;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/docBox")
public class DocController {

	private final DocService docService;
	private final EmpAuthUtil empAuthUtil;

	// 개인문서함 문서 전체조회
	@GetMapping("/docList")
	public void DocJoinList(Model model, Authentication authentication) {
		EmployeeVO employeeVO = empAuthUtil.getAuthEmp(authentication);
		List<DocJoinVO> list = docService.DocJoinList(employeeVO.getId());
		model.addAttribute("docBoxs", list);
		// return "docBoxs/docList";
	}

	// 문서결과별 문서 조회
	/*
	 * @GetMapping("/docApprovalStatusList") public void
	 * getApprovalStatus(@RequestParam String approvalStatus, Model model,
	 * Authentication authentication) { EmployeeVO employeeVO =
	 * empAuthUtil.getAuthEmp(authentication); String userId = employeeVO.getId();
	 * 
	 * List<DocJoinVO> list; switch (approvalStatus) { case "임시저장": list =
	 * docService.docApprovalStatusList("임시저장", userId); break; case "결재완료": list =
	 * docService.docApprovalStatusList("결재완료", userId); break; case "결재대기": list =
	 * docService.docApprovalStatusList("결재대기", userId); break; case "결재수신": list =
	 * docService.docApprovalStatusList("결재수신", userId); break; default: list = new
	 * ArrayList<>(); // 기본값 또는 에러 처리 break; }
	 * 
	 * model.addAttribute("docBoxs", list); }
	 */
	// 문서결과별 문서 조회
	@GetMapping("/docApprovalStatusList")
	public void getApprovalStatus(@RequestParam String approvalStatus, Model model, Authentication authentication) {
		EmployeeVO employeeVO = empAuthUtil.getAuthEmp(authentication);
		String userId = employeeVO.getId();

		// 결재 상태별 문서 조회
		List<DocJoinVO> list = docService.getDocumentsByStatusAndUserId(approvalStatus, userId);

		model.addAttribute("docBoxs", list);
	}

	// 부서문서함 문서 전체조회
	@GetMapping("/docDeptList")
	public String docDeptList(HttpSession session, Model model, Authentication authentication) {
		EmployeeVO employeeVO = empAuthUtil.getAuthEmp(authentication);
		int departmentNo = employeeVO.getDepartmentNo();
		if (departmentNo == 0) {
			return "errorPage"; // departmentNo가 없을 때의 처리
		}

		List<DocJoinVO> list = docService.DocDeptList(departmentNo);
		model.addAttribute("docBoxs", list);
		return "docBox/docDeptList";
	}

	// 부서문서 결재완료 조회
	@GetMapping("/docDeptStatusList")
	public String getDeptStatus(@RequestParam String approvalStatus, HttpSession session, Model model,
			Authentication authentication) {
		EmployeeVO employeeVO = empAuthUtil.getAuthEmp(authentication);
		int departmentNo = employeeVO.getDepartmentNo();
		if (departmentNo == 0) {
			return "errorPage"; // departmentNo가 없을 때의 처리
		}

		List<DocJoinVO> list = docService.docDeptStatusList(approvalStatus, departmentNo);
		model.addAttribute("docBoxs", list);
		return "docBox/docDeptStatusList";
	}

	// 부서문서 중요문서 업데이트
	@PostMapping("/updateDeptImportant")
	@ResponseBody
	public ResponseEntity<String> updateDeptImportant(@RequestParam String edmsDocNo, @RequestParam String important,
			HttpSession session, Authentication authentication) {
		EmployeeVO employeeVO = empAuthUtil.getAuthEmp(authentication);
		int departmentNo = employeeVO.getDepartmentNo();
		if (departmentNo == 0) {
			return ResponseEntity.badRequest().body("부서 정보가 필요합니다."); // departmentNo가 없을 때의 처리
		}

		docService.docUpdateImportant(edmsDocNo, important); // 중요 상태 업데이트
		return ResponseEntity.ok("중요문서로 설정 완료");
	}

	// 부서문서 중요문서 조회
	@GetMapping("/docDeptImportantList")
	public String getDeptImportantList(@RequestParam String important, HttpSession session, Model model,
			Authentication authentication) {
		EmployeeVO employeeVO = empAuthUtil.getAuthEmp(authentication);
		int departmentNo = employeeVO.getDepartmentNo();
		if (departmentNo == 0) {
			return "errorPage"; // departmentNo가 없을 때의 처리
		}

		List<DocJoinVO> list = docService.docDeptImportantList(important, departmentNo);
		model.addAttribute("docBoxs", list);
		return "docBox/docDeptImportantList"; // 반환할 뷰 이름
	}

}// 끝
