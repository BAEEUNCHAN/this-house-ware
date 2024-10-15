package com.contractor.app.employee.service;

import java.util.Date;

import lombok.Data;

@Data
public class AuthenticationVO {
	
	String id;
	String authenticationsValue;
	Date authenticationsTime;

}
