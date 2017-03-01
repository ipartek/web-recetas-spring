<%@ include file="../includes/header.jsp" %> 
<%@ include file="../includes/nav.jsp" %> 

<a href="usuario"><spring:message code="url.volver" text="Volver"/></a>

<h1><spring:message code="view.usuario.titulo" text="Formulario"/></h1>

<div class="row">
	<div class="col-md-6">
		<form:form action="usuario/crear" modelAttribute="usuario">
		
			<form:hidden path="id" readonly="true"/><br>
			
			<form:label path="nombre"><spring:message code="view.form.label.nombre" text="Nombre"/></form:label>
			<form:input class="form-control" path="nombre"/><br>	
			<form:errors path="nombre" cssStyle="color:red;"/>
			<br>
			
			<form:label path="email"><spring:message code="view.form.label.email" text="Email"/></form:label>
			<form:input class="form-control" path="email"/><br>	
			<form:errors path="email" cssStyle="color:red;"/>
			<br>
			
			<form:label path="password"><spring:message code="view.form.label.password" text="Password"/></form:label>
			<form:password class="form-control" showPassword="true" path="password"/><br>	
			<form:errors path="password" cssStyle="color:red;"/>
			<br>
			
			<form:label path="imagen"><spring:message code="view.form.label.imagen" text="Imagen"/></form:label>
			<form:input class="form-control" path="imagen"/><br>	
			<form:errors path="imagen" cssStyle="color:red;"/>
			<br>
			
			<c:choose>
				<c:when test="${usuario.id == -1}">
					<form:button class="btn btn-success" type="submit"><spring:message code="btn.crear" text="Crear"/></form:button>
				</c:when>
				<c:otherwise>
					<form:button class="btn btn-success" type="submit"><spring:message code="btn.modificar" text="Modificar"/></form:button>
				</c:otherwise>
			</c:choose>
		
		</form:form>
		
		<c:if test="${usuario.id != -1}">
			<br>
			<a style="color:red;" href="usuario/delete/${usuario.id}"><button class="btn btn-danger"><spring:message code="btn.eliminar" text="Eliminar"/></button></a>
			<br>
		</c:if>
	</div> <!-- <div class="col-md-6"> -->
	
	<div class="col-md-6">
		<c:if test="${usuario.id != -1}">
			
			<h2><spring:message code="view.form.usuario.titulo" arguments="${usuario.nombre}" text="Listado de Recetas del usuario: ${usuario.nombre}"/>
				<span>
					<a href="#"><button class="btn btn-primary">Añadir Recetas</button></a>
				</span>
			</h2>
			
			<c:if test="${empty recetasUsuario}">
				<p>El usuario no tiene recetas todavia.</p>
			</c:if>
			
				<ol>
					<c:forEach items="${recetasUsuario}" var="r">
						<li> 
							<a href="receta/edit/${r.id}">
								<img class="tamImg img-thumbnail" alt="foto ${r.nombre}" src="${r.imagen}">  ${r.nombre}
							</a>
						</li>
					</c:forEach>
				</ol>
		</c:if>
	</div> <!-- <div class="col-md-6"> -->
</div> <!-- <div class="row"> -->

<%@ include file="../includes/footer.jsp" %> 