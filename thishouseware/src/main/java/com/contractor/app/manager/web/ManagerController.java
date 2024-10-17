package com.contractor.app.manager.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import com.contractor.app.manager.service.ManagerService;

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

	private final ManagerService managerService; 
	private final EmployeeService employeeService;
	
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
	    
        String fileName = uploadFile.getOriginalFilename();
        // System.out.println("fileName : " + fileName);
        // 충돌방지를 위해 날짜 폴더와 UUID를 통한 고유식별자를 활용한다.
        //날짜 폴더 생성
        String folderPath = makeFolder();
        //UUID 고유 식별자를통해 중복 방지 
        String uuid = UUID.randomUUID().toString();
        //저장할 파일 이름 중간에 "_"를 이용하여 구분
        String uploadFileName = folderPath +File.separator + uuid + "_" + fileName;
        String saveName = uploadPath + File.separator + uploadFileName;
        //Paths.get() 메서드는 특정 경로의 파일 정보를 가져옵니다.(경로 정의하기)
        Path savePath = Paths.get(saveName);
        System.out.println("path : " + saveName);
        try{
        	//uploadFile에 파일을 업로드 하는 메서드 transferTo(file)
        	uploadFile.transferTo(savePath);  
        } catch (IOException e) {
        	answer = "error2";
            return answer;        
        }
        
        // DB 에 저장하기위한 파일의 경로는 추출한다.
        imageLink = setImagePath(uploadFileName);
        empVO.setImageLink(imageLink);
        System.out.println(empVO);
        
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
	    String uploadFileName = null;
	    if(uploadFile !=null) {
	    	// 기존 파일 삭제하기
	        System.out.println(deleteFile(empVO.getImageLink()));
	    	
	    	// 이미지 파일이 아니면은 처리하지 않는다.(콘텐츠 타입으로 확인)
		    if(uploadFile.getContentType().startsWith("image") == false){
		    	System.err.println("this file is not image type");
		    	answer = "error1";
		    	return answer;
		    }
		    
	    	String fileName = uploadFile.getOriginalFilename();
	        String folderPath = makeFolder();
	        String uuid = UUID.randomUUID().toString();
	        uploadFileName = folderPath +File.separator + uuid + "_" + fileName;
	        String saveName = uploadPath + File.separator + uploadFileName;
	        Path savePath = Paths.get(saveName);
	        System.out.println("path : " + saveName);
	        try{
	        	uploadFile.transferTo(savePath);  
	        } catch (IOException e) {
	        	answer = "error2";
	            return answer;        
	        }
	    }
        
        // DB 에 저장하기위한 파일의 경로는 추출한다.
        if(uploadFileName !=null) {
        	empVO.setImageLink(setImagePath(uploadFileName));
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
	
	private String deleteFile(String beforeImageLink){
		
		String filePath = uploadPath + beforeImageLink;
	    File file = new File(filePath);	
	    if(file.exists()) {
	    	if(file.delete()){
	    		return "파일삭제 성공";
    		}else{
    			return "파일삭제 실패";
    		}
	    }
	    return "파일이 없습니다.";
	}

	/**
	 * 현재 날짜 값을 토대로 폴더를 만들어주는 메서드이다.
	 * @return 폴더 경로 내용
	 */
	private String makeFolder() {
		String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		String folderPath = "img/emp/"; // img를 저장할 것이기에 img를 추가한다.
		// LocalDate를 문자열로 포멧
		folderPath += str.replace("/", File.separator);
		File uploadPathFoler = new File(uploadPath, folderPath);
		
		// File newFile= new File(dir,"파일명");
		if (uploadPathFoler.exists() == false) {
			uploadPathFoler.mkdirs();
			// 만약 uploadPathFolder가 존재하지않는다면 makeDirectory하라는 의미입니다.
			// mkdir(): 디렉토리에 상위 디렉토리가 존재하지 않을경우에는 생성이 불가능한 함수
			// mkdirs(): 디렉토리의 상위 디렉토리가 존재하지 않을 경우에는 상위 디렉토리까지 모두 생성하는 함수
		}
		return folderPath;
	}
	
	/**
	 * 파일이 저장되는 경로를 구해준다.
	 */
	private String setImagePath(String uploadFileName) {
		return uploadFileName.replace(File.separator, "/");
	}


}
