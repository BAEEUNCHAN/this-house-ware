package com.contractor.app.edms.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contractor.app.appr.service.ApprService;
import com.contractor.app.appr.service.ApproverVO;
import com.contractor.app.common.service.Base64ToImgDecodeUtil;
import com.contractor.app.edms.mapper.EdmsMapper;
import com.contractor.app.edms.service.EdmsDocVO;
import com.contractor.app.edms.service.EdmsFormVO;
import com.contractor.app.edms.service.EdmsService;

@Service
public class EdmsServiceImpl implements EdmsService {
	private final EdmsMapper edmsMapper;
	private final ApprService apprService;

	@Autowired
	public EdmsServiceImpl(EdmsMapper edmsMapper, ApprService apprService) {
		this.edmsMapper = edmsMapper;
		this.apprService = apprService;
	}

	// 결재문서 전체조회
	@Override
	public List<EdmsDocVO> edmsDocList() {
		return edmsMapper.selectDocAllList();
	}

	// 결재문서 단건조회
	@Override
	public EdmsDocVO edmsDocInfo(EdmsDocVO edmsDocVO) {
		return edmsMapper.selectEdmsDocInfo(edmsDocVO);
	}

	// 결재문서 등록
	@Override
	public String edmsInsert(EdmsDocVO edmsDocVO) {
		edmsDocVO.setApprovalStatus(EdmsDocVO.STATUS_PENDING); // 결재대기 상태로 설정
		edmsDocVO.setApproverOrder(0); // 기안자(0번째)
		int result = edmsMapper.insertEdmsInfo(edmsDocVO);
		return result == 1 ? edmsDocVO.getEdmsDocNo() : null;
	}

	// 결재문서 임시저장
	@Override
	public String edmsInsertSave(EdmsDocVO edmsDocVO) {
		edmsDocVO.setApprovalStatus(EdmsDocVO.STATUS_TEMPORARY); // 임시저장 상태로 설정
		edmsMapper.insertSaveDoc(edmsDocVO);
		return edmsDocVO.getEdmsDocNo();
	}

	// 결재 승인 처리
	@Override
	public String approveDocument(String edmsDocNo, String approvalStatus) {
	    // 결재 문서 정보 조회
	    EdmsDocVO edmsDoc = edmsMapper.selectEdmsDocInfo(new EdmsDocVO(edmsDocNo));
	    
	    // 현재 결재 순번 확인
	    int currentOrder = edmsDoc.getApproverOrder();

	    // 반려 상태인 경우 승인 불가
	    if (edmsDoc.getApprovalStatus().equals(EdmsDocVO.STATUS_REJECTED)) {
	        return "반려 상태이므로 승인할 수 없습니다.";
	    }

	    // 결재선 정보 조회 (다음 결재자 조회에 사용)
	    List<ApproverVO> approvers = apprService.apprList(edmsDoc.getApprovalLineNo());

	    if (currentOrder < approvers.size() - 1) {
	        // 다음 결재자 설정
	        int nextOrder = currentOrder + 1;
	        String nextApproverId = approvers.get(nextOrder).getApprover();

	        edmsDoc.setApproverOrder(nextOrder);
	        edmsDoc.setApprovalStatus(EdmsDocVO.STATUS_RECEIVED);
	        edmsDoc.setCurrentApproverId(nextApproverId);

	        // 다음 결재자로 넘어갈 때마다 캡처
	        captureDocument(edmsDoc);

	    } else {
	        // 모든 결재가 완료된 경우 처리
	        edmsDoc.setApprovalStatus(EdmsDocVO.STATUS_COMPLETED);
	        edmsDoc.setCurrentApproverId(null); // 결재 완료 시 결재자 없음 설정
	        captureDocument(edmsDoc); // 결재 완료 시에도 캡처 수행
	    }

	    // 승인 상태 업데이트
	    edmsMapper.updateDocumentApprovalStatus(edmsDoc);
	    return "결재가 완료되었습니다.";
	}

	// 결재 반려
	@Override
	public String rejectDocument(String edmsDocNo, String reason) {
		EdmsDocVO edmsDoc = new EdmsDocVO();
		edmsDoc.setEdmsDocNo(edmsDocNo);
		edmsDoc.setApprovalStatus(EdmsDocVO.STATUS_REJECTED); // 반려 상태로 설정
		edmsDoc.setRejectReason(reason); // 반려 사유 설정
		edmsDoc.setApproverOrder(0); // 결재 순서를 기안자로 초기화
		edmsDoc.setCurrentApproverId(edmsDoc.getId()); // 기안자의 ID로 설정

		edmsMapper.updateDocumentRejectionStatus(edmsDoc); // 반려 상태 업데이트
		return "반려되었습니다.";
	}

	// 결재 완료 시 캡처 처리
	private void captureDocument(EdmsDocVO edmsDoc) {
		Map<String, Object> captureInfo = saveImage(edmsDoc.getFileName());
		edmsDoc.setFileName((String) captureInfo.get("fileName"));
		edmsMapper.updateDocumentFileName(edmsDoc); // 파일명 업데이트
	}

	// 이미지 파일 저장
	private Map<String, Object> saveImage(String file) {
		Map<String, Object> object = new HashMap<>();

		try {
			Date createDate = new Date();
			String year = new SimpleDateFormat("yyyy").format(createDate);
			String month = new SimpleDateFormat("MM").format(createDate);
			String day = new SimpleDateFormat("dd").format(createDate);

			String path = "/d:/upload/edms/" + year + "/" + month + "/" + day + "/";
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

	// 결재 상태에 따라 문서 조회
	@Override
	public List<EdmsDocVO> getDocumentsByStatusAndUserId(String approvalStatus, String userId) {
		return edmsMapper.selectDocumentsByStatusAndUserId(approvalStatus, userId);
	}

	// 파일명 업데이트
	@Override
	public void updateFileName(EdmsDocVO edmsDoc) {
		edmsMapper.updateDocumentFileName(edmsDoc);
	}

	// 결재 승인 상태 업데이트
	@Override
	public void updateDocumentApprovalStatus(EdmsDocVO edmsDoc) {
		edmsMapper.updateDocumentApprovalStatus(edmsDoc);
	}

	// 결재 반려 상태 업데이트
	@Override
	public void updateDocumentRejectionStatus(EdmsDocVO edmsDoc) {
		edmsMapper.updateDocumentRejectionStatus(edmsDoc);
	}

	// 결재양식 전체조회
	@Override
	public List<EdmsFormVO> edmsFormList() {
		return edmsMapper.selectFormAllList();
	}

	// 결재양식 단건조회
	@Override
	public EdmsFormVO edmsFormInfo(EdmsFormVO edmsFormVO) {
		return edmsMapper.selectEdmsFormInfo(edmsFormVO);
	}
}