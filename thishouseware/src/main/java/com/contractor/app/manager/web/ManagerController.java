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
	
	@GetMapping("manager/addUser")
	public String addUser(Model model ) {
		
		List<DepartmentVO> departments = employeeService.getDepartmentList();
		// 확인용 주석!
		//		departments.forEach(obj ->{
		//			System.out.println(obj);
		//		});
		
		model.addAttribute("departments" , departments);
		
		return "manager/addEmployee";
		
	}
	
	/**
	 * 파일을 올리는 함수이다.
	 */
	@PostMapping("manager/addUser")
	@ResponseBody
	public String uploadFile( 
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			@RequestParam("name") String name,
			@RequestParam("hireDt") String hireDt,
			@RequestParam("phone") String phone,
			@RequestParam("departemntNo") String departemntNo,
			@RequestParam("positionCode") String positionCode,
			@RequestPart MultipartFile uploadFile ) {
		
		String imageLink = null;
		String answer = "failed";

    	// 이미지 파일이 아니면은 처리하지 않는다.(콘텐츠 타입으로 확인)
	    if(uploadFile.getContentType().startsWith("image") == false){
	    	System.err.println("this file is not image type");
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
             e.printStackTrace();             
        }

        EmployeeVO empVO = new EmployeeVO();
        empVO.setEmail(email);
        empVO.setPassword(password);
        empVO.setName(name);
        empVO.setPhone(phone);
        empVO.setDepartmentNo(Integer.parseInt(departemntNo));
        empVO.setPositionCode(positionCode);
        // 날자 타입
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
			empVO.setHireDt(formatter.parse(hireDt));
		} catch (ParseException e) {
			return answer;
		}
        // DB 에 저장하기위한 파일의 경로는 추출한다.
        imageLink = setImagePath(uploadFileName);
        empVO.setImageLink(imageLink);
        System.out.println(empVO);
        
        // 서버입력 조건
        if(employeeService.addUser(empVO) != 1 ) {
        	return answer;
        }
        
	    answer = "success";
	    return answer;
	}

	/**
	 * 현재 날짜 값을 토대로 폴더를 만들어주는 메서드이다.
	 * @return 폴더 경로 내용
	 */
	private String makeFolder() {
		String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		
		// LocalDate를 문자열로 포멧
		String folderPath = str.replace("/", File.separator);
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
