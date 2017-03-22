
package com.ipartek.formacion.ws.client.correos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="idioma" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigopostal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="buscaoficinas" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="maxnumoficinas" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="buscabuzones" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="maxnumbuzones" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "idioma",
    "codigopostal",
    "buscaoficinas",
    "maxnumoficinas",
    "buscabuzones",
    "maxnumbuzones"
})
@XmlRootElement(name = "BusquedaPorCodigoPostalSinFiltro")
public class BusquedaPorCodigoPostalSinFiltro {

    protected String idioma;
    protected String codigopostal;
    protected String buscaoficinas;
    protected String maxnumoficinas;
    protected String buscabuzones;
    protected String maxnumbuzones;

    /**
     * Obtiene el valor de la propiedad idioma.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdioma() {
        return idioma;
    }

    /**
     * Define el valor de la propiedad idioma.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdioma(String value) {
        this.idioma = value;
    }

    /**
     * Obtiene el valor de la propiedad codigopostal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigopostal() {
        return codigopostal;
    }

    /**
     * Define el valor de la propiedad codigopostal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigopostal(String value) {
        this.codigopostal = value;
    }

    /**
     * Obtiene el valor de la propiedad buscaoficinas.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuscaoficinas() {
        return buscaoficinas;
    }

    /**
     * Define el valor de la propiedad buscaoficinas.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuscaoficinas(String value) {
        this.buscaoficinas = value;
    }

    /**
     * Obtiene el valor de la propiedad maxnumoficinas.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaxnumoficinas() {
        return maxnumoficinas;
    }

    /**
     * Define el valor de la propiedad maxnumoficinas.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaxnumoficinas(String value) {
        this.maxnumoficinas = value;
    }

    /**
     * Obtiene el valor de la propiedad buscabuzones.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuscabuzones() {
        return buscabuzones;
    }

    /**
     * Define el valor de la propiedad buscabuzones.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuscabuzones(String value) {
        this.buscabuzones = value;
    }

    /**
     * Obtiene el valor de la propiedad maxnumbuzones.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaxnumbuzones() {
        return maxnumbuzones;
    }

    /**
     * Define el valor de la propiedad maxnumbuzones.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaxnumbuzones(String value) {
        this.maxnumbuzones = value;
    }

}
