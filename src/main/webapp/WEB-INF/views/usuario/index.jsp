<%@ include file="../includes/header.jsp" %> 
<%@ include file="../includes/nav.jsp" %> 

<a href="/formacion">Inicio</a>

<h1>Listado Usuarios</h1>

<a href="usuario/edit">Crear Nuevo</a>

<%@ include file="../includes/mensaje.jsp" %> 

<ul>
<c:forEach items="${usuarios}" var="u">
	<li><a href="usuario/edit/${u.id}"><img class="tamImg" alt="Foto ${u.nombre}" src="${u.imagen}"> ${u.nombre}</a></li>
</c:forEach>
</ul>



<%@ include file="../includes/footer.jsp" %> 