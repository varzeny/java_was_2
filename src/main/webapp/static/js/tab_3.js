

var canvas = document.getElementById("world_map");
var ctx = canvas.getContext("2d");



var img1 = new Image();
img1.src = "../img/dungeon.png";
img1.onload = function(){
	ctx.drawImage(img1,0,0,10,10);
};


var img2 = new Image();
img2.src = "../img/castle.png";
img2.onload = function(){
	ctx.drawImage(img2,50,50,10,10);
};



