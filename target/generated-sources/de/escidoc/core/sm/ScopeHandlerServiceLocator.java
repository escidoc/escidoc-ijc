/**
 * ScopeHandlerServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.escidoc.core.sm;

public class ScopeHandlerServiceLocator extends org.apache.axis.client.Service implements de.escidoc.core.sm.ScopeHandlerService {

    public ScopeHandlerServiceLocator() {
    }


    public ScopeHandlerServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ScopeHandlerServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ScopeHandlerService
    private java.lang.String ScopeHandlerService_address = "http://localhost:8080/axis/services/ScopeHandlerService";

    public java.lang.String getScopeHandlerServiceAddress() {
        return ScopeHandlerService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ScopeHandlerServiceWSDDServiceName = "ScopeHandlerService";

    public java.lang.String getScopeHandlerServiceWSDDServiceName() {
        return ScopeHandlerServiceWSDDServiceName;
    }

    public void setScopeHandlerServiceWSDDServiceName(java.lang.String name) {
        ScopeHandlerServiceWSDDServiceName = name;
    }

    public de.escidoc.core.sm.ScopeHandler getScopeHandlerService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ScopeHandlerService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getScopeHandlerService(endpoint);
    }

    public de.escidoc.core.sm.ScopeHandler getScopeHandlerService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            de.escidoc.core.sm.ScopeHandlerServiceSoapBindingStub _stub = new de.escidoc.core.sm.ScopeHandlerServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getScopeHandlerServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setScopeHandlerServiceEndpointAddress(java.lang.String address) {
        ScopeHandlerService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (de.escidoc.core.sm.ScopeHandler.class.isAssignableFrom(serviceEndpointInterface)) {
                de.escidoc.core.sm.ScopeHandlerServiceSoapBindingStub _stub = new de.escidoc.core.sm.ScopeHandlerServiceSoapBindingStub(new java.net.URL(ScopeHandlerService_address), this);
                _stub.setPortName(getScopeHandlerServiceWSDDServiceName());
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
        if ("ScopeHandlerService".equals(inputPortName)) {
            return getScopeHandlerService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.escidoc.de/services/ScopeHandlerService/0.1", "ScopeHandlerService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.escidoc.de/services/ScopeHandlerService/0.1", "ScopeHandlerService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ScopeHandlerService".equals(portName)) {
            setScopeHandlerServiceEndpointAddress(address);
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
