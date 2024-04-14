<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>landing</title>

</head>

<body>
	<h1>여기는 랜딩페이지이다.</h1>
	<p>여기서 접속을 해야만 다른 페이지로 접근 WEB-INF에 있는 보호받는 페이지들에 접근 가능하다.</p>
	<form method="POST" action="ctrl_login" style="background-color: #aaaaaa">
		<label>ID : </label><input type="text" name=id><br>
		<label>PW : </label><input type="text" name=pw><br>
		<input type="submit" value="접속하기">
	</form>

</body>

</html>