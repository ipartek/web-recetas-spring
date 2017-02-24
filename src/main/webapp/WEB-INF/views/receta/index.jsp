<%@ include file="../includes/header.jsp" %> 

<h1>Listado Recetas</h1>

<a href="receta/edit">Crear Nuevo</a>

<p>${msg}</p>

<ul>
<c:forEach items="${recetas}" var="r">
	<li>		
		<a href="receta/edit/${r.id}">
			<img class="tamImg img-thumbnail"  alt="imagen ${r.nombre}" src="${r.imagen}"> 
			${r.nombre}
		</a>
		<img class="tamImg img-circle" src="${r.usuario.imagen}">${r.usuario.nombre}
	</li>
</c:forEach>
</ul>



<%@ include file="../includes/footer.jsp" %> 