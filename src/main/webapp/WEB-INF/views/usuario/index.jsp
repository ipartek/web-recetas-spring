<%@ include file="../includes/header.jsp" %> 

<h1>Listado Usuarios</h1>

<a href="usuario/edit">Registrarse</a>

<p>${msg}</p>

<table class="tablePlugin" cellspacing="0" width="100%">
  <thead>
      <tr>
          <th>Imagen</th>
          <th>Nombre</th>
          <th>Nº Recetas</th>          
      </tr>
  </thead>
  <tbody> 
		<c:forEach items="${usuarios}" var="u">
			<tr>
				<td>
					<a href="usuario/edit/${u.id}"> 
						<img class="tamImg"  alt="imagen ${u.nombre}" src="${u.imagen}">
					</a>
				</td>				
				<td>
					<a href="usuario/edit/${u.id}">${u.nombre}</a>
				</td>
				<td>
					<span class="label label-primary">${fn:length(u.recetas)}</span>
				</td>
			</tr>
		</c:forEach>
</tbody>
</table>

<%@ include file="../includes/scripts.jsp" %> 
<%@ include file="../includes/footer.jsp" %> 