<%@ include file="../includes/header.jsp" %> 

<a href="/formacion">Inicio</a>

<h1>Listado Recetas</h1>

<a href="receta/edit">Crear Nuevo</a>

<p>${msg}</p>

<ul>
<c:forEach items="${recetas}" var="r">
	<li><a href="receta/edit/${r.id}">${r.id} </a> <img class="tamImg"  alt="imagen ${r.nombre}" src="${r.imagen}"> <a href="receta/edit/${r.id}">${r.nombre} </a></li>
</c:forEach>
</ul>



<%@ include file="../includes/footer.jsp" %> 