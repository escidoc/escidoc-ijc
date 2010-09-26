/**
 * RoleHandlerServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.escidoc.core.aa;

public class RoleHandlerServiceLocator extends org.apache.axis.client.Service implements de.escidoc.core.aa.RoleHandlerService {

    public RoleHandlerServiceLocator() {
    }


    public RoleHandlerServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public RoleHandlerServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for RoleHandlerService
    private java.lang.String RoleHandlerService_address = "http://localhost:8080/axis/services/RoleHandlerService";

    public java.lang.String getRoleHandlerServiceAddress() {
        return RoleHandlerService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String RoleHandlerServiceWSDDServiceName = "RoleHandlerService";

    public java.lang.String getRoleHandlerServiceWSDDServiceName() {
        return RoleHandlerServiceWSDDServiceName;
    }

    public void setRoleHandlerServiceWSDDServiceName(java.lang.String name) {
        RoleHandlerServiceWSDDServiceName = name;
    }

    public de.escidoc.core.aa.RoleHandler getRoleHandlerService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(RoleHandlerService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getRoleHandlerService(endpoint);
    }

    public de.escidoc.core.aa.RoleHandler getRoleHandlerService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            de.escidoc.core.aa.RoleHandlerServiceSoapBindingStub _stub = new de.escidoc.core.aa.RoleHandlerServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getRoleHandlerServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setRoleHandlerServiceEndpointAddress(java.lang.String address) {
        RoleHandlerService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (de.escidoc.core.aa.RoleHandler.class.isAssignableFrom(serviceEndpointInterface)) {
                de.escidoc.core.aa.RoleHandlerServiceSoapBindingStub _stub = new de.escidoc.core.aa.RoleHandlerServiceSoapBindingStub(new java.net.URL(RoleHandlerService_address), this);
                _stub.setPortName(getRoleHandlerServiceWSDDServiceName());
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
        if ("RoleHandlerService".equals(inputPortName)) {
            return getRoleHandlerService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.escidoc.de/services/RoleHandlerService/0.1", "RoleHandlerService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.escidoc.de/services/RoleHandlerService/0.1", "RoleHandlerService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("RoleHandlerService".equals(portName)) {
            setRoleHandlerServiceEndpointAddress(address);
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
