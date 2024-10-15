package com.contractor.app.employee.service;

import java.awt.Image;
import java.util.Date;

import lombok.Data;

@Data
public class EmployeeVO {
	String id;
	String password;
	String name;
	Date hireDt;
	String phone;
	String email;
	String imageLink;
	Date resignDt;
	int departemntNo;
	String positionCode;
	Image dSignature;
}