# googleOtp (Spring boot)

### OTP 앱
```
안드로이드: https://play.google.com/store/apps/details?id=com.google.android.apps.authenticator2&hl=ko

iOS: https://itunes.apple.com/kr/app/google-authenticator/id388497605?mt=8
```
 
### [절차] (OTP 고유키값은 DB에 저장)
```
1. id,password 맞는지 체크 

2. googleOTP 고유키값 DB에 있는지 없는지 체크  

3. 없을시 Google OTP 앱으로 바코드 등록 유도 or 있을때 otp 6자리 인증

4. 로그인 완료
```

### [6자리 인증 번호 주기 시간]
```
 - window 변수의 역활은 인증 주기 시간인거 같다 (Hash값 테스트 결과)
 - Google OTP 앱에 표기되는 6자리가 몇초 몇 분마다 바뀌냐에 따라 windown 변수값을 올리면됨
 - 검증 시 hash값을 찍어보면 1개의 OTP 번호가 아닌 여러개의 번호중 입력된 번화와 일치하는지 비교확인
 - window변수값을 3으로 줄시 약간의 시간차가 있어서 앞,뒤의 OTP검증 번호도 1~2분 내에서는 인증이
 - Google OTOP의 주기가 30초 인관계로 30초가 지나면 새로운 인증값을 받기위해 window값을 0으로설정
```

### [테스트 URL] 
```
http://127.0.0.1/loginView
```
### [비고]
```
공공기관 프로젝트 중 구글 OTP 인증을 통한 로그인 구현을 요청받아 구글링 하던중 해당 참고 글 참고하여 구현해보았습니다.
git에 올린 project는 DB에 키값을 저장하는 것이아닌 간단한 테스트용으로 session에 저장하여 otp key 값과 6자리 인증 번호 값을 비교하는 형태입니다.
```

### [참고]
``` 
https://lasdri.tistory.com/793
https://www.javacodegeeks.com/2011/12/google-authenticator-using-it-with-your.html#comment-14663
```
