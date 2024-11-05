package com.contractor.app.fileroom.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.contractor.app.common.service.CommonCodeService;
import com.contractor.app.common.service.CommonCodeVO;
import com.contractor.app.employee.service.EmployeeVO;
import com.contractor.app.fileroom.service.FileRoomService;
import com.contractor.app.fileroom.service.FileRoomsVO;
import com.contractor.app.fileroom.service.FilesVO;
import com.contractor.app.fileroom.service.FolderVO;
import com.contractor.app.util.EmpAuthUtil;
import com.contractor.app.util.FileUploadUtil;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

/**
 * 자료실 관리
 */
@RequiredArgsConstructor
@Controller
@RequestMapping("/file")
public class FileRoomController {
	// ${} 메모리에서의 변수값을 가져온다.
	// 환경변수 및 application.properties 파일의 변수 값을 Read
	@Value("${file.upload.path}")
	private String uploadPath;

	private final FileRoomService fileRoomService;
	private final CommonCodeService commonCodeService;
	private final EmpAuthUtil empAuthUtil;

	// 메인페이지 : URI - fileMainPage / RETURN - file/fileMainPage
	@GetMapping("/fileMainPage")
	public String fileMainPage(Model model, FileRoomsVO fileRoomsVO, Authentication authentication,
			@RequestParam(value = "fileRoomsNo", required = false) Integer fileRoomsNo) {
		EmployeeVO employeeVO = empAuthUtil.getAuthEmp(authentication);

		// 자료실 전체조회
		List<FileRoomsVO> fileRooms = fileRoomService.selectFilerooms(fileRoomsVO);

		// 자료실별 폴더와 파일을 담을 Map 생성
		Map<Integer, List<FolderVO>> folderMap = new HashMap<>();
		Map<Integer, List<FilesVO>> fileMap = new HashMap<>();

		// fileRoomsNo가 null이 아니면 해당 자료실의 폴더와 파일만 조회
		if (fileRoomsNo != null) {
			System.out.println("조회할 자료실 번호: " + fileRoomsNo);

			// 선택한 자료실의 폴더 및 파일 목록 조회
			List<FolderVO> folders = fileRoomService.selectFolders(fileRoomsNo);
			List<FilesVO> files = fileRoomService.selectFiles(fileRoomsNo);

			// 특정 자료실 번호에 대한 폴더와 파일을 Map에 저장
			folderMap.put(fileRoomsNo, folders);
			fileMap.put(fileRoomsNo, files);
		} else {
			// 전체 자료실의 폴더와 파일 목록 조회
			for (FileRoomsVO fileRoom : fileRooms) {
				Integer currentFileRoomsNo = fileRoom.getFileRoomsNo();

				// 자료실별 폴더 및 파일 조회 후 Map에 저장
				List<FolderVO> folders = fileRoomService.selectFolders(currentFileRoomsNo);
				List<FilesVO> files = fileRoomService.selectFiles(currentFileRoomsNo);

				folderMap.put(currentFileRoomsNo, folders);
				fileMap.put(currentFileRoomsNo, files);
			}
		}

		// 모델에 추가하여 뷰로 전달
		model.addAttribute("fileRooms", fileRooms);
		model.addAttribute("folderMap", folderMap);
		model.addAttribute("fileMap", fileMap);
		model.addAttribute("fileRoomsNo", fileRoomsNo);
		model.addAttribute("employeeVO", employeeVO);

		return "fileroom/fileMainPage";
	}

	// 폴더 추가 - 처리 : URI - folderInsert / PARAMETER - FolderVO(QueryString)
	@PostMapping("/folderFileList")
	public String folderFileList(FolderVO folderVO) { // <form/> 활용한 submit
		int fileRoomsNo = fileRoomService.insertFolder(folderVO);
		return "redirect:folderFileList?fileRoomsNo=" + fileRoomsNo;
	}

	// 자료실별 폴더, 파일 전체조회 : URI - folderFileList / RETURN - file/folderFileList
	@GetMapping("/folderFileList")
	public String folderFileList(Model model, Authentication authentication, FolderVO folderVO, FileRoomsVO fileRoomsVO,
			@RequestParam(value = "fileRoomsNo", required = false) Integer fileRoomsNo) {
		EmployeeVO employeeVO = empAuthUtil.getAuthEmp(authentication);

		// 자료실, 폴더, 파일 정보 가져오기
		FileRoomsVO fileRoom = fileRoomService.selectFileroom(fileRoomsVO);
		List<FolderVO> folders = fileRoomService.selectFolders(fileRoomsNo);
		List<FilesVO> files = fileRoomService.selectFiles(fileRoomsNo);

		// 페이지에 전달
		model.addAttribute("fileRoom", fileRoom);
		model.addAttribute("folders", folders);
		model.addAttribute("files", files);
		model.addAttribute("employeeVO", employeeVO);

		return "fileroom/folderFileList";
	}

