/**
 * JhoveHandlerServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.escidoc.core.tme;

public class JhoveHandlerServiceLocator extends org.apache.axis.client.Service implements de.escidoc.core.tme.JhoveHandlerService {

    public JhoveHandlerServiceLocator() {
    }


    public JhoveHandlerServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public JhoveHandlerServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for JhoveHandlerService
    private java.lang.String JhoveHandlerService_address = "http://localhost:8080/axis/services/JhoveHandlerService";

    public java.lang.String getJhoveHandlerServiceAddress() {
        return JhoveHandlerService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String JhoveHandlerServiceWSDDServiceName = "JhoveHandlerService";

    public java.lang.String getJhoveHandlerServiceWSDDServiceName() {
        return JhoveHandlerServiceWSDDServiceName;
    }

    public void setJhoveHandlerServiceWSDDServiceName(java.lang.String name) {
        JhoveHandlerServiceWSDDServiceName = name;
    }

    public de.escidoc.core.tme.JhoveHandler getJhoveHandlerService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(JhoveHandlerService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getJhoveHandlerService(endpoint);
    }

    public de.escidoc.core.tme.JhoveHandler getJhoveHandlerService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            de.escidoc.core.tme.JhoveHandlerServiceSoapBindingStub _stub = new de.escidoc.core.tme.JhoveHandlerServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getJhoveHandlerServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setJhoveHandlerServiceEndpointAddress(java.lang.String address) {
        JhoveHandlerService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (de.escidoc.core.tme.JhoveHandler.class.isAssignableFrom(serviceEndpointInterface)) {
                de.escidoc.core.tme.JhoveHandlerServiceSoapBindingStub _stub = new de.escidoc.core.tme.JhoveHandlerServiceSoapBindingStub(new java.net.URL(JhoveHandlerService_address), this);
                _stub.setPortName(getJhoveHandlerServiceWSDDServiceName());
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
        if ("JhoveHandlerService".equals(inputPortName)) {
            return getJhoveHandlerService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.escidoc.de/services/JhoveHandlerService/0.1", "JhoveHandlerService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.escidoc.de/services/JhoveHandlerService/0.1", "JhoveHandlerService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("JhoveHandlerService".equals(portName)) {
            setJhoveHandlerServiceEndpointAddress(address);
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
