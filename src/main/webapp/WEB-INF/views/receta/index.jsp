<%@ include file="../includes/header.jsp" %> 

<h1>Listado recetas</h1>

<a href="receta/edit">Crear Nuevo</a>

<p>${msg}</p>

<ul>
<c:forEach items="${recetas}" var="r">
	<li><a href="receta/edit/${r.id}">${r.nombre} <img src="${r.imagen}"/></a></li>
</c:forEach>
</ul>



<%@ include file="../includes/footer.jsp" %> 