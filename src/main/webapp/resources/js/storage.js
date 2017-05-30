window.onload = function(){
	pintar();
}

function guardarStorage(nombre, url){
	console.log('nombre ' + nombre + ' url ' + url);
	flag=true;
	for (var count = 1; count <= localStorage.length; count++) {	
		if(jQuery.parseJSON(localStorage.getItem(count) == JSON.stringify({"nombre" : nombre, "url" : url}))){
			flag=false
			change= count;
		}
	}
	if(flag){
		if(localStorage.length>=5){			
			for (var count = 1; count <= localStorage.length; count++) {	
				localStorage.setItem(count,localStorage.getItem(count+1));
			}
				localStorage.setItem("5", JSON.stringify({"nombre" : nombre, "url" : url}));		
		}else{		
				localStorage.setItem((localStorage.length+1), JSON.stringify({"nombre" : nombre, "url" : url}));
		}
	}else{
		for (var count = change; count <= localStorage.length; count++) {	
			localStorage.setItem(count,localStorage.getItem(count+1));					
		}
		if(localStorage.length>=5){
			
			localStorage.setItem("5", JSON.stringify({"nombre" : nombre, "url" : url}));
		}else{		
			localStorage.setItem((localStorage.length), JSON.stringify({"nombre" : nombre, "url" : url}));
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
			ol.prepend(liaux);
		}
}