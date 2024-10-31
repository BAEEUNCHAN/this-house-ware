package com.contractor.app.fileroom.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

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

import com.contractor.app.board.service.PagingVO;
import com.contractor.app.common.service.CommonCodeService;
import com.contractor.app.common.service.CommonCodeVO;
import com.contractor.app.fileroom.service.FileRoomService;
import com.contractor.app.fileroom.service.FileRoomsVO;
import com.contractor.app.fileroom.service.FilesVO;
import com.contractor.app.fileroom.service.FolderVO;
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

	// 메인페이지 : URI - fileMainPage / RETURN - file/fileMainPage
	@GetMapping("/fileMainPage")
	public String fileMainPage(Model model) {
		// 자료실 전체조회
		List<FileRoomsVO> list = fileRoomService.selectFilerooms(null);

		model.addAttribute("list", list);

		return "fileroom/fileMainPage";
	}

	// 자료실별 폴더, 파일 전체조회 : URI - folderFileList / RETURN - file/folderFileList
	@GetMapping("/folderFileList")
	public String folderFileList(Model model, FolderVO folderVO, FileRoomsVO fileRoomsVO, PagingVO pagingVO,
			@RequestParam(value = "nowPage", defaultValue = "1", required = false) String nowPage,
			@RequestParam(value = "cntPerPage", defaultValue = "10", required = false) String cntPerPage,
			@RequestParam(required = false) String searchWord,
			@RequestParam(value = "fileRoomsNo", required = false) Integer fileRoomsNo) {

		// 폴더, 파일 총 개수 가져오기
		int folderTotal = fileRoomService.countFolder(fileRoomsNo);
		int fileTotal = fileRoomService.countFile(fileRoomsNo);
		int total = folderTotal + fileTotal;

		// 페이징 및 검색어 설정
		pagingVO = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		if (searchWord != null && !searchWord.isEmpty()) {
			pagingVO.setSearchWord(searchWord.trim());
		}

		// DB 쿼리에서 사용할 start, end값 계산 (총 개수 추가)
		pagingVO.calcStartEnd(Integer.parseInt(nowPage), Integer.parseInt(cntPerPage), total);

		// 자료실, 폴더, 파일 정보 가져오기
		FileRoomsVO fileRoom = fileRoomService.selectFileroom(fileRoomsVO);
		List<FolderVO> folders = fileRoomService.selectFolders(fileRoomsNo);
		List<FilesVO> files = fileRoomService.selectFiles(fileRoomsNo);

		// 페이지에 전달
		model.addAttribute("fileRoom", fileRoom);
		model.addAttribute("paging", pagingVO);
		model.addAttribute("folders", folders);
		model.addAttribute("files", files);

		return "fileroom/folderFileList";
	}

	// 파일 업로드 - 페이지 : URI - fileInsert / RETURN - file/fileInsert
	@GetMapping("/fileInsert")
	public String fileInsertForm(@RequestParam(value = "fileRoomsNo", required = false) Integer fileRoomsNo,
			@RequestParam(value = "folderNo", required = false) Integer folderNo, Model model,
			FileRoomsVO fileRoomsVO, FolderVO folderVO, Authentication authentication) {
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
		model.addAttribute("folders", folders);

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
		;

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

}