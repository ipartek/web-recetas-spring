<%@ include file="../includes/header.jsp" %> 

<h1>Listado ingredientes</h1>

<a class="btn btn-primary" href="ingrediente/edit">Crear Nuevo</a>

<p>${msg}</p>

<p>mostrando ${fn:length(ingredientes)} ingredientes de ${total}</p>
<br>
<form:form action="ingrediente" method="post" modelAttribute="formularioBusqueda">
	<form:label path="nombre">Busca Ingredientes</form:label>
	<form:input path="nombre"/>
	<form:errors path="nombre" cssClass="error"></form:errors>
	<br>
	<form:label path="ordenAscendente">Ordenados Ascendentemente</form:label>
	<form:checkbox path="ordenAscendente"/>
	<br>
	<form:button class="btn btn-warning" type="submit">Filtrar</form:button>
</form:form>


<table class="tablePlugin" cellspacing="0" width="100%">
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
			<c:if test="${i.gluten}"><img class="img_icon" src="resources/img/gluten.png" alt="contiene Gluten"/></c:if>
			<c:if test="${!i.gluten}"><img class="img_icon" src="resources/img/gluten_free.png" alt="Libre de Gluten"/></c:if>
		</td>
	</tr>		
	</c:forEach>
</tbody>	
</table>

<%@ include file="../includes/footer.jsp" %> 