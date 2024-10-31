package com.contractor.app.edms.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.contractor.app.appr.service.ApprLineVO;
import com.contractor.app.appr.service.ApprService;
import com.contractor.app.common.service.Base64ToImgDecodeUtil;
import com.contractor.app.edms.service.EdmsDocVO;
import com.contractor.app.edms.service.EdmsFormVO;
import com.contractor.app.edms.service.EdmsService;
import com.contractor.app.util.FileUploadUtil;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/edms")
public class EdmsController {
	// ${} 메모리에서의 변수값을 가져온다.
	// 환경변수 및 application.properties 파일의 변수 값을 Read
	@Value("${file.upload.path}")
	private String uploadPath;

	private final EdmsService edmsService;
	
	private final ApprService apprService;

	@Autowired
	EdmsController(EdmsService edmsService, ApprService apprService) {
		this.edmsService = edmsService;
		this.apprService = apprService;
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
	public String empInsertForm(Model model) {
		List<ApprLineVO> list = apprService.apprLineList();
		model.addAttribute("apprLines", list);
		return "edms/edmsInsert";
	}

	// 결재문서 등록 - 처리
	@PostMapping("/edmsInsert")
	public String edmsInsertProcess(EdmsDocVO edmsDocVO, @RequestParam(required = false) String screenshot,
			@RequestPart(required = false) MultipartFile uploadFile) {
		// 이미지 처리
		Map<String, Object> object = SaveImage(screenshot);

		// 파일 이름 VO에 저장
		edmsDocVO.setFileName((String) object.get("fileName"));

		// 첨부파일 처리
		String imageLink = null;
		String answer = "failed";

		// 첨부파일이 없을경우 다운로드 파일을 보여주지 않는다
		if (uploadFile != null && uploadFile.getSize() > 0) {
			imageLink = FileUploadUtil.fileUpload(uploadFile, "upload/edms/", uploadPath);
			edmsDocVO.setAttatch(imageLink);
		}

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
			Date createDate = new Date();
			String year = (new SimpleDateFormat("yyyy").format(createDate)); // 년도
			String month = (new SimpleDateFormat("MM").format(createDate)); // 월
			String day = (new SimpleDateFormat("dd").format(createDate)); // 일
			// Path를 설정한다.
			String path = "/d:/upload/edms/" + year + "/" + month + "/" + day + "/";
			// 이미지로 저장
			UUID uuid = UUID.randomUUID();
			String image = uuid + ".png";

			if (file != null) {
				Base64ToImgDecodeUtil.decoder(file, path, image);
				object.put("fileName", "edms/" + year + "/" + month + "/" + day + "/" + image);
			} else {
				object.put("fileName", "");
			}
			object.put("path", path);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return object;
	}

	// 결재문서 첨부파일 다운로드
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
	// 결재문서 임시저장
	 @PostMapping("/insertSaveDoc")
	    public ResponseEntity<String> saveTemporary(@RequestBody EdmsDocVO edmsDocVO) {
	        edmsDocVO.setApprovalStatus("임시저장"); // 결재 상태를 임시저장으로 설정
	        edmsService.edmsInseSave(edmsDocVO); // 임시 저장 메서드 호출
	        return ResponseEntity.ok().build();
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

}

// 끝
