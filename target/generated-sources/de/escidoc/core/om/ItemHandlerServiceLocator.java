/**
 * ItemHandlerServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.escidoc.core.om;

public class ItemHandlerServiceLocator extends org.apache.axis.client.Service implements de.escidoc.core.om.ItemHandlerService {

    public ItemHandlerServiceLocator() {
    }


    public ItemHandlerServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ItemHandlerServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ItemHandlerService
    private java.lang.String ItemHandlerService_address = "http://localhost:8080/axis/services/ItemHandlerService";

    public java.lang.String getItemHandlerServiceAddress() {
        return ItemHandlerService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ItemHandlerServiceWSDDServiceName = "ItemHandlerService";

    public java.lang.String getItemHandlerServiceWSDDServiceName() {
        return ItemHandlerServiceWSDDServiceName;
    }

    public void setItemHandlerServiceWSDDServiceName(java.lang.String name) {
        ItemHandlerServiceWSDDServiceName = name;
    }

    public de.escidoc.core.om.ItemHandler getItemHandlerService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ItemHandlerService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getItemHandlerService(endpoint);
    }

    public de.escidoc.core.om.ItemHandler getItemHandlerService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            de.escidoc.core.om.ItemHandlerServiceSoapBindingStub _stub = new de.escidoc.core.om.ItemHandlerServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getItemHandlerServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setItemHandlerServiceEndpointAddress(java.lang.String address) {
        ItemHandlerService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (de.escidoc.core.om.ItemHandler.class.isAssignableFrom(serviceEndpointInterface)) {
                de.escidoc.core.om.ItemHandlerServiceSoapBindingStub _stub = new de.escidoc.core.om.ItemHandlerServiceSoapBindingStub(new java.net.URL(ItemHandlerService_address), this);
                _stub.setPortName(getItemHandlerServiceWSDDServiceName());
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
        if ("ItemHandlerService".equals(inputPortName)) {
            return getItemHandlerService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.escidoc.de/services/ItemHandlerService/0.1", "ItemHandlerService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.escidoc.de/services/ItemHandlerService/0.1", "ItemHandlerService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ItemHandlerService".equals(portName)) {
            setItemHandlerServiceEndpointAddress(address);
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
