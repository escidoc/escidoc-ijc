/**
 * ReportDefinitionHandlerServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.escidoc.core.sm;

public class ReportDefinitionHandlerServiceLocator extends org.apache.axis.client.Service implements de.escidoc.core.sm.ReportDefinitionHandlerService {

    public ReportDefinitionHandlerServiceLocator() {
    }


    public ReportDefinitionHandlerServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ReportDefinitionHandlerServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ReportDefinitionHandlerService
    private java.lang.String ReportDefinitionHandlerService_address = "http://localhost:8080/axis/services/ReportDefinitionHandlerService";

    public java.lang.String getReportDefinitionHandlerServiceAddress() {
        return ReportDefinitionHandlerService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ReportDefinitionHandlerServiceWSDDServiceName = "ReportDefinitionHandlerService";

    public java.lang.String getReportDefinitionHandlerServiceWSDDServiceName() {
        return ReportDefinitionHandlerServiceWSDDServiceName;
    }

    public void setReportDefinitionHandlerServiceWSDDServiceName(java.lang.String name) {
        ReportDefinitionHandlerServiceWSDDServiceName = name;
    }

    public de.escidoc.core.sm.ReportDefinitionHandler getReportDefinitionHandlerService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ReportDefinitionHandlerService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getReportDefinitionHandlerService(endpoint);
    }

    public de.escidoc.core.sm.ReportDefinitionHandler getReportDefinitionHandlerService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            de.escidoc.core.sm.ReportDefinitionHandlerServiceSoapBindingStub _stub = new de.escidoc.core.sm.ReportDefinitionHandlerServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getReportDefinitionHandlerServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setReportDefinitionHandlerServiceEndpointAddress(java.lang.String address) {
        ReportDefinitionHandlerService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (de.escidoc.core.sm.ReportDefinitionHandler.class.isAssignableFrom(serviceEndpointInterface)) {
                de.escidoc.core.sm.ReportDefinitionHandlerServiceSoapBindingStub _stub = new de.escidoc.core.sm.ReportDefinitionHandlerServiceSoapBindingStub(new java.net.URL(ReportDefinitionHandlerService_address), this);
                _stub.setPortName(getReportDefinitionHandlerServiceWSDDServiceName());
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
        if ("ReportDefinitionHandlerService".equals(inputPortName)) {
            return getReportDefinitionHandlerService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.escidoc.de/services/ReportDefinitionHandlerService/0.1", "ReportDefinitionHandlerService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.escidoc.de/services/ReportDefinitionHandlerService/0.1", "ReportDefinitionHandlerService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ReportDefinitionHandlerService".equals(portName)) {
            setReportDefinitionHandlerServiceEndpointAddress(address);
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
