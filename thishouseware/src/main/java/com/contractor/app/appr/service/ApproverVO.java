package com.contractor.app.appr.service;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Data
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ApproverVO {
 private Integer approverNo; // 결재자번호
 private String approvalType; // 결재타입
 private String approver; // 결재자
 private String departmentName; // 부서번호
 private String name; // 직원이름
 private String positionCode; // 직급코드
 private Integer approvalOrder; // 결재 순서
 private Integer approvalLineNo; // 결재라인 번호
 private String favoriteChk; // 즐찾여부
 
//직급 이름
 private String positionName; // 포지션 이름
 
}
