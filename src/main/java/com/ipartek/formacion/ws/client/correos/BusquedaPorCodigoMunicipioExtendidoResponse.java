
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
 *         &lt;element name="BusquedaPorCodigoMunicipioExtendidoResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "busquedaPorCodigoMunicipioExtendidoResult"
})
@XmlRootElement(name = "BusquedaPorCodigoMunicipioExtendidoResponse")
public class BusquedaPorCodigoMunicipioExtendidoResponse {

    @XmlElement(name = "BusquedaPorCodigoMunicipioExtendidoResult")
    protected String busquedaPorCodigoMunicipioExtendidoResult;

    /**
     * Obtiene el valor de la propiedad busquedaPorCodigoMunicipioExtendidoResult.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusquedaPorCodigoMunicipioExtendidoResult() {
        return busquedaPorCodigoMunicipioExtendidoResult;
    }

    /**
     * Define el valor de la propiedad busquedaPorCodigoMunicipioExtendidoResult.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusquedaPorCodigoMunicipioExtendidoResult(String value) {
        this.busquedaPorCodigoMunicipioExtendidoResult = value;
    }

}
