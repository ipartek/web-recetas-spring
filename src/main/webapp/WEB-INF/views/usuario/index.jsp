<%@ include file="../includes/header.jsp" %> 

<a href="/formacion">Inicio</a>

<h1>Listado Usuarios</h1>

<a href="usuario/edit">Crear Nuevo</a>

<p>${msg}</p>

<ul>
<c:forEach items="${usuarios}" var="u">
	<li><a href="usuario/edit/${u.id}"><img class="tamImg" alt="Foto ${u.nombre}" src="${u.imagen}"> ${u.nombre}</a></li>
</c:forEach>
</ul>



<%@ include file="../includes/footer.jsp" %> 