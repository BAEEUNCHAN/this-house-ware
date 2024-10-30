package com.contractor.app.appr.service;

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
 private String approver; // 결재 순서
 private String departmentName; // 부서번호
 private String name; // 직원이름
 private String positionName; // 직급이름
 private Integer approvalOrder; // 결재 순서
 
 
	

}
