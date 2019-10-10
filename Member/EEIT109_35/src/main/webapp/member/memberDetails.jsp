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
							<li><a href="member/LoginMember.html">登入會員</a></li>
						</c:otherwise>

					</c:choose>
				</ul>
			</nav>
			
		<jsp:useBean id="member" scope="request"
			class="com.eeit109team6.memberDao.Member" />
		
		</header>
		
		
		<div class="content">
        <article>
            <h2 class="title">會員資料  於${member.getRegisteredtime()}建檔</h2>
            <section>
              <table border="1" style="border : 1px solid black;width : 50%;text-align: center;margin : 15px auto">
              <tr><td>姓名：</td><td>${member.getUsername()}</td></tr>
              <tr><td>身分證字號：</td><td>${member.getIdnumber()}</td></tr>
              <tr><td>姓名：</td><td>${member.getSex()}</td></tr>
              <tr><td>生日：</td><td>${member.getBirth()}</td></tr>
              
              
              
              <!--  
			mem = new Member();
			mem.setMember_id(rs.getInt("member_id"));
			mem.setAccount(rs.getString("account"));
			mem.setPassword(rs.getString("password"));
			mem.setUsername(rs.getString("username"));
			mem.setIdnumber(rs.getString("idnumber"));
			mem.setSex(rs.getString("sex"));
			mem.setBirth(rs.getString("birth"));
			mem.setRegisteredtime(rs.getString("registeredtime"));
              -->
             
              </table>
            </section>
        </article>


      
    </div>
	</div>


</body>
</html>