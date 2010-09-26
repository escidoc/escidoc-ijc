/**
 * SRWSampleService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package gov.loc.www.zing.srw.service;

public interface SRWSampleService extends javax.xml.rpc.Service {
    public java.lang.String getSRWAddress();

    public gov.loc.www.zing.srw.service.SRWPort getSRW() throws javax.xml.rpc.ServiceException;

    public gov.loc.www.zing.srw.service.SRWPort getSRW(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
    public java.lang.String getExplainSOAPAddress();

    public gov.loc.www.zing.srw.service.ExplainPort getExplainSOAP() throws javax.xml.rpc.ServiceException;

    public gov.loc.www.zing.srw.service.ExplainPort getExplainSOAP(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
