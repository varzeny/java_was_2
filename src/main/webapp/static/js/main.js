
var currentTab = "div_tab_3";

window.onload = function(){
	document.getElementById(currentTab).style.display="block";
}


function openTab(tabNum){
	console.log("탭 전환됨")
	document.getElementById(currentTab).style.display="none";
	currentTab = tabNum;
	document.getElementById(currentTab).style.display="block";
	
}



