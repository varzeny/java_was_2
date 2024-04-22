<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>this is temp1 page</title>
</head>

<body>

	<label>ROW : </label><input type="text" id="row"><br>
	<label>COL : </label><input type="text" id="col"><br>
	<label>octave : </label><input type="text" id="octave"><br>
	<label>persistence : </label><input type="text" id="per"><br>
	<input type="button" value="맵 생성" onclick="makeMap()">

	
	<div>
		<canvas id="map"></canvas>
	</div>


	<script>
		var canvas = document.getElementById("map");
		var ctx = canvas.getContext("2d");
	
		async function makeMap(){
			console.log("makeMap 시작");
			let row = parseInt(document.getElementById("row").value);
			let col = parseInt(document.getElementById("col").value);
			let octave = parseInt(document.getElementById("octave").value);
			let per = parseFloat(document.getElementById("per").value);
	
			try{
				const response = await fetch("/prac_was_2/ctrl_temp1", {
					method: "POST",
					headers: {
						'Content-Type': 'application/json'
					},
					body: JSON.stringify({
						row: row,
						col: col,
						octave: octave,
						per: per
					})
				});
				if(!response.ok){ throw new Error("HTTP error, status = " + response.status); }
				if(response.headers.get("content-type").includes("application/json")){
					const data = await response.json();
					canvas.width = 1000;
					canvas.height = 1000;
					
		            // 받은 데이터로 캔버스에 그리기
		            for(let i = 0; i < row; i++) {
		                for(let j = 0; j < col; j++) {
		                	let color;
		                	if(data[i][j] < 100){ ctx.fillStyle = "rgb(0,0,255)"; ctx.fillRect(j*10, i*10, 10, 10); continue;}
		                	color = data[i][j];
		                	// console.log(data[i][j]);
		                    ctx.fillStyle = "rgb("+color+","+color+","+color+")";
		                    ctx.fillRect(j*10, i*10, 10, 10);
		                }
		            }
					
				}
			} catch(e){
				console.log("에러", e);
			}
			console.log("makeMap 종료");
		}
	</script>


</body>

</html>