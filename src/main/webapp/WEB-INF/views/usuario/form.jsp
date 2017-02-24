<%@ include file="../includes/header.jsp" %> 
<%@ include file="../includes/nav.jsp" %> 

<a href="usuario">Volver</a>

<h1>Formulario</h1>

<form:form action="usuario/crear" modelAttribute="usuario">

	<form:hidden path="id" readonly="true"/><br>
	
	<form:label path="nombre">Nombre</form:label>
	<form:input path="nombre"/><br>	
	<form:errors path="nombre" cssStyle="color:red;"/>
	<br>
	
	<form:label path="email">Email</form:label>
	<form:input path="email"/><br>	
	<form:errors path="email" cssStyle="color:red;"/>
	<br>
	
	<form:label path="password">Password</form:label>
	<form:password showPassword="true" path="password"/><br>	
	<form:errors path="password" cssStyle="color:red;"/>
	<br>
	
	<form:label path="imagen">Imagen</form:label>
	<form:input path="imagen"/><br>	
	<form:errors path="imagen" cssStyle="color:red;"/>
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
	<br>
	

	<h2>Listado de Recetas del usuario: ${usuario.nombre}
		<span>
			<a href="#">[ Añadir Recetas ]</a>
		</span>
	</h2>
	
		<ol>
			<c:forEach items="${recetasUsuario}" var="r">
				<li> 
					<img class="tamImg" alt="foto ${r.nombre}" src="${r.imagen}">  ${r.nombre}
				</li>
			</c:forEach>
		</ol>
</c:if>

<%@ include file="../includes/footer.jsp" %> 