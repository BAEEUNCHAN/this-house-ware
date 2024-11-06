package com.contractor.app.edms.service;

import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class EdmsDocVO {

	List<Integer> approvers; // 결재라인에 있는 결재자들 번호


    // 결재 상태 
    public static final String STATUS_PENDING = "결재대기";
    public static final String STATUS_RECEIVED = "결재수신";
    public static final String STATUS_COMPLETED = "결재완료";
    public static final String STATUS_TEMPORARY = "임시저장"; 
    public static final String STATUS_REJECTED = "반려";

    private String edmsDocNo; // 결재문서 번호 Primary Key / not null
    private String id; // 아이디 Foreign Key / not null
    private String title; // 제목 / not null
    private String content; // 내용
    private String fileName; // 문서파일명
    private String attatch; // 첨부파일
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date submitDt; // 상신일시
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date approvalDt; // 결재일시
    private String approvalStatus; // 결재상태
    private String edmsFormNo; // 결재양식 번호 / not null
    private boolean important; // 중요문서 여부
    private int approverOrder; // 결재 순서
    private String rejectReason; // 반려 사유
    private String currentApproverId; // 결재자 id
    private int approvalLineNo; // 결재선 번호
    private String currentApprovalStatus; // 결재자별 결재상태
    
    private int departmentNo; // 부서번호


    public EdmsDocVO(String edmsDocNo) {
        this.edmsDocNo = edmsDocNo;
    }
}
