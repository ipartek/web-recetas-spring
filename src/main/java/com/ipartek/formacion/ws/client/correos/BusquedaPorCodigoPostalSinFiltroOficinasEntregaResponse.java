
package com.ipartek.formacion.ws.client.correos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BusquedaPorCodigoPostalSinFiltroOficinasEntregaResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "busquedaPorCodigoPostalSinFiltroOficinasEntregaResult"
})
@XmlRootElement(name = "BusquedaPorCodigoPostalSinFiltroOficinasEntregaResponse")
public class BusquedaPorCodigoPostalSinFiltroOficinasEntregaResponse {

    @XmlElement(name = "BusquedaPorCodigoPostalSinFiltroOficinasEntregaResult")
    protected String busquedaPorCodigoPostalSinFiltroOficinasEntregaResult;

    /**
     * Obtiene el valor de la propiedad busquedaPorCodigoPostalSinFiltroOficinasEntregaResult.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusquedaPorCodigoPostalSinFiltroOficinasEntregaResult() {
        return busquedaPorCodigoPostalSinFiltroOficinasEntregaResult;
    }

    /**
     * Define el valor de la propiedad busquedaPorCodigoPostalSinFiltroOficinasEntregaResult.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusquedaPorCodigoPostalSinFiltroOficinasEntregaResult(String value) {
        this.busquedaPorCodigoPostalSinFiltroOficinasEntregaResult = value;
    }

}
