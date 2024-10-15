package com.contractor.app.employee.service;

import java.awt.Image;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class EmployeeVO {
	String id;
	String password;
	String name;
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	Date hireDt;
	String phone;
	String email;
	String imageLink;
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	Date resignDt;
	int departmentNo;
	String positionCode;
	Image dSignature;
}
