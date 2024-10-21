package com.contractor.app.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class FileUpload {

	/**
	 * 파일 업로드가 실패하면 null 이 반환된다.
	 * @param 업로드할 파일 , 상위 파일링크
	 * @return application.properties에 지정한 파일링크를 제외한 파일 링크
	 */
	public static String fileUpload(MultipartFile uploadFile 
			, String highLink,String uploadPath) {
        String fileName = uploadFile.getOriginalFilename();
        //날짜 폴더 생성
        String folderPath = makeFolder(highLink,uploadPath);
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
        	System.out.println(e.getMessage());
            return null;        
        }
        return setImagePath(uploadFileName);
	}

	/**
	 * 파일이 저장되는 경로를 구해준다.
	 */
	public static String setImagePath(String uploadFileName) {
		return uploadFileName.replace(File.separator, "/");
	}

	/**
	 * 이미지 링크를 가지고 삭제 작업을 해준다.
	 * @param 이전에 사용하던 이미지 링크
	 * @param application.properties 에서 지정한 업로드 경로
	 * @return 처리 결과
	 */
	public static String deleteFile(String beforeImageLink, String uploadPath){
		
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
	public static String makeFolder(String highLink, String uploadPath) {
		String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		String folderPath = highLink; // img를 저장할 것이기에 img를 추가한다.
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
}