@javax.xml.bind.annotation.XmlSchema(namespace = "OficinasBuzones", elementFormDefault = javax.xml.bind.annotation.XmlNsForm.QUALIFIED)


/**
 * Clase para conectarnos en servicios web
 * endpoint: http://www.correos.es/dinamic/Servicios-Web/OficinasBuzones/OficinasBuzones.asmx?WSDL <br>
 * 
 * Usaremos la herramienta de java <code>wsimport</code> para generar el cliente del WS<br>
 * 
 * wsimport -keep -p com.ipartek.formacion.ws.client.correos -verbose <url?WSDL>
 * 
 * @author Curso
 *@see https://www.ibm.com/support/knowledgecenter/es/SSAW57_8.5.5/com.ibm.websphere.nd.doc/ae/rwbs_wsimport.html
 */

package com.ipartek.formacion.ws.client.correos;