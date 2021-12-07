package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.service.LoginService;
import com.service.UserVo;
import com.util.GoogleOtp;

/**
 * @Class Name  : GoogleOtpController.java
 * @Description :  
 * @Modification Information
 *
 *    수정일              수정자         수정내용
 *    -------  -------  -------------------
 *    2021. 12. 7.	JHY    	최초생성
 *
 * @author JHY
 * @since 2021. 12. 7.
 * @version 1.0
 * @see
 */
@RestController
public class GoogleOtpController {
	
	/** javax.servlet.http.HttpServletRequest */
	@Autowired
	private HttpServletRequest request;
	
	@Resource(name="LoginService")
	private LoginService loginService;
	
	/**
	 * OTP GENERATE 
	 * @param vo
	 * @param model
	 * @return
	 */
	@RequestMapping("/otpGenerate")
	public JsonObject otpGenerate(
			@ModelAttribute( "vo" ) UserVo vo) {
		
		JsonObject generateJson = loginService.otpKeyGenerate(request, vo);
		
		return generateJson;
	}
	
	/**
	 * OTP CERT 
	 * @param code
	 */
	@RequestMapping( "/otpCert" )
	public JsonObject  otpCert(
			@ModelAttribute( "vo" ) UserVo vo){
		
		JsonObject otpCert = loginService.otpCert(request, vo);
        
        return otpCert;
	}
}

