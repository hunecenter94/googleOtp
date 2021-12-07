package com.service.impl;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.service.LoginService;
import com.service.UserVo;
import com.util.GoogleOtp;

/**
 * @Class Name  : LoginServiceImpl.java
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
@Service("LoginService")
public class LoginServiceImpl implements LoginService {

	/**
	 * OTP KEY Generate
	 * @param request
	 * @param vo
	 * @return
	 */
	@Override
	public JsonObject otpKeyGenerate(HttpServletRequest request, UserVo vo) {
		HttpSession session = request.getSession();
		//구글 otp key
		String gotpKey = (String)GoogleOtp.replaceNull(session.getAttribute("gotpKey"));
		String qrCdUrl = (String)GoogleOtp.replaceNull(session.getAttribute("qrCdUrl"));
		HashMap<String, String> otpGenerate = null;
		
		String serverUrl = request.getScheme()+"://"+request.getServerName();
		
		//json 객체 반환
		JsonObject generateJson = new JsonObject();
		
		if(!"".equals(gotpKey) && !"".equals(qrCdUrl)) {
			generateJson.addProperty("result", OTP_KEY_HAVE);
			generateJson.addProperty("qrCdUrl", qrCdUrl);
			generateJson.addProperty("gotpKey", gotpKey);
			
			System.out.println(gotpKey);
			System.out.println(qrCdUrl);
		}else {
			//OTP KEY 생성 및 QR CODE URL 생성
			otpGenerate = GoogleOtp.generate(vo.getUserId(), serverUrl);
			
			gotpKey = otpGenerate.get("encodedKey");
			
			//session 체크를 위해 setAttribute
			session.setAttribute("qrCdUrl", otpGenerate.get("url"));
			session.setAttribute("gotpKey", gotpKey);
			
			generateJson.addProperty("result", OTP_KEY_GENERATE);
			generateJson.addProperty("qrCdUrl", otpGenerate.get("url"));
			generateJson.addProperty("gotpKey", gotpKey);
			
		}
		return generateJson;
	}

	/**
	 * OTP CERT
	 * @param request
	 * @param vo
	 * @return
	 */
	@Override
	public JsonObject otpCert(HttpServletRequest request, UserVo vo) {
		HttpSession session = request.getSession();
		//구글 otp key
		String gotpKey = (String)GoogleOtp.replaceNull(session.getAttribute("gotpKey"));
		
		//json 객체 반환
		JsonObject certJson = new JsonObject();
		
		boolean certChk = false;
		
		if(!"".equals(GoogleOtp.replaceNull(gotpKey)) && !"".equals(GoogleOtp.replaceNull(vo.getUserCode()))) {
			certChk = GoogleOtp.checkCode(vo.getUserCode(), gotpKey);
			System.out.println("certChk : "+ certChk);
			if(certChk) {
				certJson.addProperty("result", LOGIN_SUCCESS);
			}else {
				certJson.addProperty("result", LOGIN_FAIL);
			}
		}
		return certJson;
	}
	
	

}

