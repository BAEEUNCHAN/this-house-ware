package com.contractor.app.appr.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ApprFavoriteVO {

    private Integer favoriteNo;       // 즐겨찾기 번호 Primary Key
    private String favoriteName;      // 즐겨찾기 이름
    private Integer approvalLineNo;   // 결재선 번호
    private String id;                // 아이디
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDt;            // 생성일시   
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updateDt;            // 수정일시
}