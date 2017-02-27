<%@ include file="../includes/header.jsp" %> 

<a href="usuario">Volver</a>
<h1>Formulario</h1>
${msg}
<div class="row">
	<div class="col-md-6">
<form:form action="usuario/crear" modelAttribute="usuario">

	<form:input path="id" readonly="true"/><br>
	<form:input path="nombre"/><br>	
	<form:errors path="nombre" cssStyle="color:red;"/><br>

	<form:input path="email" value="${usuario.email}"/><br><br>
	<form:label path="imagen">URL de la imagen: </form:label>
	<form:input path="imagen" value="${usuario.imagen}"/><br>
	<img class="tamImg" tag="imagenUsuario"src="${usuario.imagen}" />
	
	<br>
	<c:choose>
		<c:when test="${usuario.id == -1}">
			<form:button type="submit">Crear</form:button>
		</c:when>
		<c:otherwise>
			<form:button type="submit">Modificar</form:button>
		</c:otherwise>
	</c:choose>

</form:form>


<c:if test="${usuario.id != -1}">
	<br>
	<a style="color:red;" href="usuario/delete/${usuario.id}">Eliminar</a>
</c:if>
</div>			
	<div class="col-md-6">
		<c:if test="${recetas.size() == 0}">
			<p>Este usuario no tiene recetas</p>
		</c:if>
		<c:if test="${recetas.size() > 0}">
		<h2>Listado de recetas del usuario</h2>
		
		<ul style="list-style:none;">
			<c:forEach items="${recetas}" var="r">
			<li>
				<span class="texto col-md-6">${r.nombre}</span>
				<img class="tamImgReceta col-md-6" src="${r.imagen}"/>
			</li>
			</c:forEach>
		</ul>
		</c:if>
	</div>	
</div> <!--  <div class="row"> -->
<%@ include file="../includes/footer.jsp" %> 
