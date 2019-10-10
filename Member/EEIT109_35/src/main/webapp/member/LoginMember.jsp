<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Login Member</title>
	<link rel="stylesheet" href="../CSS/login.css">
	<script src="../JS/login.js"></script>

</head>

<body>
	<form action="../LoginMember.do" method="post">

		<fieldset>
			<legend>登入帳號</legend>
			<div class="div1">
				<label class='label1'>帳號:</label><input type="text" id="account" name="account"><span id="account_msg"></span>
			</div>
			<div class="div1">
				<label class='label1'>密碼:</label><input type="password" id="password" name="password"><span id="passowrd_msg"></span>

			</div>
            <div style="margin: auto auto;width: 60%">
                <button name="FaceBook" type="button" style="background-color: #0066FF" class="btn btn-facebook" onclick="FBLogin();"><i
                        class="fab fa-facebook-f fa-lg"></i> Facebook登入
                </button> <button name="Google" type="button" style="background-color: #FF3333" class="btn btn-google"
                    onclick="GoogleLogin();"><i class="fab fa-google-plus-g fa-lg"></i> Google登入
                </button>
            </div>
 
            <div id="submit" class="div1" style="text-align: center">
				<input name="login" type="submit" value="登入" disabled> <input name="fergetpwd" type="submit" value="忘記密碼">

            </div>
		</fieldset>
	</form>
</body>

</html>