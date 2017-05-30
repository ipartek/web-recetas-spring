window.onload = function(){
	pintar();
}
function guardarStorage(nombre, url){
	console.log('nombre ' + nombre + ' url ' + url);
	flag=true;
	for (var count = 1; count <= localStorage.length; count++) {	
		if(jQuery.parseJSON(localStorage.getItem(count) == JSON.stringify({"nombre" : nombre, "url" : url}))){
			flag=false
		}
	}
	if(flag){
		if(localStorage.length>=5){			
				localStorage.setItem("5",localStorage.getItem(4));
				localStorage.setItem("4",localStorage.getItem(3));
				localStorage.setItem("3",localStorage.getItem(2));
				localStorage.setItem("2",localStorage.getItem(1));
				localStorage.setItem("1", JSON.stringify({"nombre" : nombre, "url" : url}));		
		}else{		
				localStorage.setItem((localStorage.length+1), JSON.stringify({"nombre" : nombre, "url" : url}));
		}
	}
	pintar();
	
}

function pintar(){
	var ol = $("#list");
	ol.empty();
	var li = "<li><a href='##link##'>##nombre##</a></li>"
		for (var count = 1; count <= localStorage.length; count++) {	
			liaux = li.replace("##link##", jQuery.parseJSON(localStorage.getItem(count)).url);
			liaux = liaux.replace("##nombre##", jQuery.parseJSON(localStorage.getItem(count)).nombre);
			ol.append(liaux);
		}
}