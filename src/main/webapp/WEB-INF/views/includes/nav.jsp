
<div class="container">

	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Web Recetas</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li class="">
						<a href="usuario">
							<spring:message code="nav.usuario" text="Usuarios"/>
						</a>
					</li>
					<li>
						<a href="receta">
							<spring:message code="nav.recetas" text="Recetas"/>
						</a>
					</li>
					<li>
						<a href="ingrediente">
							<spring:message code="nav.ingredientes" text="Ingredientes"/>
						</a>
					</li>
              
				</ul>
				
				<ul class="nav navbar-nav navbar-right">
					<li class="active">
						<a href="${requestScope['javax.servlet.forward.request_uri']}?locale=es">ES</a>
					</li>
					<li>
						<a href="${requestScope['javax.servlet.forward.request_uri']}?locale=eu">EU</a>
					</li>
					<li>
						<a href="${requestScope['javax.servlet.forward.request_uri']}?locale=en">EN</a>
					</li>
              
				</ul>
            
			</div><!--/.nav-collapse -->
		</div><!--/.container-fluid -->
	</nav>

      <!-- Main component for a primary marketing message or call to action -->
      <div class="jumbotron">
        
      


