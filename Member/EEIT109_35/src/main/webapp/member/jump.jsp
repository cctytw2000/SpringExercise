<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<meta http-equiv="refresh" content="3;url=./home.jsp">
<title>Insert title here</title>
</head>
<body>

	<div style="border: 1px solid red;width:70%;height:30%;margin:10% auto;box-shadow: 15px 15px 15px orange;border-radius: 15px 15px;text-align: center;">
	<h1>${msg}</h1>
		<h3 style="line-height: 2.5em">
			頁面將在3秒鐘之後跳轉至首頁，如果沒有跳轉請點<a href="./home.jsp">首頁</a>
		</h3>
	</div>
</body>
</html>

