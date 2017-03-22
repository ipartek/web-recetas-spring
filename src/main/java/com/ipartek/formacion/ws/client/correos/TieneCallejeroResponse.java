
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
 *         &lt;element name="TieneCallejeroResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "tieneCallejeroResult"
})
@XmlRootElement(name = "TieneCallejeroResponse")
public class TieneCallejeroResponse {

    @XmlElement(name = "TieneCallejeroResult")
    protected String tieneCallejeroResult;

    /**
     * Obtiene el valor de la propiedad tieneCallejeroResult.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTieneCallejeroResult() {
        return tieneCallejeroResult;
    }

    /**
     * Define el valor de la propiedad tieneCallejeroResult.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTieneCallejeroResult(String value) {
        this.tieneCallejeroResult = value;
    }

}
