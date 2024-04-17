
var currentTab = "tab_1";

window.onload = function(){
	document.getElementById("tab_1").style.display="block";
}


function openTab(tabNum){
	console.log("탭 전환됨")
	document.getElementById(currentTab).style.display="none";
	currentTab = tabNum;
	document.getElementById(currentTab).style.display="block";
	
}



function sayHello() {
	alert("Hello, World!");
	console.log("aaa");
}
	
