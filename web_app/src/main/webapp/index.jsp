<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Find where your phone is</title>
	<link rel = "stylesheet" href="resources/css/style.css">
	<style>
		@font_face{
			font-family:'SDMiSaeng'; <!-- 글꼴 -->
			src: local('SDMiSaeng'),
			url('SDMiSaeng.eot'),
			url('SDMiSaeng.woff') format('woff'),
			url('SDMisaeng.ttf') format('truetype');
		}
		
		h1.fi {
        	display: none;
      	}
	
	</style>
	
	<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
	<script>
		$( document ).ready( function() {
	    	$( 'h1.fi' ).fadeIn( 3000 );
		});
	</script>
</head>
<body>
	<div id="wrap">
		<header>
			<div>
				<h1 class = "logo">
					<a href="https://www.naver.com"><img src="resources/image/phone.jpg"></a>
					
				</h1>
				
				<ul class="title">
					<li>최고다 폰 찾기</li>
				</ul>
				
				
				<ul class="gnb">
					<li><a href="./member/google_api.do">GPS 활동 기록</a></li>
					<li><a href="json">분실 정보</a></li>
					
					
					<c:if test="${member == null}">
						<li><a href="./member/loginForm.do">로그인</a></li>
					</c:if>
					<c:if test="${member != null}">
						<li><a href="./member/logout.do">로그아웃</a></li>
					</c:if>
					<li><a href="./member/memberJoinForm.do">회원가입</a></li>
				</ul>
			</div>
		</header>
	</div>
	<button onClick="window.location.reload()">새로 고침</button>
	<center>
		
		
    	<h1 class="fi"><br>GPS 기록으로 폰을 찾으세요!<br><br></h1>
		
		<a href="https://www.google.com"><img src="resources/image/sb1.jpg"></a>
	</center>

<!-- <P>  The time on the server is ${serverTime}. </P> -->

</body>
</html>