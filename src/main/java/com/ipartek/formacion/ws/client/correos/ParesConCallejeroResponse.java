
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
 *         &lt;element name="ParesConCallejeroResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "paresConCallejeroResult"
})
@XmlRootElement(name = "ParesConCallejeroResponse")
public class ParesConCallejeroResponse {

    @XmlElement(name = "ParesConCallejeroResult")
    protected String paresConCallejeroResult;

    /**
     * Obtiene el valor de la propiedad paresConCallejeroResult.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParesConCallejeroResult() {
        return paresConCallejeroResult;
    }

    /**
     * Define el valor de la propiedad paresConCallejeroResult.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParesConCallejeroResult(String value) {
        this.paresConCallejeroResult = value;
    }

}
