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
	<div>
		<label id="label_msg"></label>
		<form id="form_login" method="POST" style="background-color: #aaaaaa">
			<label>ID : </label><input type="text" name=id required="required"><br>
			<label>PW : </label><input type="password" name=pw required="required"><br>
			<input type="button" value="접속하기" onclick="f_login()">
		</form>
	</div>
	
	
	<script type="text/javascript" src="static/js/index.js"></script>

</body>

</html>