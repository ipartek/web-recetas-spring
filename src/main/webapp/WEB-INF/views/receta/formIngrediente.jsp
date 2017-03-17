<%@ include file="../includes/header.jsp" %> 

<a href="receta">Volver</a>



<c:if test="${not empty ingrediente.nombre}">
<c:set var="accion" value="add"/>
<c:set var="boton" value="Modificar"/>
<h1>Modificar ${ingrediente.nombre} de ${receta.nombre}</h1>
</c:if>

<c:if test="${ empty ingrediente.nombre}">
<c:set var="accion" value="nuevo"/>
<c:set var="boton" value="Añadir"/>
<h1>Añadir ingrediente a receta: ${receta.nombre }</h1>
</c:if>


${msg}

<c:if test="${!ingrediente.gluten}">Gluten Free</c:if>
<c:if test="${ingrediente.gluten}">*Atención contiene Gluten</c:if>

<form:form action="receta/${receta.id}/${accion}/ingrediente" modelAttribute="ingrediente">

<!-- Si hay ingredientes para seleccionar estamos añadiendo un ingrediente no incluido a la receta -->
	<c:if test="${disponibles!=null}">

	<form:label path="idIngrediente">Selecciona el ingrediente</form:label>
	<form:select path="idIngrediente" >
		<c:forEach items="${disponibles}" var="d">
				<form:option value="${d.idIngrediente}"> ${d.nombre}</form:option>
			</c:forEach>
	 </form:select>

	</c:if>	
	
	<!-- Si no hay ingredientes para seleccionar estamos modificando un ingrediente existente -->
	
	<c:if test="${disponibles==null}">
	<form:hidden path="idIngrediente"/>
	
	</c:if>	
	<form:hidden path="nombre"/>	
	
	<form:hidden path="gluten"/>	
	<form:label path="cantidad">Cantidad:</form:label>
	<form:input path="cantidad"/>	
	<form:errors path="cantidad" cssClass="rojo"/>
	<br>
	<br>
	<form:button>${boton}</form:button>

</form:form>




 





<%@ include file="../includes/footer.jsp" %>





