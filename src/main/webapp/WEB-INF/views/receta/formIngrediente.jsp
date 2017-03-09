
<!-- Formulario para añadir un ingrediente a una Receta -->
<%@ include file="../includes/header.jsp" %> 

<a href="receta">Volver</a>


<!-- declara una variable y le asigna un 
valor (en este caso, edit) y a continuación hace la evaluación mediante un if
de cuando hay que editar los ingredientes -->

<!-- Hacemos un if en el que recogemos si el parámetro nombre no viene vacio, ya que
si tiene información no hay que crear uno nuevo, si no solo editarlo. -->

<!-- Hacemos otro if en el que recogemos si el parámetro del nombre viene vacio, ya que
en caso de venir vacío, se trata de un ingrediente que debemos añadir (ya que no
hay nada que cambiar, solo podemos añadir algo) -->

	<!-- seguimos con la variable que hemos declarado antes y le cambiamos el 
	valor a nuevo, ya que en esta opción lo que queremos es añadir uno nuevo.
	No se si este parámetro se añade en la URL o no, pero sirve para diferenciar
	que acción hay que realizar. -->


<c:set var="accion" value="edit" />
<c:if test="${not empty ingrediente.nombre }">
	<h1>Modificar ${ingrediente.nombre} en <b>${receta.nombre}</b></h1>
</c:if>
<c:if test="${empty ingrediente.nombre}">
	<h1>Añadir Ingrediente Nuevo en <b>${receta.nombre}</b></h1>
	<c:set var="accion" value="nuevo" />
</c:if>


${msg}

<!-- mediante dos if evaluamos si tiene o no tiene gluten la receta. Como tenemos
ingrediente.gluten=true por defecto codificado para indicar de que SI tiene gluten,
a la hora de saber si no tiene gluten habrá que poner que es distinto de true (es
decir, false al tratarse de una booleana) -->

<!-- Dentro del formulario, en la declaración de ello, debemos indicar a dónde va el
formulario, donde muestra los datos etc. En el action hay que poner una URL y ahí
escribimos la ruta necesaria, utilizando cuando sea posible las variables para
una mejor optimización (que muestre la id del ingrediente seleccionado, el nombre
de una receta, la acción que estamos haciendo etc). Como solo tenemos un formulario
y en el propio formulario debemos poner dos acciones diferentes (una añadir un
ingrediente si NO recibimos información y otra editarlo si recibimos información) y 
para evitar crear 2 formularios, en la URL indicamos la página de la que venimos (que
en este caso es receta), luego le pasamos la ID de la receta que estamos 
editando (ya que la ID nunca va a repetirse, al contrario de lo que puede pasar
con los nombres etc de las recetas), y aquí también le indicamos la acción que
debe realizar (y que hemos especificado antes mediante un c:set  ) para
evitar que se confunda y vaya a dos zonas diferentes. Después terminamos por
añadir la parte final de la receta, que en este caso es ingrediente. De tal forma que un 
ejemplo de URL de cada opción sería el siguiente:

http://localhost:8080/formacion/receta/2/edit/ingrediente/4
http://localhost:8080/formacion/receta/3/nuevo/ingrediente/
 -->

<c:if test="${!ingrediente.gluten}">Gluten Free</c:if>
<c:if test="${ingrediente.gluten}">*Atención contiene Gluten</c:if>

<form:form 
           action="receta/${receta.id}/${accion}/ingrediente"  
           modelAttribute="ingrediente">

	
	<!-- Si hay ingredientes para seleccionar, estamos Creando un nuevo Ingrediente-->
	
	<c:if test="${disponibles != null}">
		<form:label path="id">Selecciona Ingrediente:</form:label>
		<form:select path="id">
		<c:forEach items="${disponibles}" var="i">
			<form:option value="${i.id}">${i.nombre}</form:option>
		</c:forEach>
		</form:select>
		<br>
	</c:if>
	
	
	<!-- Si no hay ingredientes para seleccionar, estamos modificando un Ingreiente existente -->
	<c:if test="${disponibles == null}">
		<form:hidden path="id"/>
	</c:if>
		
	<form:hidden path="nombre"/>
	<form:hidden path="gluten"/>
		
	<form:label path="cantidad">Cantidad:</form:label>
	<form:input path="cantidad"/>	
	<br>
	<br>
	<form:button>Modificar</form:button>

</form:form>


<%@ include file="../includes/footer.jsp" %>