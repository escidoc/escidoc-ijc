/**
 * ActionHandlerService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.escidoc.core.aa;

public interface ActionHandlerService extends javax.xml.rpc.Service {
    public java.lang.String getActionHandlerServiceAddress();

    public de.escidoc.core.aa.ActionHandler getActionHandlerService() throws javax.xml.rpc.ServiceException;

    public de.escidoc.core.aa.ActionHandler getActionHandlerService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}