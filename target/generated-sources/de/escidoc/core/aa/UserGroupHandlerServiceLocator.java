/**
 * UserGroupHandlerServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.escidoc.core.aa;

public class UserGroupHandlerServiceLocator extends org.apache.axis.client.Service implements de.escidoc.core.aa.UserGroupHandlerService {

    public UserGroupHandlerServiceLocator() {
    }


    public UserGroupHandlerServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public UserGroupHandlerServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for UserGroupHandlerService
    private java.lang.String UserGroupHandlerService_address = "http://localhost:8080/axis/services/UserGroupHandlerService";

    public java.lang.String getUserGroupHandlerServiceAddress() {
        return UserGroupHandlerService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String UserGroupHandlerServiceWSDDServiceName = "UserGroupHandlerService";

    public java.lang.String getUserGroupHandlerServiceWSDDServiceName() {
        return UserGroupHandlerServiceWSDDServiceName;
    }

    public void setUserGroupHandlerServiceWSDDServiceName(java.lang.String name) {
        UserGroupHandlerServiceWSDDServiceName = name;
    }

    public de.escidoc.core.aa.UserGroupHandler getUserGroupHandlerService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(UserGroupHandlerService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getUserGroupHandlerService(endpoint);
    }

    public de.escidoc.core.aa.UserGroupHandler getUserGroupHandlerService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            de.escidoc.core.aa.UserGroupHandlerServiceSoapBindingStub _stub = new de.escidoc.core.aa.UserGroupHandlerServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getUserGroupHandlerServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setUserGroupHandlerServiceEndpointAddress(java.lang.String address) {
        UserGroupHandlerService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (de.escidoc.core.aa.UserGroupHandler.class.isAssignableFrom(serviceEndpointInterface)) {
                de.escidoc.core.aa.UserGroupHandlerServiceSoapBindingStub _stub = new de.escidoc.core.aa.UserGroupHandlerServiceSoapBindingStub(new java.net.URL(UserGroupHandlerService_address), this);
                _stub.setPortName(getUserGroupHandlerServiceWSDDServiceName());
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
        if ("UserGroupHandlerService".equals(inputPortName)) {
            return getUserGroupHandlerService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.escidoc.de/services/UserGroupHandlerService/0.1", "UserGroupHandlerService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.escidoc.de/services/UserGroupHandlerService/0.1", "UserGroupHandlerService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("UserGroupHandlerService".equals(portName)) {
            setUserGroupHandlerServiceEndpointAddress(address);
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
