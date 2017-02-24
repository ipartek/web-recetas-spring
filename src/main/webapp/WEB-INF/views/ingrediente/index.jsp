<%@ include file="../includes/header.jsp" %> 
<%@ include file="../includes/nav.jsp" %> 

<a href="/formacion">Inicio</a>

<h1>Listado ingredientes</h1>

<a href="ingrediente/edit">Crear Nuevo</a>

<p>${msg}</p>

<ul>
<c:forEach items="${ingredientes}" var="i">
	<li><a href="ingrediente/edit/${i.id}">${i.nombre}</a></li>
</c:forEach>
</ul>



<%@ include file="../includes/footer.jsp" %> 