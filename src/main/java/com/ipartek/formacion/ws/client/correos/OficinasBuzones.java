
package com.ipartek.formacion.ws.client.correos;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "OficinasBuzones", targetNamespace = "OficinasBuzones", wsdlLocation = "http://www.correos.es/dinamic/Servicios-Web/OficinasBuzones/OficinasBuzones.asmx?WSDL")
public class OficinasBuzones
    extends Service
{

    private final static URL OFICINASBUZONES_WSDL_LOCATION;
    private final static WebServiceException OFICINASBUZONES_EXCEPTION;
    private final static QName OFICINASBUZONES_QNAME = new QName("OficinasBuzones", "OficinasBuzones");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://www.correos.es/dinamic/Servicios-Web/OficinasBuzones/OficinasBuzones.asmx?WSDL");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        OFICINASBUZONES_WSDL_LOCATION = url;
        OFICINASBUZONES_EXCEPTION = e;
    }

    public OficinasBuzones() {
        super(__getWsdlLocation(), OFICINASBUZONES_QNAME);
    }

    public OficinasBuzones(WebServiceFeature... features) {
        super(__getWsdlLocation(), OFICINASBUZONES_QNAME, features);
    }

    public OficinasBuzones(URL wsdlLocation) {
        super(wsdlLocation, OFICINASBUZONES_QNAME);
    }

    public OficinasBuzones(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, OFICINASBUZONES_QNAME, features);
    }

    public OficinasBuzones(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public OficinasBuzones(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns OficinasBuzonesSoap
     */
    @WebEndpoint(name = "OficinasBuzonesSoap")
    public OficinasBuzonesSoap getOficinasBuzonesSoap() {
        return super.getPort(new QName("OficinasBuzones", "OficinasBuzonesSoap"), OficinasBuzonesSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns OficinasBuzonesSoap
     */
    @WebEndpoint(name = "OficinasBuzonesSoap")
    public OficinasBuzonesSoap getOficinasBuzonesSoap(WebServiceFeature... features) {
        return super.getPort(new QName("OficinasBuzones", "OficinasBuzonesSoap"), OficinasBuzonesSoap.class, features);
    }

    private static URL __getWsdlLocation() {
        if (OFICINASBUZONES_EXCEPTION!= null) {
            throw OFICINASBUZONES_EXCEPTION;
        }
        return OFICINASBUZONES_WSDL_LOCATION;
    }

}
