package com.contractor.app.fileroom.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.contractor.app.common.service.CommonCodeService;
import com.contractor.app.fileroom.service.FileRoomService;

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
	public String fileMainPage() {

		return "fileroom/fileMainPage";
	}
}
