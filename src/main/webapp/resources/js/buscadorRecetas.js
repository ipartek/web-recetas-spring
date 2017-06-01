/*
 Buscador de la barra de navegacion de Recetas mediante la API rest
 BUsca recetas que coincidan su nombre, minimo 3 letras
 Ejemplo: http://localhost:8080/formacion/api/receta/?filter=pat
*/

"use strict"



$(function() {
  console.debug('buscadorRecetas.js ready');
  
  $( "#buscadorReceta" ).autocomplete({
      source: function( request, response ) {
        $.ajax( {
          url: "/formacion/api/receta/?filter=" + $("#buscadorReceta").val(),
          dataType: "json",
          success: function( data ) {
        	var aString = [];
            $.each(data, function(index, rec){
            	aString.push({"label":rec.nombre, "value":rec.id});
        	});
            response( aString );
          }
        } );
      },
      minLength: 3,
      select: function( event, ui ) {
        console.debug( "Selected: " + ui.item.value + " id " + ui.item.label );
        var url = "receta/edit/"+ ui.item.value;
        window.location.href = url;
      }
    } );
  
});