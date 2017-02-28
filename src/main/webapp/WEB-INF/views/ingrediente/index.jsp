<%@ include file="../includes/header.jsp" %> 
<%@ include file="../includes/nav.jsp" %> 

<h1>Listado ingredientes</h1>

<a href="ingrediente/edit">Crear Nuevo</a>

<p>${msg}</p>

<div class="row">
	<form:form action="ingrediente/filtro" modelAttribute="formularioBusqueda">
		<div class="col-md-6">
			<form:label path="nombre">Buscar Ingrediente por Nombre</form:label>
			<form:input class="form-control" path="nombre"/>
			<form:errors path="nombre" cssClass="error"></form:errors><br>
			<form:label path="ordenAscendente">Ordenado Ascendentemete</form:label>
			<form:checkbox path="ordenAscendente"/><br>	
		</div> <!-- <div class="col-md-6"> -->
		
		<div class="col-md-4">
			<br>
			<form:button class="btn btn-primary" type="submit">Buscar</form:button>
		</div> <!-- <div class="col-md-4"> -->
		
	</form:form>
</div><!-- <div class="row"> -->
<br><br>
<table class="tablePlugin">
	<thead>
		<tr>
			<th>Nombre</th>
			<th>Gluten</th>
		</tr>
	</thead>

	<tbody> 	
		<c:forEach items="${ingredientes}" var="i">
			<tr>
				<td>
					<a href="ingrediente/edit/${i.id}">${i.nombre}</a>
				</td>
				<td>
					<c:if test="${i.gluten}"><img class="imgIcon " alt="gluten Free" src="resources/img/gluten.png"> </c:if>
					<c:if test="${!i.gluten}"><img class="imgIcon" alt="gluten Free" src="resources/img/glutenFree.png"> </c:if>
				</td>
			</tr>
		</c:forEach>
	</tbody>
	
</table>



<%@ include file="../includes/footer.jsp" %> 