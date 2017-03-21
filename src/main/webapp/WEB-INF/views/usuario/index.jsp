<%@ include file="../includes/header.jsp" %> 
<%@ include file="../includes/nav.jsp" %> 

<h1><spring:message code="view.usuario.titulo" text="Listado Usuarios"/></h1>

<a href="usuario/edit"><spring:message code="url.crear" text="Crear Nuevo"/></a>

<%@ include file="../includes/mensaje.jsp" %> 


<table class="tablePlugin">
	<thead>
		<tr>
			
			<th>imagen</th>
			<th>nombre</th>
			<th>Nº Recetas</th>          
		</tr>
	</thead>

	<tbody>       
		<c:forEach items="${usuarios}" var="u">
			<tr>
				<td>
					<a href="usuario/edit/${u.id}"><img class="tamImg" alt="Foto ${u.nombre}" src="${u.imagen}"></a>
				</td>
				<td>
					<a href="receta/edit/${u.id}">${u.nombre}</a>
				</td>
				<td>
				<span class="label label-primary">${fn:length(u.recetas)}</span>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>



<%@ include file="../includes/footer.jsp" %> 