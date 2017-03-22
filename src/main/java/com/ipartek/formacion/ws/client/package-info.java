/**
 * Clases Java para conectarnos al ServicioWeb de Correos.<br>
 * endpoint:
 * http://www.correos.es/dinamic/Servicios-Web/OficinasBuzones/OficinasBuzones.
 * asmx?WSDL
 * 
 * Usaremos la herramienta de java <code>wsimport</code> para generar el cliente
 * web con JAX-WS.<br>
 * 
 * wsimport -keep -p NOMBRE_PACKAGE -verbose <url?WSDL> Lanzar comando desde
 * MsDos desde la carpeta SRC/MAIN/JAVA
 * 
 * @author Curso
 * @see https://www.ibm.com/support/knowledgecenter/es/SSAW57_8.5.5/com.ibm.
 *      websphere.nd.doc/ae/rwbs_wsimport.html
 * 
 */

package com.ipartek.formacion.ws.client;