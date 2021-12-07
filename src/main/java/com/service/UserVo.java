package com.service;
/**
 * @Class Name  : UserVo.java
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
public class UserVo {

	private String userId;
	private String userPw;
	private String otpKey;
	private String userCode;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getOtpKey() {
		return otpKey;
	}
	public void setOtpKey(String otpKey) {
		this.otpKey = otpKey;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
}

