/*
 Buscador de la barra de navegacion de Recetas mediante la API rest
 BUsca recetas que coincidan su nombre, minimo 3 letras
 Ejemplo: http://localhost:8080/formacion/api/receta/?filter=pat
*/

"use strict"

var recetas;

$(function() {
  console.debug('buscadorRecetas.js ready');

  $( "#buscadorReceta" ).autocomplete({
      source: function( request, response ) {
        $.ajax( {
          url: "/formacion/api/receta/?filter=" + $("#buscadorReceta").val(),
          dataType: "json",
          success: function( data ) {
        	var aString = [];
        	recetas = data;
            $.each(data, function(index, rec){
            	aString.push(rec.nombre);
        	});
            response( aString );
          }
        } );
      },
      minLength: 3,
      select: function( event, ui ) {
        console.debug( "Selected: " + ui.item.value + " aka " + ui.item.id );
        $.each(recetas, function(index, rec){
        	if (ui.item.value==rec.nombre) {
        		window.location.href = "receta/edit/" + rec.id;
        	}
    	});
      }
    } );
  
});