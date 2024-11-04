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
import org.springframework.security.core.Authentication;
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
import com.contractor.app.employee.service.EmployeeVO;
import com.contractor.app.util.EmpAuthUtil;
import com.contractor.app.util.FileUploadUtil;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/edms")
public class EdmsController {
	@Value("${file.upload.path}")
	private String uploadPath;

	private final EdmsService edmsService;
	private final ApprService apprService;
	private final EmpAuthUtil empAuthUtil;

	@Autowired
	public EdmsController(EdmsService edmsService, ApprService apprService, EmpAuthUtil empAuthUtil) {
		this.edmsService = edmsService;
		this.apprService = apprService;
		this.empAuthUtil = empAuthUtil; // empAuthUtil 주입
	}

	// 결재문서 전체조회
	@GetMapping("/edmsDocList")
	public void edmsDocList(Model model) {
		List<EdmsDocVO> list = edmsService.edmsDocList();
		model.addAttribute("edmsDocs", list);
	}

	// 결재문서 단건조회
	@GetMapping("/edmsDocInfo")
	public void edmsDocInfo(EdmsDocVO edmsDocVO, Model model) {
		EdmsDocVO findVO = edmsService.edmsDocInfo(edmsDocVO);
		model.addAttribute("edmsDocs", findVO);
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
		edmsDocVO.setApprovalStatus(EdmsDocVO.STATUS_PENDING); // 결재대기 상태로 설정

		// 이미지 처리
		Map<String, Object> object = saveImage(screenshot);
		edmsDocVO.setFileName((String) object.get("fileName"));

		// 첨부파일 처리
		if (uploadFile != null && uploadFile.getSize() > 0) {
			String imageLink = FileUploadUtil.fileUpload(uploadFile, "upload/edms/", uploadPath);
			edmsDocVO.setAttatch(imageLink);
		}

		String edn = edmsService.edmsInsert(edmsDocVO);
		return edn != null ? "redirect:edmsDocInfo?edmsDocNo=" + edn : "redirect:edmsDocList";
	}

	// 결재 승인 처리
	@PostMapping("/approve")
	public ResponseEntity<String> approveDocument(@RequestParam String edmsDocNo) {
		EdmsDocVO edmsDoc = edmsService.edmsDocInfo(new EdmsDocVO(edmsDocNo));
		int currentOrder = edmsDoc.getApprovalOrder();
		List<Integer> approvers = edmsDoc.getApprovers();

		// 다음 결재자 조회
		String nextApproverId = edmsService.findNextApprover(edmsDocNo, currentOrder);
		if (nextApproverId != null) {
			// 다음 결재자가 있는 경우
			edmsDoc.setApprovalOrder(currentOrder + 1);
			edmsDoc.setApprovalStatus(EdmsDocVO.STATUS_RECEIVED);
			edmsDoc.setCurrentApproverId(nextApproverId); // 다음 결재자로 설정
		} else {
			// 모든 결재가 완료된 경우
			edmsDoc.setApprovalStatus(EdmsDocVO.STATUS_COMPLETED);
			edmsDoc.setCurrentApproverId(null); // 결재 완료 시 결재자 없음
			captureDocument(edmsDoc); // 결재 완료 시 캡처 수행
		}

		edmsService.updateFileName(edmsDoc);
		return ResponseEntity.ok("결재가 완료되었습니다.");
	}

	// 결재 반려 처리
	@PostMapping("/reject")
	public ResponseEntity<String> rejectDocument(@RequestParam String edmsDocNo, @RequestParam String reason) {
		EdmsDocVO edmsDoc = new EdmsDocVO();
		edmsDoc.setEdmsDocNo(edmsDocNo);
		edmsDoc.setApprovalStatus(EdmsDocVO.STATUS_REJECTED); // 반려 상태로 설정
		edmsDoc.setRejectReason(reason); // 반려 사유 설정
		edmsDoc.setApprovalOrder(0); // 결재 순서를 기안자로 초기화
		edmsDoc.setCurrentApproverId(edmsDoc.getId()); // 기안자의 ID로 설정

		edmsService.updateFileName(edmsDoc);
		return ResponseEntity.ok("반려되었습니다.");
	}

