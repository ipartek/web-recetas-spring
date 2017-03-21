<%@ include file="../includes/header.jsp"%>

<h1>Listado Usuarios</h1>

<a class="btn btn-success" href="usuario/edit">Registrarse</a>

<p>${msg}</p>

<ul>

</ul>
<table class="tablePlugin" cellspacing="0" width="100%">
	<thead>
		<tr>
			<th>#ID</th>
			<th>Imagen</th>
			<th>Cocinero</th>
			<th>Recetas</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${usuarios}" var="u">
			<tr>
				<td><a href="/usuario/edit/${u.id}">${u.id} </a></td>
				<td><img class="tamImg" alt="imagen ${u.nombre}"
					src="${u.imagen}"></td>
				<td><a href="usuario/edit/${u.id}">${u.nombre}</a></td>
				<td>
					<p>${fn:length(u.recetas)}</p>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<%@ include file="../includes/footer.jsp"%>
