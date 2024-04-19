

var canvas = document.getElementById("world_map");
var ctx = canvas.getContext("2d");

var img = new Image();
img.onload = function(){
	ctx.drawImage(img,500,400,500,400,0,0,800,600);
};
img.src = "../img/world_map.jpg";