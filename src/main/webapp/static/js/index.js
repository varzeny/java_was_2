

window.onload = function(){
	document.getElementById("label_msg").innerHTML = "접속하시오!"
}

window.onbeforeunload = function(){
	
}


async function f_login() {
    console.log("f_login 시작");
    var formData = new FormData(document.getElementById("form_login"));
    
    try {
        const response = await fetch("/prac_was_2/ctrl_login", { method: "POST", body: formData });
        if (!response.ok) {
            throw new Error("ctrl_login의 리스폰 문제");
        }
        const text = await response.text();
        console.log(text);
        if(text=="login success !"){
			window.location.href = "/prac_was_2/ctrl_main";
		}else{
			document.getElementById("label_msg").innerHTML = "서버의 리스폰 내용은 이렇다 : " + text;
		}
    } catch (e) {
        console.log("try에서 문제생김", e);
        document.getElementById("label_msg").innerHTML = "try에서 문제가 생김";
    }
    console.log("f_login 종료");
	
}

