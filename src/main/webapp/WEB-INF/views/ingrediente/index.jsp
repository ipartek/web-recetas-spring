<%@ include file="../includes/header.jsp" %> 

<h1><spring:message code="view.index.ingrediente.titulo" text="Ingredientes"/></h1>

<a class="btn btn-primary" href="ingrediente/edit">
	<spring:message code="btn.crear" text="Crear"/>
</a>

<p>${msg}</p>


<p>
	<spring:message code="view.index.ingrediente.mostrar" 
					arguments="${fn:length(ingredientes)},${total}" 
					text="mostrando x de y"/>
</p>

<br>
<form:form action="ingrediente" method="post" modelAttribute="formularioBusqueda">
	<form:label path="nombre">
		<spring:message code="view.form.label.buscar" text="Orden Ascendente"/>
	</form:label>
	<form:input path="nombre"/>
	<form:errors path="nombre" cssClass="error"></form:errors>
	<br>
	<form:label path="ordenAscendente">
		<spring:message code="view.form.label.orden" text="Orden Ascendente"/>
	</form:label>
	<form:checkbox path="ordenAscendente"/>
	<br>
	<form:button class="btn btn-warning" type="submit">
		<spring:message code="btn.filtrar" text="Filtrar"/>
	</form:button>
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

<%@ include file="../includes/scripts.jsp" %>
<%@ include file="../includes/footer.jsp" %> 