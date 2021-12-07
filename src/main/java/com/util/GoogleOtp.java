package com.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base32;

/**
 * @Class Name  : GoogleOtp.java
 * @Description : GoogleOtp util
 * @Modification Information
 *
 *    수정일              수정자         수정내용
 *    -------  -------  -------------------
 *    2019.12.09.	JHY    	최초생성
 *
 * @since 2019.12.09.
 * @version 1.0
 * @see
 */
public class GoogleOtp {
	/**
	 * OTPKEY 생성 및 QR코드 생성	
	 * @param userName
	 * @param hostName
	 * @return
	 */
	public static HashMap<String, String> generate(String userName, String hostName) {
		HashMap<String, String> map = new HashMap<String, String>();
		byte[] buffer = new byte[5 + 5 * 5];
		new Random().nextBytes(buffer);
		Base32 codec = new Base32();
		byte[] secretKey = Arrays.copyOf(buffer, 10);
		byte[] bEncodedKey = codec.encode(secretKey);

		String encodedKey = new String(bEncodedKey);
		String url = getQRBarcodeURL(userName, hostName, encodedKey);
		// Google OTP (userName, hostName을 받는 이유 : 앱에 저장될 명칭) userName@hostName 으로 저장됨 (양식은 상관이 없음)
		// key를 입력하거나 생성된 QR코드를 바코드 스캔하여 등록

		map.put("encodedKey", encodedKey);
		map.put("url", url);
		
		return map;
	}

	/**
	 * OTP번호 check
	 * @param userCode Google OTP 앱에 표시되는 6자리 숫자
	 * @param otpkey Google OTP PK
	 * @return
	 */
	public static boolean checkCode(String userCode, String otpkey) {
		System.out.println("userCode : "+userCode);
		System.out.println("otpkey : "+otpkey);
		boolean result = false;
		if(isNumber(userCode)){
			long otpnum = Integer.parseInt(userCode); // Google OTP 앱에 표시되는 6자리 숫자
			long wave = new Date().getTime() / 30000; // Google OTP의 주기는 30초
		
			try {
				Base32 codec = new Base32();
				byte[] decodedKey = codec.decode(otpkey);
				int window = 3;
				for (int i = -window; i <= window; ++i) {
					long hash = verify_code(decodedKey, wave + i);
					if (hash == otpnum) result = true;
				}
			} catch (InvalidKeyException | NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}else{
			result = false;
		}
	
		return result;
	}
	
	/**
	 * OTP_KEY 검증
	 * @param key
	 * @param t
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 */
	private static int verify_code(byte[] key, long t) throws NoSuchAlgorithmException, InvalidKeyException {
		byte[] data = new byte[8];
		long value = t;
		for (int i = 8; i-- > 0; value >>>= 8) {
			data[i] = (byte) value;
		}

		SecretKeySpec signKey = new SecretKeySpec(key, "HmacSHA1");
		Mac mac = Mac.getInstance("HmacSHA1");
		mac.init(signKey);
		byte[] hash = mac.doFinal(data);

		int offset = hash[20 - 1] & 0xF;

		// We're using a long because Java hasn't got unsigned int.
		long truncatedHash = 0;
		for (int i = 0; i < 4; ++i) {
			truncatedHash <<= 8;
			// We are dealing with signed bytes:
			// we just keep the first byte.
			truncatedHash |= (hash[offset + i] & 0xFF);
		}

		truncatedHash &= 0x7FFFFFFF;
		truncatedHash %= 1000000;

		return (int) truncatedHash;
	}

	/**
	 * QR코드 주소 생성
	 * @param user
	 * @param host
	 * @param secret
	 * @return
	 */
	public static String getQRBarcodeURL(String user, String host, String secret) {
		// QR코드 주소 생성
		String format2 = "http://chart.apis.google.com/chart?cht=qr&chs=200x200&chl=otpauth://totp/%s@%s%%3Fsecret%%3D%s&chld=H|0";
		return String.format(format2, user, host, secret);
	}
	
	/**
	 * 숫자타입 여부를 판단한다.
	 * @param str 판단할 스트링
	 * @return 숫자타입 : true
	 */
	public static boolean isNumber(String str){
	    if(str.equals("")) return false;
        char[] ch = str.toCharArray();
        int len = ch.length;	    
	    for(int i = 0 ; i < len ; i++){
	        if(ch[i] < 48 || ch[i] > 59){
	            return false;
	        }
	    }
	    return true;
	}	
	
	 /**
     * NULL 문자를 대체한다.
     * @param obj 원본 객체
     * @return obj가 NULL일 경우 "", 아닐경우 obj
     */
    public final static String replaceNull(Object obj){
    	String str = "";
        if (obj != null) {
        	str = obj.toString();
        }
        return str;
    }
}
