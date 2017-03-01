<%@ include file="../includes/header.jsp" %> 
<%@ include file="../includes/nav.jsp" %> 

<a href="ingrediente"><spring:message code="url.volver" text="Volver"/></a>

<h1><spring:message code="view.form.ingrediente.titulo" text="Formulario"/></h1>

<form:form action="ingrediente/crear" modelAttribute="ingrediente">

	<form:input class="form-control" path="id" readonly="true"/><br>
	<form:label path="nombre"><spring:message code="view.form.label.nombre" text="Nombre"/></form:label>
	<form:input class="form-control" path="nombre"/><br>	
	<form:errors path="nombre" cssStyle="color:red;"/>
	<br>
	
	
	<form:label path="gluten"><spring:message code="view.form.label.gluten" text="¿Contiene Gluten?"/></form:label>
	<form:checkbox path="gluten"/><br>	
	
	<c:choose>
		<c:when test="${ingrediente.id == -1}">
			<form:button class="btn btn-success" type="submit"><spring:message code="btn.crear" text="Crear Nuevo"/></form:button>
		</c:when>
		<c:otherwise>
			<form:button class="btn btn-success" type="submit"><spring:message code="btn.modificar" text="Modificar"/></form:button>
		</c:otherwise>
	</c:choose>

</form:form>

<c:if test="${ingrediente.id != -1}">
	<br>
	<a style="color:red;" href="ingrediente/delete/${ingrediente.id}"><button class="btn btn-danger"><spring:message code="btn.eliminar" text="Eliminar"/></button></a>

</c:if>

<%@ include file="../includes/footer.jsp" %> 