	// 결재 완료 시 작성된 문서 캡처
	private void captureDocument(EdmsDocVO edmsDoc) {
		Map<String, Object> captureInfo = saveImage(edmsDoc.getFileName());
		edmsDoc.setFileName((String) captureInfo.get("fileName"));
		edmsService.updateFileName(edmsDoc);
	}

	// 결재문서 임시저장
	@PostMapping("/insertSaveDoc")
	public ResponseEntity<String> saveTemporary(@RequestBody EdmsDocVO edmsDocVO) {
		edmsDocVO.setApprovalStatus(EdmsDocVO.STATUS_TEMPORARY); // 결재 상태를 임시저장으로 설정
		edmsService.edmsInseSave(edmsDocVO); // 임시 저장 메서드 호출
		return ResponseEntity.ok().build();
	}

	// 결재문서 등록 시 작성된 폼(html)을 이미지화
	public Map<String, Object> saveImage(String file) {
		Map<String, Object> object = new HashMap<>();

		try {
			if (file == null) {
				throw new IllegalArgumentException("No image data provided.");
			}

			Date createDate = new Date();
			String year = new SimpleDateFormat("yyyy").format(createDate);
			String month = new SimpleDateFormat("MM").format(createDate);
			String day = new SimpleDateFormat("dd").format(createDate);

			String path = "/d:/upload/edms/" + year + "/" + month + "/" + day + "/";
			UUID uuid = UUID.randomUUID();
			String image = uuid + ".png";

			Base64ToImgDecodeUtil.decoder(file, path, image);
			object.put("fileName", "edms/" + year + "/" + month + "/" + day + "/" + image);
			object.put("path", path);

		} catch (Exception e) {
			e.printStackTrace();
			object.put("fileName", ""); // 기본값으로 빈 문자열 설정
		}

		return object;
	}

	// 파일 다운로드 처리
	@GetMapping("/fileDownload")
	public void fileDownload(@RequestParam("fileLink") String file, HttpServletResponse response) throws IOException {
		int lastIndex = file.lastIndexOf("/");
		String fileName = (lastIndex != -1) ? file.substring(lastIndex + 1) : file;
		File f = new File(uploadPath, file);

		String encodedFileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");

		response.setContentType("application/octet-stream");
		response.setContentLength((int) f.length());
		response.setHeader("Content-disposition", "attachment; filename*=UTF-8''" + encodedFileName);

		OutputStream os = response.getOutputStream();
		FileInputStream fis = new FileInputStream(f);
		FileCopyUtils.copy(fis, os);
		fis.close();
		os.close();
	}

	// 결재문서별 조회
	@GetMapping("/docApprovalStatusList")
	public String getDocumentsByStatus(@RequestParam String approvalStatus, Model model,
			Authentication authentication) {
		EmployeeVO employeeVO = empAuthUtil.getAuthEmp(authentication);
		String userId = employeeVO.getId(); // 현재 로그인된 사용자 ID

		List<EdmsDocVO> documents = edmsService.getDocumentsByStatusAndUserId(approvalStatus, userId);

		model.addAttribute("documents", documents);
		model.addAttribute("approvalStatus", approvalStatus);
		return "edms/docApprovalStatusList";
	}

	// 결재양식 전체조회
	@GetMapping("/edmsFormList")
	public void edmsFormList(Model model) {
		List<EdmsFormVO> list = edmsService.edmsFormList();
		model.addAttribute("edmsForms", list);
	}

	// 결재양식 단건조회
	@GetMapping("/edmsFormInfo")
	public void empInfo(EdmsFormVO edmsFormVO, Model model) {
		EdmsFormVO findVO = edmsService.edmsFormInfo(edmsFormVO);
		model.addAttribute("edmsForms", findVO);
	}
}