package com.contractor.app.fileroom.web;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.contractor.app.board.service.PagingVO;
import com.contractor.app.common.service.CommonCodeService;
import com.contractor.app.common.service.CommonCodeVO;
import com.contractor.app.fileroom.service.FileRoomService;
import com.contractor.app.fileroom.service.FileRoomsVO;
import com.contractor.app.fileroom.service.FolderFileVO;
import com.contractor.app.fileroom.service.FolderVO;

import lombok.RequiredArgsConstructor;

/**
 * 자료실 관리
 */
@RequiredArgsConstructor
@Controller
@RequestMapping("/file")
public class FileRoomController {
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
	public String folderFileList(Model model, FolderFileVO folderFileVO, FileRoomsVO fileRoomsVO, PagingVO pagingVO,
			@RequestParam(value = "nowPage", defaultValue = "1", required = false) String nowPage,
			@RequestParam(value = "cntPerPage", defaultValue = "10", required = false) String cntPerPage,
			@RequestParam(required = false) String searchWord) {

		// 폴더/파일 총 개수 가져오기
		int total = fileRoomService.countFolerFile(folderFileVO);

		// 페이징 및 검색어 설정
		pagingVO = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		if (searchWord != null && !searchWord.isEmpty()) {
			pagingVO.setSearchWord(searchWord.trim());
		}

		// DB 쿼리에서 사용할 start, end값 계산 (총 개수 추가)
		pagingVO.calcStartEnd(Integer.parseInt(nowPage), Integer.parseInt(cntPerPage), total);

		// 자료실, 폴더/파일 정보 가져오기
		FileRoomsVO fileRoom = fileRoomService.selectFileroom(fileRoomsVO);
		List<FolderFileVO> list = fileRoomService.selectFolderFile(pagingVO, folderFileVO);

		// 페이지에 전달
		model.addAttribute("paging", pagingVO);
		model.addAttribute("fileRoom", fileRoom);
		model.addAttribute("list", list);

		return "fileroom/folderFileList";
	}

	// 파일 업로드 - 페이지 : URI - fileInsert / RETURN - file/fileInsert
	@GetMapping("/fileInsert")
	public String fileInsertForm(@RequestParam(value = "fileRoomsNo", required = false) Integer fileRoomsNo,
			Model model, FileRoomsVO fileRoomsVO, FolderVO folderVO, Authentication authentication) {
		// 자료실 유형 조회
		List<CommonCodeVO> fileRoomsType = commonCodeService.selectCommonCode("0R");

		// 자료실 전체조회 - fileRoomsName
		List<FileRoomsVO> fileRooms = fileRoomService.selectFilerooms(null);

		// 자료실별 폴더 전체조회 - folderName, fileRoomsNo
		List<FolderVO> folders = fileRoomService.selectFolders(folderVO);

		// 페이지에 전달
		model.addAttribute("selectedfileRoomsNo", fileRoomsNo);
		model.addAttribute("fileRoomsType", fileRoomsType);
		model.addAttribute("fileRooms", fileRooms);
		model.addAttribute("folders", folders);

		return "fileroom/fileInsert";
		// classpath:/templates/board/postInsert.html => 실제 경로
	}
}
