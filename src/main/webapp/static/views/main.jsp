<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Main</title>
	<link rel="stylesheet" type="text/css" href="../css/main.css">
	<link rel="stylesheet" type="text/css" href="../css/tab_1.css">
	<link rel="stylesheet" type="text/css" href="../css/tab_2.css">
	<link rel="stylesheet" type="text/css" href="../css/tab_3.css">
</head>

<body>
	<h1>메인화면</h1>
	<P>여기는 메인화면으로, 모든 활동이 이루어 집니다.</P>
	
	<div class="div_main">
		<div class="div_main_btn">
			<button class=tab_btn onclick="openTab('div_tab_1')">탭1 : 상황</button>
			<button class=tab_btn onclick="openTab('div_tab_2')">탭2 : 기지</button>
			<button class=tab_btn onclick="openTab('div_tab_3')">탭3 : 작전실</button>
		</div>

		<jsp:include page="../views/tab_1.jsp"/>
		<jsp:include page="../views/tab_2.jsp"/>
		<jsp:include page="../views/tab_3.jsp"/>

	</div>

	
	<script type="text/javascript" src="../js/main.js"></script>
	<script type="text/javascript" src="../js/tab_1.js"></script>
	<script type="text/javascript" src="../js/tab_2.js"></script>
	<script type="text/javascript" src="../js/tab_3.js"></script>
</body>
</html>