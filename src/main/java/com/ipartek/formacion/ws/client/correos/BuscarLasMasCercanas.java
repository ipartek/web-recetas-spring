
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
 *         &lt;element name="numoficinas" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="oficinas" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numbuzones" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="buzones" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="servicios" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fecha" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hora" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idioma" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigoprovincia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigolocalidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigocalle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numerocalle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "numoficinas",
    "oficinas",
    "numbuzones",
    "buzones",
    "servicios",
    "fecha",
    "hora",
    "idioma",
    "codigoprovincia",
    "codigolocalidad",
    "codigocalle",
    "numerocalle"
})
@XmlRootElement(name = "BuscarLasMasCercanas")
public class BuscarLasMasCercanas {

    protected String numoficinas;
    protected String oficinas;
    protected String numbuzones;
    protected String buzones;
    protected String servicios;
    protected String fecha;
    protected String hora;
    protected String idioma;
    protected String codigoprovincia;
    protected String codigolocalidad;
    protected String codigocalle;
    protected String numerocalle;

    /**
     * Obtiene el valor de la propiedad numoficinas.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumoficinas() {
        return numoficinas;
    }

    /**
     * Define el valor de la propiedad numoficinas.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumoficinas(String value) {
        this.numoficinas = value;
    }

    /**
     * Obtiene el valor de la propiedad oficinas.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOficinas() {
        return oficinas;
    }

    /**
     * Define el valor de la propiedad oficinas.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOficinas(String value) {
        this.oficinas = value;
    }

    /**
     * Obtiene el valor de la propiedad numbuzones.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumbuzones() {
        return numbuzones;
    }

    /**
     * Define el valor de la propiedad numbuzones.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumbuzones(String value) {
        this.numbuzones = value;
    }

    /**
     * Obtiene el valor de la propiedad buzones.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuzones() {
        return buzones;
    }

    /**
     * Define el valor de la propiedad buzones.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuzones(String value) {
        this.buzones = value;
    }

    /**
     * Obtiene el valor de la propiedad servicios.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServicios() {
        return servicios;
    }

    /**
     * Define el valor de la propiedad servicios.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServicios(String value) {
        this.servicios = value;
    }

    /**
     * Obtiene el valor de la propiedad fecha.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Define el valor de la propiedad fecha.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFecha(String value) {
        this.fecha = value;
    }

    /**
     * Obtiene el valor de la propiedad hora.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHora() {
        return hora;
    }

    /**
     * Define el valor de la propiedad hora.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHora(String value) {
        this.hora = value;
    }

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
     * Obtiene el valor de la propiedad codigoprovincia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoprovincia() {
        return codigoprovincia;
    }

    /**
     * Define el valor de la propiedad codigoprovincia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoprovincia(String value) {
        this.codigoprovincia = value;
    }

    /**
     * Obtiene el valor de la propiedad codigolocalidad.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigolocalidad() {
        return codigolocalidad;
    }

    /**
     * Define el valor de la propiedad codigolocalidad.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigolocalidad(String value) {
        this.codigolocalidad = value;
    }

    /**
     * Obtiene el valor de la propiedad codigocalle.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigocalle() {
        return codigocalle;
    }

    /**
     * Define el valor de la propiedad codigocalle.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigocalle(String value) {
        this.codigocalle = value;
    }

    /**
     * Obtiene el valor de la propiedad numerocalle.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumerocalle() {
        return numerocalle;
    }

    /**
     * Define el valor de la propiedad numerocalle.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumerocalle(String value) {
        this.numerocalle = value;
    }

}
