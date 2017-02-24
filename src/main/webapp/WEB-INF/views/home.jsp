<%@ include file="includes/header.jsp" %> 
 
<h1>
	Hello ${usuario}!  
</h1>

<P>The time on the server is ${serverTime}. </P>

<a href="canciones">Listado Canciones</a>
<br>	
<a href="saluda/pepe">Saludo, pasando parametro 'saluda/pepe'</a>
<br>
<a href="ingrediente">CRUD Ingredientes</a>
<br>
<a href="receta">CRUD Recetas</a>


<%@ include file="includes/footer.jsp" %>