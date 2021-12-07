<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

 <meta content="text/html;charset=utf-8" http-equiv="Content-Type">
 <meta name="keywords" content="Template, html, premium, themeforest" />
 <meta name="description" content="Traveler - Premium template for travel companies">
 <meta name="author" content="Tsoy">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">

 <!-- GOOGLE FONTS -->
 <link href='http://fonts.googleapis.com/css?family=Roboto:400,300,100,500,700' rel='stylesheet' type='text/css'>
 <link href='http://fonts.googleapis.com/css?family=Open+Sans:400italic,400,300,600' rel='stylesheet' type='text/css'>
 <!-- /GOOGLE FONTS -->
 <link rel="stylesheet" href="/Web-home/css/bootstrap.css">
 <link rel="stylesheet" href="/Web-home/css/font-awesome.css">
 <link rel="stylesheet" href="/Web-home/css/icomoon.css">
 <link rel="stylesheet" href="/Web-home/css/styles.css">
 <link rel="stylesheet" href="/Web-home/css/mystyles.css">
 <script src="/Web-home/js/modernizr.js"></script>

  <script src="/Web-home/js/jquery.js"></script>
  <script src="/Web-home/js/bootstrap.js"></script>
  <script src="/Web-home/js/slimmenu.js"></script>
  <script src="/Web-home/js/bootstrap-datepicker.js"></script>
  <script src="/Web-home/js/bootstrap-timepicker.js"></script>
  <script src="/Web-home/js/nicescroll.js"></script>
  <script src="/Web-home/js/dropit.js"></script>
  <script src="/Web-home/js/ionrangeslider.js"></script>
  <script src="/Web-home/js/icheck.js"></script>
  <script src="/Web-home/js/fotorama.js"></script>
  <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>
  <script src="/Web-home/js/typeahead.js"></script>
  <script src="/Web-home/js/card-payment.js"></script>
  <script src="/Web-home/js/magnific.js"></script>
  <script src="/Web-home/js/owl-carousel.js"></script>
  <script src="/Web-home/js/fitvids.js"></script>
  <script src="/Web-home/js/tweet.js"></script>
  <script src="/Web-home/js/countdown.js"></script>
  <script src="/Web-home/js/gridrotator.js"></script>
  <script src="/Web-home/js/custom.js"></script>
  
<script>
function loginSubmit(form) { 
	var userId = $("#userId").val(); 
	var userPw = $("#userPw").val();
	var chk = true; 
	var message = ""; 
	if(userId==null || userId=='' || userId=='undefined'){
		chk = false;
		alert("아이디를 입력해주세요.");
		$("#userId").focus();		 
	} 
	if(userPw==null || userPw=='' || userPw=='undefined'){ 
		chk = false;
			alert("비밀번호를 입력해주세요.");
		$("#userPw").focus();	
	}
	if(chk){ 
		 $.ajax({ 
			 type:"post", 
			 url:'/otpGenerate', 
			 data:{"userId":userId, "userPw":userPw}, 
			 async:false,
			 cache:false, 
			 success:function(r) { 
				 if(r.result=="OTP_KEY_HAVE") {
					 var url2= "/otpCert"; 
					 var html = "<h3>GOOGLE OTP 인증</h3>"; 
					 //html += " <a href=\"#close\" onclick=\"$.unblockUI();\" class=\"_blockClose\">close</a>";
					 html += " <input type='hidden' id='userId' name='userId' value='"+userId+"'>"; 
					 html += " <input type='hidden' id='userPwd' name='userPwd' value='"+userPw+"''>"; 
					 html += " <div class='form-group form-group-icon-left'><i class='fa fa-lock input-icon input-icon-show'></i>";
					 html += " <label>OTP번호</label>"; 
					 html += " <input type='text' name='userCode' id='userCode'>";
					 html += " <input class='btn btn-primary' type='button' onclick='otpCert();' value='인증' />";
					 html += " </div>"; 
					 $("#otpCert").append(html);
					 //$.blockUI({ message:html }); 
					 }else if(r.result=="NOT_MATCHED_PWD"){
						 var html = "<div class=\"_blockInner\">"; 
						 html += " <a href=\"#close\" onclick=\"$.unblockUI();\" class=\"_blockClose\">close</a>"; 
						 html += "아이디와 패스워드가 일치하지 않습니다."; 
						 html +="</div>"; 
						// $.blockUI({ message:html }); 
					 }else if(r.result=="OTP_KEY_GENERATE"){ 
						 var qrUrl = r.qrCdUrl; 
						 var url2= "/otpCert"; 
						 var html = "";
						 //html += " <a href=\"#close\" onclick=\"$.unblockUI();\" class=\"_blockClose\">close</a>";
						 html += " <input type='hidden' id='userId' name='userId' value='"+userId+"'>"; 
						 html += " <input type='hidden' id='userPwd' name='userPwd' value='"+userPw+"''>"; 
						 html += " <div class='form-group form-group-icon-left'><i class='fa fa-lock input-icon input-icon-show'></i>";
						 html += " <label>OTP번호</label>"; 
						 html += " <input type='text' name='userCode' id='userCode'>";
						 html += " <input class='btn btn-primary' type='button' onclick='otpCert();' value='인증' />";
						 html += " </div>"; 
						 html += "<div class=\"_blockInner\">"; 
						 html += " <iframe style='height:220px;' name=\"blockUI\" src=\""+qrUrl+"\"></iframe>"; 
						 html += "<br> QR 코드를 GOOGLE OTP앱으로 바코드 등록해주세요.<br>";
						 html += "앱 다운로드 : <a href='https://play.google.com/store/apps/details?id=com.google.android.apps.authenticator2&hl=ko' target='_blank'>안드로이드</a> <br>"
						 html += "앱 다운로드 : <a href='https://itunes.apple.com/kr/app/google-authenticator/id388497605?mt=8' target='_blank'>iOS</a>"
						 html +="</div>"; 
						 
						 $("#otpCert").append(html);
						 //$.blockUI({ message:html }); 
					 } 
			} 
		}); 
	} 
}

function otpCert() { 
	var userCode = $("#userCode").val();
	var chk = true; 
	if(userCode==null || userCode=='' || userCode=='undefined'){ 
		chk = false;
		alert("인증번호 입력해주세요.");
		$("#userCode").focus();	
	 }
	if(chk){ 
		$.ajax({ 
			 type:"post", 
			 url:'/otpCert', 
			 data:{"userCode":userCode}, 
			 async:false,
			 cache:false, 
			 success:function(r) {
				 if(r.result=="SUCCESS"){
					 alert("인증에 성공하였습니다.");
				 }else{
					 alert("인증에 실패하였습니다.");
				 }
			 }
		});
	}
}
</script>