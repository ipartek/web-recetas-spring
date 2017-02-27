<%@ include file="../includes/header.jsp" %> 

<h1>Listado ingredientes</h1>

<a href="ingrediente/edit">Crear Nuevo</a>

<h2>Filtro de ingredientes por nombre</h2>
<p>${msg}</p>
<form:form action="ingrediente/filtro" modelAttribute="ingrediente">
	<form:label path="nombre"></form:label>
	<form:input path="nombre"/>
	<form:button type="submit">Filtrar</form:button>
</form:form>

<p>Mostrando los ultimos 500</p>

<table  class="tablePlugin" cellspacing="0" width="100%">
<thead>
	<tr>
		<th>Nombre</th>
		<th>Gluten</th>
	</tr>
</thead>
<tbody>
	<c:forEach items="${ingredientes}" var="i">
		<tr>
			<td><a href="ingrediente/edit/${i.id}">${i.nombre}</a></td>
			<td>
				<c:if test="${i.gluten}"><img style="width:50px;" src="resources/img/gluten.png"></c:if>
				<c:if test="${!i.gluten}"><img style="width:50px;" src="resources/img/sin-gluten.png"></c:if>
			</td>
		</tr>
	</c:forEach>
</tbody>
</table>


<%@ include file="../includes/footer.jsp" %> 