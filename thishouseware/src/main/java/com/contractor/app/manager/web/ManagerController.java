package com.contractor.app.manager.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.contractor.app.employee.EmployeeUtil;
import com.contractor.app.employee.service.DepartmentVO;
import com.contractor.app.employee.service.EmployeeService;
import com.contractor.app.employee.service.EmployeeVO;
import com.contractor.app.util.FileUpload;

import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ManagerController {
	
	// ${} 메모리에서의 변수값을 가져온다.
	// 환경변수 및 application.properties 파일의 변수 값을 Read
	@Value("${file.upload.path}")
	private String uploadPath;

	private final EmployeeService employeeService;
	
	@GetMapping("manager/emps")
	public String emps( Model model) {
		List<EmployeeVO> emps = new ArrayList<EmployeeVO>();
		emps = employeeService.getEmployees();
		emps.forEach(obj ->{
			String positionName = EmployeeUtil.getPostionName(obj.getPositionCode());
			obj.setPositionName(positionName);
		});
		model.addAttribute("employees" , emps);
		List<DepartmentVO> departments = employeeService.getDepartmentList();
		model.addAttribute("departments" , departments);
		return "manager/employeeList";
	}
	
	@GetMapping("manager/addEmp")
	public String addEmp(Model model ) {
		
		List<DepartmentVO> departments = employeeService.getDepartmentList();
		// 확인용 주석!
		//		departments.forEach(obj ->{
		//			System.out.println(obj);
		//		});
		
		model.addAttribute("departments" , departments);
		
		return "manager/addEmployee";
		
	}
	@GetMapping("manager/modifyEmp")
	public String modifyEmp(EmployeeVO empVO , Model model) {
		EmployeeVO findVO = employeeService.getEmployee(empVO);
		System.out.println(findVO);
		model.addAttribute("employee",findVO);
		String positionName = EmployeeUtil.getPostionName(findVO.getPositionCode());
		model.addAttribute("employeePositionName",positionName);
		List<DepartmentVO> departments = employeeService.getDepartmentList();
		model.addAttribute("departments" , departments);
		
		return "manager/modifyEmployee";
	}
	/**
	 * 직원을 추가한다.
	 */
	@PostMapping("manager/addEmp")
	@ResponseBody
	public String addEmpProcess( 
			EmployeeVO empVO,
			@RequestPart(required = false) MultipartFile uploadFile ) {
		
		String imageLink = null;
		String answer = "failed";

    	// 이미지 파일이 아니면은 처리하지 않는다.(콘텐츠 타입으로 확인)
	    if(uploadFile.getContentType().startsWith("image") == false){
	    	System.err.println("this file is not image type");
	    	answer = "error1";
	    	return answer;
	    }
        System.out.println(uploadPath);
        // DB 에 저장하기위한 파일의 경로 추출과 파일 저장을 동시에한다.
        imageLink = FileUpload.fileUpload(uploadFile,"img/emp/", uploadPath);
        if(imageLink == null)
        	return "error2";
        empVO.setImageLink(imageLink);
        
        // 서버입력 성공 여부
        try {
        	 employeeService.addEmployee(empVO);
		} catch (Exception e) {
			answer = "error4";
        	return answer;
		}
	    answer = empVO.getId();
	    return answer;
	}

	/**
	 * 직원을 수정한다.
	 */
	@PostMapping("manager/modifyEmp")
	@ResponseBody
	public String modifyEmpProcess( 
			EmployeeVO empVO,
			@RequestPart(required = false) MultipartFile uploadFile ) {

		String answer = null;
	    String imageLink = null;
	    if(uploadFile !=null) {
	    	String beforeImageLink = empVO.getImageLink(); // 기존에 사용하던 이미지 링크
	    	
	    	// 이미지 파일이 아니면은 처리하지 않는다.(콘텐츠 타입으로 확인)
		    if(uploadFile.getContentType().startsWith("image") == false){
		    	System.err.println("this file is not image type");
		    	answer = "error1";
		    	return answer;
		    }
		    
		    // DB 에 저장하기위한 파일의 경로는 추출한다.
		    imageLink = FileUpload.fileUpload(uploadFile,"img/emp/" ,uploadPath);
	        if(imageLink ==null) {
	        	return "error2";
	        }
	        empVO.setImageLink(imageLink);
	        
	        // 기존 파일 삭제하기
	        System.out.println(FileUpload.deleteFile(beforeImageLink,uploadPath));
	    }
        
        // 서버입력 성공 여부
        try {
        	employeeService.modifyEmployee(empVO);
		} catch (Exception e) {
			answer = "error4";
        	return answer;
		}
	    answer = empVO.getId();
	    return answer;
	}

}
