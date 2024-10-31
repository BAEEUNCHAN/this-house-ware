package com.contractor.app.appr.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.contractor.app.appr.service.ApprFavoriteVO;
import com.contractor.app.appr.service.ApprLineVO;
import com.contractor.app.appr.service.ApprService;
import com.contractor.app.appr.service.ApprVO;
import com.contractor.app.appr.service.ApproverVO;
import com.contractor.app.employee.service.DepartmentVO;
import com.contractor.app.employee.service.EmployeeService;
import com.contractor.app.employee.service.EmployeeVO;
import com.contractor.app.util.EmployeeUtil;

@Controller
@RequestMapping("/appr")
public class ApprController {
	private final EmployeeService employeeService;
	private final ApprService apprService;

	@Autowired
	ApprController(ApprService apprService, EmployeeService employeeService) {
		this.apprService = apprService;
		this.employeeService = employeeService;
	}

	// 결재선 전체조회
	@GetMapping("/apprLineList")
	public void apprLineList(Model model) {
		List<ApprLineVO> list = apprService.apprLineList();
		model.addAttribute("apprLines", list);
		// return "appr/apprLineList";
	}

	// 결재선 단건조회
	@GetMapping("/apprLineInfo") //
	public String apprLineInfo(ApprLineVO apprLineVO, Model model) {
		ApprLineVO findVO = apprService.apprLineInfo(apprLineVO);
		model.addAttribute("apprLines", findVO);
		return "appr/apprLineInfo";
	}

	// 결재선 추가 - 페이지(GET)
	@GetMapping("/apprLineInsert")
	public String apprLineForm() {
		return "appr/apprLineInsert";
	}

	// 결재선 추가 - 처리(POST)
	@PostMapping("/apprLineInsert")
	public String apprLineProcess(ApprLineVO apprLineVO) {
		int apprLine = apprService.apprLineInsert(apprLineVO);

		String url = null;

		if (apprLine > -1) {
			// 정상적으로 등록된 경우
			url = "redirect:apprLineInfo?approvalLineNo=" + apprLine;
			// "redirect:" 가 가능한 경우 GetMapping
		} else {
			// 등록되지 않은 경우
			url = "redirect:apprLineList";
		}
		return url;
	}

	// 결재선 삭제
	@GetMapping("/apprLineDelete")
	public String apprLineDelete(Integer approvalLineNo) {
		apprService.apprLineDelete(approvalLineNo);
		return "redirect:apprLineList";
	}

	// 결재선 수정 폼 조회 - GET 요청
	@GetMapping("/apprLineModify")
	public String apprLineUpdateForm(ApprLineVO apprLineVO, Model model) {
		ApprLineVO apprLineData = apprService.apprLineInfo(apprLineVO);
		model.addAttribute("apprLine", apprLineData);
		return "appr/apprLineModify";
	}

	// 결재선 수정 처리 - POST 요청
	@PostMapping("/apprLineModify")
	@ResponseBody // AJAX
	public Map<String, Object> apprLineUpdateAJAXJSON(@RequestBody ApprLineVO apprLineVO) {
		return apprService.apprLineUpdate(apprLineVO);
	}

	// 결재선 즐겨찾기 전체조회
	@GetMapping("/apprFavoriteList")
	public String apprFavoriteList(Model model) {
		List<ApprFavoriteVO> list = apprService.apprFavoriteList();
		model.addAttribute("apprFavorites", list);
		return "appr/apprFavoriteList";

	}

	// 결재선 즐겨찾기 업데이트
	@PostMapping("/favoriteUpdate")
	@ResponseBody
	public ResponseEntity<String> updateFavorite(@RequestParam int approvalLineNo, 
	                                             @RequestParam String id, 
	                                             @RequestParam String favoriteChk) {
	    ApprFavoriteVO favorite = new ApprFavoriteVO();
	    favorite.setApprovalLineNo(approvalLineNo);
	    favorite.setId(id);

	    if ("Y".equals(favoriteChk)) {
	        boolean exists = apprService.ifFavorite(approvalLineNo, id);
	        if (exists) {
	            apprService.favoriteUpdate(favorite);  // 이미 존재하는 경우 업데이트
	        } else {
	            apprService.insertFavorite(favorite);  // 없는 경우 새로운 즐겨찾기 추가
	        }
	    } else {
	        apprService.favoriteDelete(approvalLineNo, id);  // 'N'이면 즐겨찾기 삭제
	    }

	    return ResponseEntity.ok("즐겨찾기 상태가 업데이트되었습니다.");
	}

