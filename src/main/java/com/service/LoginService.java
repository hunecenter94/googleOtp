package com.service;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.JsonObject;

/**
 * @Class Name  : LoginService.java
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
public interface LoginService {

	/** OTP_KEY_HAVE */
	public static final String OTP_KEY_HAVE = "OTP_KEY_HAVE";
	
	/** OTP_KEY_GENERATE */
	public static final String OTP_KEY_GENERATE = "OTP_KEY_GENERATE";
	
	/** NOT_MATCHED_PWD */
	public static final String NOT_MATCHED_PWD = "NOT_MATCHED_PWD";
	
	/** LOGIN_SUCCESS */
	public static final String LOGIN_SUCCESS = "SUCCESS";
	
	/** LOGIN_FAIL */
	public static final String LOGIN_FAIL = "FAIL";
	
	/**
	 * OTP KEY Generate
	 * @param request
	 * @param vo
	 * @return
	 */
	public abstract JsonObject otpKeyGenerate(HttpServletRequest request, UserVo vo);
	
	/**
	 * OTP Cert
	 * @param request
	 * @param vo
	 * @return
	 */
	public abstract JsonObject otpCert(HttpServletRequest request, UserVo vo);
}

