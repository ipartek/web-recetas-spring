/*
 * Buscador en la barra de navegación, para buscar recetas mediante la API  
 * Busca recetas que coincidan su nombre, minimo 3 letras 
 * Ejemplo: http://localhost:8080/formacion/api/receta/?filter=pat
 * 
 * */	
	
"use-strict"


$(function() {

	console.debug('search-receta.js ready');
			
	$( "#buscar_receta" ).autocomplete({		
		source: function( request, response ) {
	        $.ajax( {
	          url: "/formacion/api/receta/?filter=" + $("#buscar_receta").val(),
	          dataType: "json",	          
	          success: function( data ) {
	        	var aString = [];
	        	$.each(data, function(i,v){	        		
	        		aString.push( {'label': v.nombre, 'value' : v.id} );	
	        	}); 	        	  
	            response( aString );
	          }
	        });
	      },		
	     minLength: 1,
	     select: function( event, ui ) {
	       console.debug( "Selected: " + ui.item.value + " label= " + ui.item.label );
	       var url = "receta/edit/" + ui.item.value;
	       window.location.href = url;
	     }
	});
	
	
	
})