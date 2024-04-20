
var currentTab = "div_tab_3";

window.onload = function(){
	document.getElementById(currentTab).style.display="block";
	loadUser();
	console.log("~~~");
	
}


function openTab(tabNum){
	console.log("탭 전환됨")
	document.getElementById(currentTab).style.display="none";
	currentTab = tabNum;
	document.getElementById(currentTab).style.display="block";
}


async function loadUser(){
	console.log("loadData 발동");
	try{
		const response = await fetch( "/prac_was_2/ctrl_loadUser", {method:"GET"} );
		if( !response.ok ){ throw new Error("ctrl_loadData 오류!"); }
		
		const ct = response.headers.get("content-type");
		if( ct && ct.includes("application/json") ){
			const data = await response.json();
			console.log(data);			
		}else{
			const text = await response.text();
			console.log(text);
		}
		
	}catch(e){
		console.log("문제 생김!",e);
	}
	console.log("loadData 종료!");
}

