#Web Recetas App

Proyecto Web 2.5 desarrollado con Java6 y Spring 3.
Multidioma para Castellano, Euskera e Ingles.

![Alt text](documentacion/screenshot.png?raw=true 'pantallazo de listado recetas')

## Requisitos:
	Es necesario tener instalado el siguiente entorno para poder ejecutar la App:
	-Java JDK 6 o superior
	-SGBD: MySQL 5.0.8 o superior
	-Servidor Aplicaciones Tomcat6 o superior

## instalación:


	-Importar script de la carpeta "deploy/install.sql"
	-Desplegar "deploy/web-recetas-app1.0.war" en Tomcat
	-Acceder mediante navegador a url:  "http://localhost:8080/formacion"

	
	*Si se desea cambiar las credenciales de la base de datos, modificar el fichero: "src\main\resources\database.properties" y volver a generar el WAR
	