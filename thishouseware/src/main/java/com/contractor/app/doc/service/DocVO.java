package com.contractor.app.doc.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Data
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DocVO { // 문서함
	
 private String docBoxNo; // 문서함 번호 Primary Key
 private String docBoxName; // 문서함 이름
 private String type; // 분류
 private String description; // 설명
 private String id;// 생성자 Foreign Key
 private String upperDocBoxNo; // 상위 문서함 번호
 private String accessRestriction; // 접근제한 설정
 
	
	

}
