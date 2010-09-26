/**
 * SRWPort.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package gov.loc.www.zing.srw.service;

public interface SRWPort extends java.rmi.Remote {
    public gov.loc.www.zing.srw.SearchRetrieveResponseType searchRetrieveOperation(gov.loc.www.zing.srw.SearchRetrieveRequestType searchRetrieveRequest) throws java.rmi.RemoteException;
    public gov.loc.www.zing.srw.ScanResponseType scanOperation(gov.loc.www.zing.srw.ScanRequestType scanRequest) throws java.rmi.RemoteException;
}
