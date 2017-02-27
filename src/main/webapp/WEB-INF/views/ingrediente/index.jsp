<%@ include file="../includes/header.jsp" %> 

<h1>Listado ingredientes</h1>

<a href="ingrediente/edit">Crear Nuevo</a>

<p>${msg}</p>

<p>***TODO mostrando x ingredientes de TOTAL BBDD</p><br>
<p>***TODO implementar buscador</p>


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