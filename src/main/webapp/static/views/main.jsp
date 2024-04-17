<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main</title>
<link rel="stylesheet" type="text/css" href="../css/main.css">

</head>

<body>
	<jsp:include page="../views/nav.jsp"></jsp:include>
	<h1>여기는 메인화면 입니다.</h1>
	
	<div class="div_main">
		<div class="div_main_btn">
			<button class=tab_btn onclick="openTab('tab_1')">탭1</button>
			<button class=tab_btn onclick="openTab('tab_2')">탭2</button>
			<button class=tab_btn onclick="openTab('tab_3')">탭3</button>
		</div>

		<div id="tab_1" class="tab_content">
			<h3>tab1</h3>
		</div>
		
		<div id="tab_2" class="tab_content">
			<h3>tab2</h3>
		</div>
		
		<div id="tab_3" class="tab_content">
			<h3>tab3</h3>
		</div>

	</div>
	

	
	

	
	<script type="text/javascript" src="../js/main.js"></script>
</body>
</html>