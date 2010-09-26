/**
 * SemanticStoreHandlerServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.escidoc.core.om;

public class SemanticStoreHandlerServiceLocator extends org.apache.axis.client.Service implements de.escidoc.core.om.SemanticStoreHandlerService {

    public SemanticStoreHandlerServiceLocator() {
    }


    public SemanticStoreHandlerServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SemanticStoreHandlerServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SemanticStoreHandlerService
    private java.lang.String SemanticStoreHandlerService_address = "http://localhost:8080/axis/services/SemanticStoreHandlerService";

    public java.lang.String getSemanticStoreHandlerServiceAddress() {
        return SemanticStoreHandlerService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SemanticStoreHandlerServiceWSDDServiceName = "SemanticStoreHandlerService";

    public java.lang.String getSemanticStoreHandlerServiceWSDDServiceName() {
        return SemanticStoreHandlerServiceWSDDServiceName;
    }

    public void setSemanticStoreHandlerServiceWSDDServiceName(java.lang.String name) {
        SemanticStoreHandlerServiceWSDDServiceName = name;
    }

    public de.escidoc.core.om.SemanticStoreHandler getSemanticStoreHandlerService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SemanticStoreHandlerService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSemanticStoreHandlerService(endpoint);
    }

    public de.escidoc.core.om.SemanticStoreHandler getSemanticStoreHandlerService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            de.escidoc.core.om.SemanticStoreHandlerServiceSoapBindingStub _stub = new de.escidoc.core.om.SemanticStoreHandlerServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getSemanticStoreHandlerServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSemanticStoreHandlerServiceEndpointAddress(java.lang.String address) {
        SemanticStoreHandlerService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (de.escidoc.core.om.SemanticStoreHandler.class.isAssignableFrom(serviceEndpointInterface)) {
                de.escidoc.core.om.SemanticStoreHandlerServiceSoapBindingStub _stub = new de.escidoc.core.om.SemanticStoreHandlerServiceSoapBindingStub(new java.net.URL(SemanticStoreHandlerService_address), this);
                _stub.setPortName(getSemanticStoreHandlerServiceWSDDServiceName());
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
        if ("SemanticStoreHandlerService".equals(inputPortName)) {
            return getSemanticStoreHandlerService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.escidoc.de/services/SemanticStoreHandlerService/0.1", "SemanticStoreHandlerService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.escidoc.de/services/SemanticStoreHandlerService/0.1", "SemanticStoreHandlerService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SemanticStoreHandlerService".equals(portName)) {
            setSemanticStoreHandlerServiceEndpointAddress(address);
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
