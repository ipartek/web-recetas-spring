function abrirWidget(){
	if($("#widget img").hasClass("abrir")){
		$("#widget img").removeClass("abrir");
		$("#widget img").addClass("cerrar");
		$("#widget").css("right","0%");
	}else{
		$("#widget img").removeClass("cerrar");
		$("#widget img").addClass("abrir");
		$("#widget").css("right","-18%");
	}
	
}