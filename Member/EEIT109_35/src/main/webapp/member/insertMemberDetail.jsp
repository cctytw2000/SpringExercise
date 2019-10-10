<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="../JS/InsertMemberImfo.js"></script>
<script src="../JS/address.js"></script>



<link rel="stylesheet" href="../CSS/RegisteredMember.css">
<script src="https://kit.fontawesome.com/685268963f.js"></script>
</head>
<body>
	<form action="../InsertMemberImfo.do" method="post">

		<fieldset>
					<c:choose>
						<c:when test="${param.id != Null && param.token != Null}">
			
			<input type="hidden" name="memberID" value="${param.id}"> 
			<input type="hidden" name="token" value="${param.token}">
						</c:when>
						<c:otherwise>
					<input type="hidden" name="account" value="${param.account}"> 
			<input type="hidden" name="username" value="${param.username}">
						</c:otherwise>

					</c:choose>
			<div class="div1">
				<label class='label1'>身分證字號:</label><input id="idnumber" type="text"
					name="idnumber"><span id="idnumber_password"></span>
			</div>

			<div class="div1">
				<label class='label1'>地址:</label> <select id="city">
					<option value=""></option>
				</select> <select id="Township">
					<option value=""></option>
				</select><input type="text" size="30" name="alladdress" id="alladdress"
					value=""><span id="address_password"></span>

			</div>


			<div class="div1">
				<label class='label1'>性別:</label><input type="radio" id="sex"
					name="sex" value="male" required="required">男<input
					id="sex" type="radio" name="sex" value="female">女<span
					id="sex_password" required="required"></span>
			</div>
			<div class="div1">
				<label class='label1'>生日:</label><input id="date" type="date"
					name="birth"><span id="date_password"></span>
			</div>
			<div id="submit" class="div1" style="text-align: center">
				<button type="submit" disabled>新增</button>

			</div>
		</fieldset>
</body>
</html>