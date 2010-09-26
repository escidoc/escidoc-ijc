/**
 * AdminHandlerService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.escidoc.core.adm;

public interface AdminHandlerService extends javax.xml.rpc.Service {
    public java.lang.String getAdminHandlerServiceAddress();

    public de.escidoc.core.adm.AdminHandler getAdminHandlerService() throws javax.xml.rpc.ServiceException;

    public de.escidoc.core.adm.AdminHandler getAdminHandlerService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