	// 즐겨찾기 삭제
	@GetMapping("/favoriteDelete")
	public String favoriteDelete(Integer favoriteNo, String id) {
		apprService.favoriteDelete(favoriteNo, id);
		return "redirect:apprFavoriteList";
	}

	/*
	 * // 결재자 정보 전체조회
	 * 
	 * @GetMapping("/apprList") public void apprList(@RequestParam Integer
	 * approvalLineNo, Model model) { List<ApproverVO> list =
	 * apprService.apprList(approvalLineNo); model.addAttribute("approvers", list);
	 * // return "appr/apprList"; }
	 */

	// 결재자 정보 전체조회(직급 포함)
	@GetMapping("/apprList")
	public void apprList(@RequestParam Integer approvalLineNo, Model model) {
		List<ApproverVO> list = apprService.apprList(approvalLineNo);

		// 직급 코드에 따른 직급 이름 설정
		for (ApproverVO approver : list) {
			String positionName = EmployeeUtil.getPostionName(approver.getPositionCode());
			approver.setPositionName(positionName); // 변환된 이름 설정
		}
		model.addAttribute("approvers", list);
	}

	// 결재자 정보 단건조회
	@GetMapping("/apprInfo") //
	public String apprInfo(ApprVO apprVO, Model model) {
		ApprVO findVO = apprService.apprInfo(apprVO);
		model.addAttribute("approvers", findVO);
		return "appr/apprInfo";
	}

	// 결재자 추가 - 페이지(GET)
	@GetMapping("/approverInsert")
	public String apprForm(Model model) {
		List<EmployeeVO> emps = new ArrayList<EmployeeVO>();
		emps = employeeService.getEmployees();
		emps.forEach(obj -> {
			String positionName = EmployeeUtil.getPostionName(obj.getPositionCode());
			obj.setPositionName(positionName);
		});
		model.addAttribute("employees", emps);
		List<DepartmentVO> departments = employeeService.getDepartmentList();
		model.addAttribute("departments", departments);
		return "appr/approverInsert";
	}

	// 부서별 직원 목록 조회
	@GetMapping("/getEmpDept")
	@ResponseBody
	public List<EmployeeVO> getEmpDept(@RequestParam("departmentNo") Integer departmentNo) {
		// 부서에 맞는 직원 리스트 가져오기
		List<EmployeeVO> employees = employeeService.getEmpDept(departmentNo);

		// 직급이름 설정
		employees.forEach(employee -> {
			String positionName = EmployeeUtil.getPostionName(employee.getPositionCode());
			employee.setPositionName(positionName);
		});

		return employees;
	}

	// 결재자 추가 - 처리(POST)
	@PostMapping("/approverInsert")
	public String apprProcess(ApprVO apprVO) {
		int approver = apprService.apprInsert(apprVO);

		String url = null;

		if (approver > -1) {
			// 정상적으로 등록된 경우
			url = "redirect:apprInfo?approverNo=" + approver;
			// "redirect:" 가 가능한 경우 GetMapping
		} else {
			// 등록되지 않은 경우
			url = "redirect:apprList";
		}
		return url;
	}

	// 결재선 삭제
	@PostMapping("/apprLineDelete")
	@ResponseBody
	public ResponseEntity<String> apprLineDelete(@RequestBody List<Integer> approvalLineNos) {
	    for (Integer approvalLineNo : approvalLineNos) {
	        apprService.apprLineDelete(approvalLineNo);
	    }
	    return ResponseEntity.ok("삭제 완료");
	}

	// 결재자 수정
	@GetMapping("/apprModify")
	public Map<String, Object> apprUpdateAJAXQueryString(ApprVO apprVO, Model model) {
		model.addAttribute("approvers", apprVO);
		return apprService.apprUpdate(apprVO);
	}

	@PostMapping("/apprModify")
	@ResponseBody // AJAX
	public Map<String, Object> apprUpdateAJAXJSON(@RequestBody ApprVO apprVO) {
		return apprService.apprUpdate(apprVO);
	}
}// 끝
