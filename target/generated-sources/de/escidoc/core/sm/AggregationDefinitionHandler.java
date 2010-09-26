/**
 * AggregationDefinitionHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.escidoc.core.sm;

public interface AggregationDefinitionHandler extends java.rmi.Remote {
    public void delete(java.lang.String in0) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException, de.escidoc.core.common.exceptions.remote.application.notfound.AggregationDefinitionNotFoundException, de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException;
    public java.lang.String create(java.lang.String in0) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.application.invalid.XmlSchemaValidationException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.invalid.XmlCorruptedException, de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException, de.escidoc.core.common.exceptions.remote.application.notfound.ScopeNotFoundException, de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException;
    public java.lang.String retrieve(java.lang.String in0) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException, de.escidoc.core.common.exceptions.remote.application.notfound.AggregationDefinitionNotFoundException, de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException;
    public java.lang.String retrieveAggregationDefinitions(java.lang.String in0) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException, de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException;
    public java.lang.String retrieveAggregationDefinitions(java.util.HashMap in0) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSearchQueryException, de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException, de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException;
}
