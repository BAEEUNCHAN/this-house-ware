package com.contractor.app.doc.service;

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
public class DocJoinVO { // 문서함 

 private int departmentNo; // 부서번호
 private String title; // 문서 제목
 private String id; // 작성자 아이디
 private String name; // 작성자 이름
 @DateTimeFormat(pattern = "yyyy-MM-dd")
 private Date submitDt; //문서 작성 날짜
 private String edmsDocNo; // 문서 번호
 private String approvalStatus; // 결재상태
 @DateTimeFormat(pattern = "yyyy-MM-dd")
 private Date approvalDt;// 결재일시
 private String important; // 중요문서

 // DepartmentVO
 private String departmentName; // 부서 이름
}
