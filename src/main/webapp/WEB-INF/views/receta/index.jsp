<%@ include file="../includes/header.jsp" %> 
<%@ include file="../includes/nav.jsp" %> 

<a href="/formacion">Inicio</a>

<h1>Listado Recetas</h1>

<a href="receta/edit">Crear Nuevo</a>

<%@ include file="../includes/mensaje.jsp" %> 

<table class="tablePlugin">
	<thead>
		<tr>
			<th>receta</th>
			<th>imagen</th>
			<th>cocinero</th>          
		</tr>
	</thead>

	<tbody>       
		<c:forEach items="${recetas}" var="r">
			<tr>
				<td>
					<a href="receta/edit/${r.id}">
						<img class="tamImg img-thumbnail"  alt="imagen ${r.nombre}" src="${r.imagen}"> 
					</a>
				</td>
				<td>
					<a href="receta/edit/${r.id}">${r.nombre}</a>
				</td>
				<td>
					<img class="tamImg75 img-circle"  alt="imagen ${r.usuario.nombre}" src="${r.usuario.imagen}">
					<p class="text-center">${r.usuario.nombre}</p>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>



<%@ include file="../includes/footer.jsp" %> 