	// 파일 업로드 - 페이지 : URI - fileInsert / RETURN - file/fileInsert
	@GetMapping("/fileInsert")
	public String fileInsertForm(@RequestParam(value = "fileRoomsNo", required = false) Integer fileRoomsNo,
			@RequestParam(value = "folderNo", required = false) Integer folderNo, Model model, FileRoomsVO fileRoomsVO,
			FolderVO folderVO, Authentication authentication) {
		// 자료실 유형 조회
		List<CommonCodeVO> fileRoomsType = commonCodeService.selectCommonCode("0R");

		// 자료실 전체조회 - fileRoomsName
		List<FileRoomsVO> fileRooms = fileRoomService.selectFilerooms(null);

		// 자료실별 폴더 전체조회 - folderName, fileRoomsNo
		List<FolderVO> folders = fileRoomService.selectFolders(fileRoomsNo);

		// 페이지에 전달
		model.addAttribute("selectedfileRoomsNo", fileRoomsNo);
		model.addAttribute("selectedfolderNo", folderNo);
		model.addAttribute("fileRoomsType", fileRoomsType);
		model.addAttribute("fileRooms", fileRooms);
		// model.addAttribute("folders", folders);

		return "fileroom/fileInsert";
	}

	// 파일 업로드 - 처리 : URI - fileInsert / PARAMETER - FilesVO(QueryString)
	// RETURN - 자료실별 폴더,파일 전체 조회 다시 호출
	@PostMapping("/fileInsert")
	public String fileInsertProcess(FilesVO filesVO, @RequestPart(required = false) MultipartFile uploadFile) {
		// 첨부파일 처리
		String link = null;

		// 첨부파일이 없을경우 다운로드 파일을 보여주지 않는다
		if (uploadFile != null && uploadFile.getSize() > 0) {
			link = FileUploadUtil.fileUpload(uploadFile, "upload/fileroom/", uploadPath);
			filesVO.setAttatch(link); // 파일 링크를 FilesVO에 설정
		}

		int fileRoomsNo = fileRoomService.insertFile(filesVO);
		String url = null;

		if (fileRoomsNo > 0) {
			// 정상적으로 등록된 경우
			url = "redirect:folderFileList?fileRoomsNo=" + fileRoomsNo;
		} else {
			// 등록되지 않은 경우
			url = "redirect:/errorPage"; // 에러 처리 로직 추가 가능
		}
		return url;
	}

	// 파일 다운로드 처리
	@GetMapping("/fileDownload")
	public void fileDownload(@RequestParam("fileLink") String file, HttpServletResponse response) throws IOException {
		int lastIndex = file.lastIndexOf("/");

		// 파일 이름 추출
		String fileName = (lastIndex != -1) ? file.substring(lastIndex + 1) : file;

		// File 객체 생성
		File f = new File(uploadPath, file);

		// 파일 이름을 UTF-8로 인코딩 (특수문자, 공백 처리)
		String encodedFileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");

		// file 다운로드 설정
		response.setContentType("application/octet-stream");
		response.setContentLength((int) f.length());
		response.setHeader("Content-disposition", "attachment; filename*=UTF-8''" + encodedFileName);

		// response 객체를 통해서 서버로부터 파일 다운로드
		OutputStream os = response.getOutputStream();
		// 파일 입력 객체 생성
		FileInputStream fis = new FileInputStream(f);
		FileCopyUtils.copy(fis, os);
		fis.close();
		os.close();
	}

	@GetMapping("/getFoldersByFileRoomsNo")
	@ResponseBody
	public List<FolderVO> getFoldersByFileRoomsNo(@RequestParam Integer fileRoomsNo) {
		List<FolderVO> folders = fileRoomService.selectFolders(fileRoomsNo);
		return folders;
	}

	// 폴더 삭제 - 처리 : URI - folderDelete / PARAMETER - Integer
	@GetMapping("/folderDelete") // QueryString : @RequestParam
	public String folderDelete(@RequestParam Integer folderNo, @RequestParam Integer fileRoomsNo) {
		fileRoomService.deleteFolder(folderNo);
		return "redirect:folderFileList?fileRoomsNo=" + fileRoomsNo;
	}

	// 파일 삭제 - 처리 : URI - fileDelete / PARAMETER - Integer
	@GetMapping("/fileDelete") // QueryString : @RequestParam
	public String fileDelete(@RequestParam Integer fileNo, @RequestParam Integer fileRoomsNo) {
		fileRoomService.deleteFile(fileNo);
		return "redirect:folderFileList?fileRoomsNo=" + fileRoomsNo;
	}

}