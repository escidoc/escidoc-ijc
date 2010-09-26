/**
 * ContentModelHandlerServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.escidoc.core.cmm;

public class ContentModelHandlerServiceLocator extends org.apache.axis.client.Service implements de.escidoc.core.cmm.ContentModelHandlerService {

    public ContentModelHandlerServiceLocator() {
    }


    public ContentModelHandlerServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ContentModelHandlerServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ContentModelHandlerService
    private java.lang.String ContentModelHandlerService_address = "http://localhost:8080/axis/services/ContentModelHandlerService";

    public java.lang.String getContentModelHandlerServiceAddress() {
        return ContentModelHandlerService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ContentModelHandlerServiceWSDDServiceName = "ContentModelHandlerService";

    public java.lang.String getContentModelHandlerServiceWSDDServiceName() {
        return ContentModelHandlerServiceWSDDServiceName;
    }

    public void setContentModelHandlerServiceWSDDServiceName(java.lang.String name) {
        ContentModelHandlerServiceWSDDServiceName = name;
    }

    public de.escidoc.core.cmm.ContentModelHandler getContentModelHandlerService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ContentModelHandlerService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getContentModelHandlerService(endpoint);
    }

    public de.escidoc.core.cmm.ContentModelHandler getContentModelHandlerService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            de.escidoc.core.cmm.ContentModelHandlerServiceSoapBindingStub _stub = new de.escidoc.core.cmm.ContentModelHandlerServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getContentModelHandlerServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setContentModelHandlerServiceEndpointAddress(java.lang.String address) {
        ContentModelHandlerService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (de.escidoc.core.cmm.ContentModelHandler.class.isAssignableFrom(serviceEndpointInterface)) {
                de.escidoc.core.cmm.ContentModelHandlerServiceSoapBindingStub _stub = new de.escidoc.core.cmm.ContentModelHandlerServiceSoapBindingStub(new java.net.URL(ContentModelHandlerService_address), this);
                _stub.setPortName(getContentModelHandlerServiceWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("ContentModelHandlerService".equals(inputPortName)) {
            return getContentModelHandlerService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.escidoc.de/services/ContentModelHandlerService/0.1", "ContentModelHandlerService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.escidoc.de/services/ContentModelHandlerService/0.1", "ContentModelHandlerService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ContentModelHandlerService".equals(portName)) {
            setContentModelHandlerServiceEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
