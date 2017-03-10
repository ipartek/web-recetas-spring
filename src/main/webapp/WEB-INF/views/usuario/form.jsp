<%@ include file="../includes/header.jsp" %> 

<a href="usuario">Volver</a>

<c:if test="${not empty usuario.nombre}">
	<h1>${usuario.nombre}</h1>
</c:if>

<c:if test="${empty usuario.nombre}">
	<h1>Crear nuevo usuario</h1>
</c:if>



${msg}

<div class="row">
<div class="col-md-6">
	<form:form action="usuario/crear" modelAttribute="usuario">
	
		<form:input path="id" readonly="true"/><br>
		<form:input path="nombre" id="nombreUsuario" placeholder ="Escribe tu nombre de Usuario"/><br>
		<p id="msgNombreUsuario"></p>	
		<form:errors path="nombre" cssStyle="color:red;" /><br>
	
		<form:input path="email" value="${usuario.email}" placeholder ="E-mail"/><br><br>
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
</div>

<div class="col-md-6">
	<h2>Recetas</h2>	
	<c:if test="${empty usuario.recetas}">
		<p>Todavía no tiene recetas</p>
	</c:if>
	
	<c:if test="${not empty usuario.recetas}">
		<ol>
		<c:forEach items="${usuario.recetas}" var="receta">
			<li><a href="receta/edit/${receta.id}">${receta.nombre}</a></li>
		</c:forEach>
		</ol>
	</c:if>
	
</div>



</div><!-- <div class="row"> -->

<c:if test="${usuario.id != -1}">
	<br>
	<a style="color:red;" href="usuario/delete/${usuario.id}">Eliminar</a>
</c:if>

<%@ include file="../includes/footer.jsp" %> 
