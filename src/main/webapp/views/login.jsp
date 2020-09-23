<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@include file="/common/taglib.jsp"%>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng Nhập</title>
</head>
<body>

	<div class="wrapper fadeInDown">
		<div id="formContent">
			<c:if test = "${not empty message }">
				<div class="alert alert-${alert}" role="alert">
 					${message}
				</div>
			</c:if>
			
			<!-- Tabs Titles -->
				
			<!-- Icon -->
			<div class="fadeIn first">
				<h4>Đăng Nhập</h4>
<!-- 				<img src="http://danielzawadzki.com/codepen/01/icon.svg" id="icon" -->
<!-- 					alt="User Icon" /> -->
			</div>

			<!-- Login Form -->
			<form  action ="<c:url value = '/dang-nhap'/>" id ="formLogin" method="POST">
				<input type="text" id="username" class="fadeIn second" name="username" placeholder="Tên đăng nhập"> 
				<input type="password" id="password" class="fadeIn third" name="password" placeholder="Mật khẩu"> 
				<input type="submit" class="fadeIn fourth" value="Đăng Nhập">
				 <input type="hidden" value="login"  name = "action"/>
			</form>

			<!-- Remind Passowrd -->
			<div id="formFooter">
				<a class="underlineHover" href="#">Quên mật khẩu?</a>
			</div>

		</div>
	</div>
</body>
</html>