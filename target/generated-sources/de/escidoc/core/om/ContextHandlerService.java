/**
 * ContextHandlerService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.escidoc.core.om;

public interface ContextHandlerService extends javax.xml.rpc.Service {
    public java.lang.String getContextHandlerServiceAddress();

    public de.escidoc.core.om.ContextHandler getContextHandlerService() throws javax.xml.rpc.ServiceException;

    public de.escidoc.core.om.ContextHandler getContextHandlerService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
