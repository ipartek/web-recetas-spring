<%@ include file="../includes/header.jsp" %> 

<h1>Listado ingredientes</h1>

<a href="ingrediente/edit">Crear Nuevo</a>


<ul>
<c:forEach items="${ingredientes}" var="i">
	<li><a href="ingrediente/edit/${i.id}">${i.nombre}</a></li>
</c:forEach>
</ul>



<%@ include file="../includes/footer.jsp" %> 