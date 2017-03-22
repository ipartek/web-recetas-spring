
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
 *         &lt;element name="maxresultados" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="provincia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="poblacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nomcalle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idioma" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoBusqueda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="oficinas" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="maxOficinas" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="maxBuzones" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="buzones" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="servicios" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fecha" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hora" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "maxresultados",
    "provincia",
    "poblacion",
    "nomcalle",
    "idioma",
    "tipoBusqueda",
    "oficinas",
    "maxOficinas",
    "maxBuzones",
    "buzones",
    "servicios",
    "fecha",
    "hora",
    "numerocalle"
})
@XmlRootElement(name = "DelimitaLocalidadesOficinasEntrega")
public class DelimitaLocalidadesOficinasEntrega {

    protected String maxresultados;
    protected String provincia;
    protected String poblacion;
    protected String nomcalle;
    protected String idioma;
    @XmlElement(name = "TipoBusqueda")
    protected String tipoBusqueda;
    protected String oficinas;
    protected String maxOficinas;
    protected String maxBuzones;
    protected String buzones;
    protected String servicios;
    protected String fecha;
    protected String hora;
    protected String numerocalle;

    /**
     * Obtiene el valor de la propiedad maxresultados.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaxresultados() {
        return maxresultados;
    }

    /**
     * Define el valor de la propiedad maxresultados.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaxresultados(String value) {
        this.maxresultados = value;
    }

    /**
     * Obtiene el valor de la propiedad provincia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * Define el valor de la propiedad provincia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvincia(String value) {
        this.provincia = value;
    }

    /**
     * Obtiene el valor de la propiedad poblacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPoblacion() {
        return poblacion;
    }

    /**
     * Define el valor de la propiedad poblacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPoblacion(String value) {
        this.poblacion = value;
    }

    /**
     * Obtiene el valor de la propiedad nomcalle.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomcalle() {
        return nomcalle;
    }

    /**
     * Define el valor de la propiedad nomcalle.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomcalle(String value) {
        this.nomcalle = value;
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
     * Obtiene el valor de la propiedad tipoBusqueda.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoBusqueda() {
        return tipoBusqueda;
    }

    /**
     * Define el valor de la propiedad tipoBusqueda.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoBusqueda(String value) {
        this.tipoBusqueda = value;
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
     * Obtiene el valor de la propiedad maxOficinas.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaxOficinas() {
        return maxOficinas;
    }

    /**
     * Define el valor de la propiedad maxOficinas.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaxOficinas(String value) {
        this.maxOficinas = value;
    }

    /**
     * Obtiene el valor de la propiedad maxBuzones.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaxBuzones() {
        return maxBuzones;
    }

    /**
     * Define el valor de la propiedad maxBuzones.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaxBuzones(String value) {
        this.maxBuzones = value;
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
