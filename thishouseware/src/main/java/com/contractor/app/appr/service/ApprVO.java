package com.contractor.app.appr.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Data
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ApprVO {
	private Integer approverNo; // 결재자번호 Primary Key / not null
	private String approver; // 결재자 / not null
	private String approverName; // 결재자 이름
	private String approvalType; // 결재타입
	private Integer approvalOrder; // 결재 순서 / not null
	private String approvalStatus; // 결재상태
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date approvalDt; // 결재일시
	private String rejectedReason; // 반려사유
	private String edmsDocNo; // 결재문서 번호 Foreign Key / not null
	private Integer approvalLineNo; // 결재선 번호 Foreign Key / not null
}
