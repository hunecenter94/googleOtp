# googleOtp
[참고]
- https://lasdri.tistory.com/793
- https://www.javacodegeeks.com/2011/12/google-authenticator-using-it-with-your.html#comment-14663

 

OTP 앱

안드로이드: https://play.google.com/store/apps/details?id=com.google.android.apps.authenticator2&hl=ko

iOS: https://itunes.apple.com/kr/app/google-authenticator/id388497605?mt=8

 

프로젝트 중 구글 OTP 인증을 통한 로그인 구현을 요청받아 구글링 하던중 API문서나 따로 정보를 찾은게 없어서...

해당 참고 글 참고하여 GoogleOTP로직을 util로 쓰고 로그인 serviceImpl에서 otp인증을 하도록 수정해서 구현

 

-절차 ※OTP 고유키값은 DB에 저장 

1. id,password 맞는지 체크 

2. googleOTP 고유키값 DB에 있는지 없는지 체크  

3. 없을시 Google OTP 앱으로 바코드 등록 유도 or 있을때 otp 6자리 인증

4. 로그인 완료

Spring boot 
테스트 URL : http://127.0.0.1/loginView

