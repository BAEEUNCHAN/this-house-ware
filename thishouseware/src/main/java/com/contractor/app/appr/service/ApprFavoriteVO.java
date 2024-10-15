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
public class ApprFavoriteVO {

 private Integer favoriteNo; // 즐겨찾기 번호 Primary Key
 private String favoriteName; // 즐겨찾기 이름
 private String favoriteLink; // 즐겨찾기 링크
 
}
