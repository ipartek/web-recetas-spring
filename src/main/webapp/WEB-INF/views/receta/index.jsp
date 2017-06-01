<%@ include file="../includes/header.jsp" %> 

<h1>Listado Recetas</h1>

<a href="receta/edit">Crear Nuevo</a>

<!-- atributo request -->
<p>${msg}</p>
<!-- parametro request, ej: ?msg=Mensaje+para+el+usuario -->
<p>${param.msg}</p>

<table class="tablePlugin" cellspacing="0" width="100%">
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
		<img class="tamImg75 img-circle" src="${r.usuario.imagen}">
		<p class="text-center">${r.usuario.nombre}</p>
	</td>
</tr>	
</c:forEach>


</tbody>
</table>


<%@ include file="../includes/scripts.jsp"%>


<%@ include file="../includes/footer.jsp" %> 