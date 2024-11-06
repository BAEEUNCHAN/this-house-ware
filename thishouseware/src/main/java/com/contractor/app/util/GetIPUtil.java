package com.contractor.app.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import jakarta.servlet.http.HttpServletRequest;

public class GetIPUtil {
	
	public static String getClientIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		//System.out.println("X-Forwarded-For" + ip);
		
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
			//System.out.println("Proxy-Client-IP" + ip);
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
			//System.out.println("WL-Proxy-Client-IP" + ip);
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
			//System.out.println("HTTP_CLIENT_IP" + ip);
		}
		// 자신의 서버로 실행시킨후 테스트하면 여기서 정보가 나옴, 다른대서도 이렇게 나옴
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			//System.out.println("HTTP_X_FORWARDED_FOR" + ip);
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		
		return ip;
	}
	
	public static String getPublicIP() {
		try {
            URL url = new URL("http://checkip.amazonaws.com");
            URLConnection connection = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String ip = in.readLine();
            // System.out.println("External IP Address: " + ip);
            return ip;
        } catch (Exception e) {
        	// System.out.println(e.getMessage());
        	// System.out.println(e);
            return "faild";
        }
	}
}
