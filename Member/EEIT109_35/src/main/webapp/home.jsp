<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="CSS/main.css">
</head>
<body>
	<div id="allpage">
		<header>
			<nav>
				<ul class="menu">
					<li><a href="#">回首頁</a></li>
					<li><a href="#">遊戲討論區</a></li>
					<li><a href="#">遊戲商城</a></li>
					<li><a href="#">電競新聞</a></li>
					<c:choose>
						<c:when test="${sessionScope.account != Null}">
							<li><a href="./MemberDetails.do">會員中心</a></li>
							<li><a href="./LogOutMember.do">登出</a></li>
							<span class="welcome">${sessionScope.username} 您好!</span>
						</c:when>
						<c:otherwise>
							<li><a href="member/RegisteredMember.html">註冊會員</a></li>
							<li><a href="member/LoginMember.jsp">登入會員</a></li>
						</c:otherwise>

					</c:choose>
				</ul>
			</nav>
		</header>

	</div>



</body>
</html>