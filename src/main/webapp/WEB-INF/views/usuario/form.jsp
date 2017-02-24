<%@ include file="../includes/header.jsp" %> 

<a href="usuario">Volver</a>

<h1>Formulario</h1>
${msg}

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
