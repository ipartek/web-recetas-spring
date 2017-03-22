
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
 *         &lt;element name="BuscarLasMasCercanasResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "buscarLasMasCercanasResult"
})
@XmlRootElement(name = "BuscarLasMasCercanasResponse")
public class BuscarLasMasCercanasResponse {

    @XmlElement(name = "BuscarLasMasCercanasResult")
    protected String buscarLasMasCercanasResult;

    /**
     * Obtiene el valor de la propiedad buscarLasMasCercanasResult.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuscarLasMasCercanasResult() {
        return buscarLasMasCercanasResult;
    }

    /**
     * Define el valor de la propiedad buscarLasMasCercanasResult.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuscarLasMasCercanasResult(String value) {
        this.buscarLasMasCercanasResult = value;
    }

}
