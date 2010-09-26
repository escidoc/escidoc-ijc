/**
 * ContentRelationHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.escidoc.core.om;

public interface ContentRelationHandler extends java.rmi.Remote {
    public java.lang.String lock(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException, de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException, de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException, de.escidoc.core.common.exceptions.remote.application.notfound.ContentRelationNotFoundException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException, de.escidoc.core.common.exceptions.remote.application.violated.LockingException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException;
    public void delete(java.lang.String in0) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException, de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException, de.escidoc.core.common.exceptions.remote.application.notfound.ContentRelationNotFoundException, de.escidoc.core.common.exceptions.remote.application.violated.LockingException;
    public java.lang.String release(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException, de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException, de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException, de.escidoc.core.common.exceptions.remote.application.notfound.ContentRelationNotFoundException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException, de.escidoc.core.common.exceptions.remote.application.violated.LockingException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException;
    public java.lang.String create(java.lang.String in0) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException, de.escidoc.core.common.exceptions.remote.application.missing.MissingAttributeValueException, de.escidoc.core.common.exceptions.remote.application.notfound.RelationPredicateNotFoundException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException, de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException, de.escidoc.core.common.exceptions.remote.application.notfound.ReferencedResourceNotFoundException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException;
    public java.lang.String unlock(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException, de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException, de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException, de.escidoc.core.common.exceptions.remote.application.notfound.ContentRelationNotFoundException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException, de.escidoc.core.common.exceptions.remote.application.violated.LockingException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException;
    public java.lang.String update(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException, de.escidoc.core.common.exceptions.remote.application.missing.MissingAttributeValueException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.notfound.RelationPredicateNotFoundException, de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException, de.escidoc.core.common.exceptions.remote.application.violated.LockingException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException, de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException, de.escidoc.core.common.exceptions.remote.application.notfound.ContentRelationNotFoundException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException, de.escidoc.core.common.exceptions.remote.application.notfound.ReferencedResourceNotFoundException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException;
    public java.lang.String retrieve(java.lang.String in0) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException, de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException, de.escidoc.core.common.exceptions.remote.application.notfound.ContentRelationNotFoundException;
    public java.lang.String submit(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException, de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException, de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException, de.escidoc.core.common.exceptions.remote.application.notfound.ContentRelationNotFoundException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException, de.escidoc.core.common.exceptions.remote.application.violated.LockingException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException;
    public java.lang.String retrieveProperties(java.lang.String in0) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException, de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException, de.escidoc.core.common.exceptions.remote.application.notfound.ContentRelationNotFoundException;
    public java.lang.String retrieveMdRecord(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException, de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException, de.escidoc.core.common.exceptions.remote.application.notfound.ContentRelationNotFoundException, de.escidoc.core.common.exceptions.remote.application.notfound.MdRecordNotFoundException;
    public java.lang.String retrieveMdRecords(java.lang.String in0) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException, de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException, de.escidoc.core.common.exceptions.remote.application.notfound.ContentRelationNotFoundException;
    public java.lang.String revise(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException, de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException, de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException, de.escidoc.core.common.exceptions.remote.application.notfound.ContentRelationNotFoundException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException, de.escidoc.core.common.exceptions.remote.application.violated.LockingException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException;
    public java.lang.String assignObjectPid(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException, de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException, de.escidoc.core.common.exceptions.remote.application.notfound.ContentRelationNotFoundException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException, de.escidoc.core.common.exceptions.remote.application.violated.LockingException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException, de.escidoc.core.common.exceptions.remote.application.violated.PidAlreadyAssignedException;
    public java.lang.String retrieveContentRelations(java.util.HashMap in0) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSearchQueryException;
    public java.lang.String retrieveRegisteredPredicates() throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException;
}
