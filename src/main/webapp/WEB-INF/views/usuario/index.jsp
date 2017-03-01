<%@ include file="../includes/header.jsp" %> 
<%@ include file="../includes/nav.jsp" %> 

<h1><spring:message code="view.usuario.titulo" text="Listado Usuarios"/></h1>

<a href="usuario/edit"><spring:message code="url.crear" text="Crear Nuevo"/></a>

<%@ include file="../includes/mensaje.jsp" %> 

<ul>
<c:forEach items="${usuarios}" var="u">
	<li><a href="usuario/edit/${u.id}"><img class="tamImg" alt="Foto ${u.nombre}" src="${u.imagen}"> ${u.nombre}</a></li>
</c:forEach>
</ul>



<%@ include file="../includes/footer.jsp" %> 