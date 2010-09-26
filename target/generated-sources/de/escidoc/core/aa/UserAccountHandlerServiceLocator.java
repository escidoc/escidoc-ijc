/**
 * UserAccountHandlerServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.escidoc.core.aa;

public class UserAccountHandlerServiceLocator extends org.apache.axis.client.Service implements de.escidoc.core.aa.UserAccountHandlerService {

    public UserAccountHandlerServiceLocator() {
    }


    public UserAccountHandlerServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public UserAccountHandlerServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for UserAccountHandlerService
    private java.lang.String UserAccountHandlerService_address = "http://localhost:8080/axis/services/UserAccountHandlerService";

    public java.lang.String getUserAccountHandlerServiceAddress() {
        return UserAccountHandlerService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String UserAccountHandlerServiceWSDDServiceName = "UserAccountHandlerService";

    public java.lang.String getUserAccountHandlerServiceWSDDServiceName() {
        return UserAccountHandlerServiceWSDDServiceName;
    }

    public void setUserAccountHandlerServiceWSDDServiceName(java.lang.String name) {
        UserAccountHandlerServiceWSDDServiceName = name;
    }

    public de.escidoc.core.aa.UserAccountHandler getUserAccountHandlerService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(UserAccountHandlerService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getUserAccountHandlerService(endpoint);
    }

    public de.escidoc.core.aa.UserAccountHandler getUserAccountHandlerService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            de.escidoc.core.aa.UserAccountHandlerServiceSoapBindingStub _stub = new de.escidoc.core.aa.UserAccountHandlerServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getUserAccountHandlerServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setUserAccountHandlerServiceEndpointAddress(java.lang.String address) {
        UserAccountHandlerService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (de.escidoc.core.aa.UserAccountHandler.class.isAssignableFrom(serviceEndpointInterface)) {
                de.escidoc.core.aa.UserAccountHandlerServiceSoapBindingStub _stub = new de.escidoc.core.aa.UserAccountHandlerServiceSoapBindingStub(new java.net.URL(UserAccountHandlerService_address), this);
                _stub.setPortName(getUserAccountHandlerServiceWSDDServiceName());
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
        if ("UserAccountHandlerService".equals(inputPortName)) {
            return getUserAccountHandlerService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.escidoc.de/services/UserAccountHandlerService/0.1", "UserAccountHandlerService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.escidoc.de/services/UserAccountHandlerService/0.1", "UserAccountHandlerService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("UserAccountHandlerService".equals(portName)) {
            setUserAccountHandlerServiceEndpointAddress(address);
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
