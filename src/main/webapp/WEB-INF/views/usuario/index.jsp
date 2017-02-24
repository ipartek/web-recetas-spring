<%@ include file="../includes/header.jsp" %> 

<h1>Listado Usuarios</h1>

<a href="usuario/edit">Registrarse</a>

<p>${msg}</p>

<ul>
<c:forEach items="${usuarios}" var="u">
	<li>
		<a href="/usuario/edit/${u.id}">${u.id} </a> 
		<img class="tamImg"  alt="imagen ${u.nombre}" src="${u.imagen}"> 
		<a href="usuario/edit/${u.id}">${u.nombre}</a>
	</li>
</c:forEach>
</ul>


<%@ include file="../includes/footer.jsp" %> 