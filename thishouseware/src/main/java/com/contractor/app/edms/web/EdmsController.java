package com.contractor.app.edms.web;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.contractor.app.common.service.Base64ToImgDecodeUtil;
import com.contractor.app.edms.service.EdmsDocVO;
import com.contractor.app.edms.service.EdmsFormVO;
import com.contractor.app.edms.service.EdmsService;

@Controller
@RequestMapping("/edms")
public class EdmsController {

	private final EdmsService edmsService;

	@Autowired
	EdmsController(EdmsService edmsService) {
		this.edmsService = edmsService;
	}

	// 결재문서 전체조회
	@GetMapping("/edmsDocList")
	public void edmsDocList(Model model) {
		List<EdmsDocVO> list = edmsService.edmsDocList();
		model.addAttribute("edmsDocs", list);
		// return "edms/edmsDocList";
	}

	// 결재문서 단건조회
	@GetMapping("/edmsDocInfo")
	public void edmsDocInfo(EdmsDocVO edmsDocVO, Model model) {
		EdmsDocVO findVO = edmsService.edmsDocInfo(edmsDocVO);
		model.addAttribute("edmsDocs", findVO);
		// return "edms/edmsDocinfo";
	}

	// 결재문서 등록 - 페이지
	@GetMapping("/edmsInsert")
	public String empInsertForm() {
		return "edms/edmsInsert";
	}

	// 결재문서 등록 - 처리
	@PostMapping("/edmsInsert")
	public String edmsInsertProcess(EdmsDocVO edmsDocVO, @RequestParam String screenshot) {
		// 이미지 처리
		Map<String, Object> object = SaveImage(screenshot);
		
		//파일 이름 VO에 저장
		edmsDocVO.setFileName((String) object.get("fileName"));
		//결재문서에 삽입 처리해서 결과 받아오기 
		String edn = edmsService.edmsInsert(edmsDocVO);

		String url = null;

		if (edn == null) {
			// 정상적으로 등록된 경우
			url = "redirect:edmsDocInfo?edmsDocNo=" + edn;
			// "redirect:" 가 가능한 경우 GetMapping
		} else {
			// 등록되지 않은 경우
			url = "redirect:edmsDocList";
		}
		return url;
	}

	// 결재문서 등록시 작성된 폼(html)을 이미지화
	public Map<String, Object> SaveImage(String file) {

		Map<String, Object> object = new HashMap<String, Object>();

		try {
			String result = "success";
			String message = "completed images save!";

			// 현재 날짜를 yyyy/MM/dd 형식으로 가져오기
			String datePath = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
			String path = "d:/upload/edms/" + datePath.replace("/", File.separator) + "/"; // 경로를 d:/upload/edms/로 수정

			// 이미지 저장을 위한 폴더 생성
			File uploadPathFolder = new File(path);
			if (!uploadPathFolder.exists()) {
				uploadPathFolder.mkdirs(); // 폴더가 없으면 생성
			}

			// 이미지로 저장할 파일명 생성
			UUID uuid = UUID.randomUUID(); // 중복 방지를 위한 UUID
			String my_screenshot_image = uuid + ".png";

			if (file != null) {
				// 이미지 디코딩 및 저장
				Base64ToImgDecodeUtil.decoder(file, path, my_screenshot_image);
			} else {
				result = "failed";
				message = "not found image!!!";
			}

			// 결과 값을 반환
			object.put("path", path); // 경로 저장 
			object.put("fileName", my_screenshot_image); // 파일 이름 저장
		} catch (Exception e) {
			e.printStackTrace();
		}

		return object;
	}

	// 결재양식 전체조회
	@GetMapping("/edmsFormList")
	public void edmsFormList(Model model) {
		List<EdmsFormVO> list = edmsService.edmsFormList();
		model.addAttribute("edmsForms", list);
		// return "edms/edmsFormList";
	}

	// 결재양식 단건조회
	@GetMapping("/edmsFormInfo")
	public void empInfo(EdmsFormVO edmsFormVO, Model model) {
		EdmsFormVO findVO = edmsService.edmsFormInfo(edmsFormVO);
		model.addAttribute("edmsForms", findVO);
		// return "edms/edmsFormInfo";
	}

} // 끝
