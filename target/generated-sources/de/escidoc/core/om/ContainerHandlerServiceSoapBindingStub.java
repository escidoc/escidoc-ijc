/**
 * ContainerHandlerServiceSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.escidoc.core.om;

public class ContainerHandlerServiceSoapBindingStub extends org.apache.axis.client.Stub implements de.escidoc.core.om.ContainerHandler {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[32];
        _initOperationDesc1();
        _initOperationDesc2();
        _initOperationDesc3();
        _initOperationDesc4();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("lock");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "lockReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.SystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "OptimisticLockingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthorizationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidStatusException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthenticationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidXmlException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.LockingException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "LockingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ContainerNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingMethodParameterException"), 
                      true
                     ));
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("delete");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.SystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthorizationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidStatusException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthenticationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.LockingException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "LockingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ContainerNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingMethodParameterException"), 
                      true
                     ));
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("release");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "releaseReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.SystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "OptimisticLockingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthorizationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidStatusException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthenticationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyVersionException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "ReadonlyVersionException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidXmlException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.LockingException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "LockingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ContainerNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingMethodParameterException"), 
                      true
                     ));
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("create");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "createReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingAttributeValueException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingAttributeValueException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.SystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.RelationPredicateNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "RelationPredicateNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthorizationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidStatusException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingElementValueException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingElementValueException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidContentException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ContentModelNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ContentModelNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthenticationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingMdRecordException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingMdRecordException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidXmlException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ReferencedResourceNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ReferencedResourceNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ContextNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ContextNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingMethodParameterException"), 
                      true
                     ));
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("unlock");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "unlockReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.SystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "OptimisticLockingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthorizationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidStatusException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthenticationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidXmlException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.LockingException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "LockingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ContainerNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingMethodParameterException"), 
                      true
                     ));
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("update");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "updateReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingAttributeValueException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingAttributeValueException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "OptimisticLockingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.SystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.RelationPredicateNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "RelationPredicateNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthorizationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidStatusException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.LockingException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "LockingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidContentException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthenticationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyVersionException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "ReadonlyVersionException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingMdRecordException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingMdRecordException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidXmlException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ReferencedResourceNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ReferencedResourceNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingMethodParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ContainerNotFoundException"), 
                      true
                     ));
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("retrieve");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "retrieveReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.SystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthorizationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthenticationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingMethodParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ContainerNotFoundException"), 
                      true
                     ));
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("submit");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "submitReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.SystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "OptimisticLockingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthorizationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidStatusException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthenticationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyVersionException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "ReadonlyVersionException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidXmlException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.LockingException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "LockingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ContainerNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingMethodParameterException"), 
                      true
                     ));
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("createContainer");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "createContainerReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.SystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingAttributeValueException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingAttributeValueException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.RelationPredicateNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "RelationPredicateNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthorizationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidStatusException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContextException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidContextException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingElementValueException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingElementValueException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.LockingException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "LockingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidContentException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ContentModelNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ContentModelNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthenticationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingMdRecordException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingMdRecordException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidXmlException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ReferencedResourceNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ReferencedResourceNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ContextNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ContextNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ContainerNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingMethodParameterException"), 
                      true
                     ));
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("retrieveProperties");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "retrievePropertiesReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.SystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthorizationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthenticationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ContainerNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingMethodParameterException"), 
                      true
                     ));
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("retrieveVersionHistory");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "retrieveVersionHistoryReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.SystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthorizationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthenticationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ContainerNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingMethodParameterException"), 
                      true
                     ));
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("retrieveMembers");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "retrieveMembersReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.SystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSearchQueryException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidSearchQueryException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidXmlException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingMethodParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ContainerNotFoundException"), 
                      true
                     ));
        _operations[11] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("retrieveMembers");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://xml.apache.org/xml-soap", "Map"), java.util.HashMap.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "retrieveMembersReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.SystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSearchQueryException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidSearchQueryException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidXmlException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingMethodParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ContainerNotFoundException"), 
                      true
                     ));
        _operations[12] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("retrieveTocs");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "retrieveTocsReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.SystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSearchQueryException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidSearchQueryException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidXmlException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingMethodParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ContainerNotFoundException"), 
                      true
                     ));
        _operations[13] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("retrieveTocs");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://xml.apache.org/xml-soap", "Map"), java.util.HashMap.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "retrieveTocsReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.SystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSearchQueryException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidSearchQueryException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidXmlException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingMethodParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ContainerNotFoundException"), 
                      true
                     ));
        _operations[14] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("addMembers");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "addMembersReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidContentException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.SystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "OptimisticLockingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingAttributeValueException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingAttributeValueException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthorizationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidStatusException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContextException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidContextException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthenticationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.LockingException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "LockingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ContainerNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingMethodParameterException"), 
                      true
                     ));
        _operations[15] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("addTocs");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "addTocsReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidContentException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.SystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "OptimisticLockingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingAttributeValueException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingAttributeValueException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthorizationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidStatusException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContextException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidContextException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthenticationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.LockingException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "LockingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ContainerNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingMethodParameterException"), 
                      true
                     ));
        _operations[16] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("removeMembers");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "removeMembersReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidContentException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "OptimisticLockingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.SystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingAttributeValueException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingAttributeValueException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthorizationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidStatusException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthenticationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.LockingException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "LockingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ContainerNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingMethodParameterException"), 
                      true
                     ));
        _operations[17] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("retrieveMdRecord");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "retrieveMdRecordReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.SystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthorizationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthenticationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.MdRecordNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "MdRecordNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ContainerNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingMethodParameterException"), 
                      true
                     ));
        _operations[18] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("retrieveMdRecords");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "retrieveMdRecordsReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.SystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthorizationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthenticationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ContainerNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingMethodParameterException"), 
                      true
                     ));
        _operations[19] = oper;

    }

    private static void _initOperationDesc3(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("retrieveStructMap");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "retrieveStructMapReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.SystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthorizationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthenticationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ContainerNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingMethodParameterException"), 
                      true
                     ));
        _operations[20] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("retrieveRelations");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "retrieveRelationsReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.SystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthorizationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthenticationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ContainerNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingMethodParameterException"), 
                      true
                     ));
        _operations[21] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("withdraw");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "withdrawReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.SystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "OptimisticLockingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthorizationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidStatusException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthenticationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyVersionException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "ReadonlyVersionException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidXmlException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.LockingException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "LockingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ContainerNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingMethodParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.AlreadyWithdrawnException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "AlreadyWithdrawnException"), 
                      true
                     ));
        _operations[22] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("revise");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "reviseReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.SystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "OptimisticLockingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidStatusException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyVersionException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "ReadonlyVersionException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidXmlException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.LockingException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "LockingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ContainerNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingMethodParameterException"), 
                      true
                     ));
        _operations[23] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("moveToContext");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "moveToContextReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidContentException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.SystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthorizationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthenticationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ContextNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ContextNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.LockingException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "LockingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ContainerNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingMethodParameterException"), 
                      true
                     ));
        _operations[24] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("createItem");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "createItemReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.SystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.RelationPredicateNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "RelationPredicateNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingAttributeValueException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingAttributeValueException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthorizationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidStatusException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContextException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidContextException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingElementValueException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingElementValueException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyElementViolationException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "ReadonlyElementViolationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.LockingException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "LockingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidContentException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ContentModelNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ContentModelNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthenticationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.FileNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "FileNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingContentException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingContentException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyAttributeViolationException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "ReadonlyAttributeViolationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingMdRecordException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingMdRecordException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidXmlException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ReferencedResourceNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ReferencedResourceNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ContextNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ContextNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingMethodParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ContainerNotFoundException"), 
                      true
                     ));
        _operations[25] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("retrieveContainers");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "retrieveContainersReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.SystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSearchQueryException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidSearchQueryException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidXmlException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingMethodParameterException"), 
                      true
                     ));
        _operations[26] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("retrieveContainers");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://xml.apache.org/xml-soap", "Map"), java.util.HashMap.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "retrieveContainersReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.SystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSearchQueryException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidSearchQueryException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidXmlException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingMethodParameterException"), 
                      true
                     ));
        _operations[27] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("addContentRelations");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "addContentRelationsReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.SystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "OptimisticLockingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.RelationPredicateNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "RelationPredicateNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthorizationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidStatusException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingElementValueException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingElementValueException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.LockingException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "LockingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidContentException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthenticationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.AlreadyExistsException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "AlreadyExistsException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyVersionException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "ReadonlyVersionException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ReferencedResourceNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ReferencedResourceNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidXmlException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ContainerNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingMethodParameterException"), 
                      true
                     ));
        _operations[28] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("removeContentRelations");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "removeContentRelationsReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.SystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "OptimisticLockingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthorizationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidStatusException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthenticationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyVersionException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "ReadonlyVersionException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingElementValueException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingElementValueException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ContentRelationNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ContentRelationNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidXmlException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.LockingException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "LockingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ContainerNotFoundException"), 
                      true
                     ));
        _operations[29] = oper;

    }

    private static void _initOperationDesc4(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("assignObjectPid");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "assignObjectPidReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "OptimisticLockingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.SystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidStatusException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidXmlException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.LockingException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "LockingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ContainerNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingMethodParameterException"), 
                      true
                     ));
        _operations[30] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("assignVersionPid");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "assignVersionPidReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.SystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "OptimisticLockingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidStatusException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyVersionException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "ReadonlyVersionException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidXmlException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.LockingException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "LockingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ContainerNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingMethodParameterException"), 
                      true
                     ));
        _operations[31] = oper;

    }

    public ContainerHandlerServiceSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public ContainerHandlerServiceSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public ContainerHandlerServiceSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.1");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://application.exceptions.common.core.escidoc.de", "ApplicationException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.ApplicationException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://exceptions.common.core.escidoc.de", "EscidocException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.EscidocException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidContentException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidContextException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContextException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidSearchQueryException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSearchQueryException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidStatusException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidXmlException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "ValidationException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.invalid.ValidationException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "XmlCorruptedException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.invalid.XmlCorruptedException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "XmlSchemaValidationException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.invalid.XmlSchemaValidationException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingAttributeValueException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.missing.MissingAttributeValueException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingContentException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.missing.MissingContentException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingElementValueException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.missing.MissingElementValueException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingMdRecordException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.missing.MissingMdRecordException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingMethodParameterException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingParameterException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.missing.MissingParameterException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ContainerNotFoundException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ContentModelNotFoundException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.notfound.ContentModelNotFoundException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ContentRelationNotFoundException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.notfound.ContentRelationNotFoundException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ContextNotFoundException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.notfound.ContextNotFoundException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "FileNotFoundException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.notfound.FileNotFoundException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "MdRecordNotFoundException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.notfound.MdRecordNotFoundException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ReferencedResourceNotFoundException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.notfound.ReferencedResourceNotFoundException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "RelationPredicateNotFoundException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.notfound.RelationPredicateNotFoundException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ResourceNotFoundException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.notfound.ResourceNotFoundException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthenticationException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthorizationException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "SecurityException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.security.SecurityException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "ApplicationServerSystemException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.system.ApplicationServerSystemException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "FedoraSystemException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.system.FedoraSystemException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "FileSystemException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.system.FileSystemException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "IntegritySystemException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.system.IntegritySystemException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "SqlDatabaseSystemException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.system.SqlDatabaseSystemException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "StatisticPreprocessingSystemException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.system.StatisticPreprocessingSystemException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "SystemException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.system.SystemException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "TripleStoreSystemException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.system.TripleStoreSystemException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "WebserverSystemException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.system.WebserverSystemException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "WorkflowEngineSystemException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.system.WorkflowEngineSystemException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "XmlParserSystemException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.system.XmlParserSystemException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "AlreadyExistsException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.violated.AlreadyExistsException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "AlreadyWithdrawnException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.violated.AlreadyWithdrawnException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "LockingException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.violated.LockingException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "OptimisticLockingException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "ReadonlyAttributeViolationException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyAttributeViolationException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "ReadonlyElementViolationException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyElementViolationException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "ReadonlyVersionException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyVersionException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "ReadonlyViolationException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyViolationException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "RuleViolationException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.violated.RuleViolationException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
                    _call.setEncodingStyle(org.apache.axis.Constants.URI_SOAP11_ENC);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public java.lang.String lock(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException, de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException, de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException, de.escidoc.core.common.exceptions.remote.application.violated.LockingException, de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "lock"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0, in1});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.system.SystemException) {
              throw (de.escidoc.core.common.exceptions.remote.system.SystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.LockingException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.LockingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void delete(java.lang.String in0) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException, de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException, de.escidoc.core.common.exceptions.remote.application.violated.LockingException, de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "delete"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.system.SystemException) {
              throw (de.escidoc.core.common.exceptions.remote.system.SystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.LockingException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.LockingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String release(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException, de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException, de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException, de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyVersionException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException, de.escidoc.core.common.exceptions.remote.application.violated.LockingException, de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "release"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0, in1});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.system.SystemException) {
              throw (de.escidoc.core.common.exceptions.remote.system.SystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyVersionException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyVersionException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.LockingException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.LockingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String create(java.lang.String in0) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.application.missing.MissingAttributeValueException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.notfound.RelationPredicateNotFoundException, de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException, de.escidoc.core.common.exceptions.remote.application.missing.MissingElementValueException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException, de.escidoc.core.common.exceptions.remote.application.notfound.ContentModelNotFoundException, de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMdRecordException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException, de.escidoc.core.common.exceptions.remote.application.notfound.ReferencedResourceNotFoundException, de.escidoc.core.common.exceptions.remote.application.notfound.ContextNotFoundException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "create"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingAttributeValueException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingAttributeValueException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.system.SystemException) {
              throw (de.escidoc.core.common.exceptions.remote.system.SystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.RelationPredicateNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.RelationPredicateNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingElementValueException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingElementValueException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.ContentModelNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.ContentModelNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingMdRecordException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingMdRecordException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.ReferencedResourceNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.ReferencedResourceNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.ContextNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.ContextNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String unlock(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException, de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException, de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException, de.escidoc.core.common.exceptions.remote.application.violated.LockingException, de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "unlock"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0, in1});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.system.SystemException) {
              throw (de.escidoc.core.common.exceptions.remote.system.SystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.LockingException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.LockingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String update(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.application.missing.MissingAttributeValueException, de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.notfound.RelationPredicateNotFoundException, de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException, de.escidoc.core.common.exceptions.remote.application.violated.LockingException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException, de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException, de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyVersionException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMdRecordException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException, de.escidoc.core.common.exceptions.remote.application.notfound.ReferencedResourceNotFoundException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException, de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "update"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0, in1});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingAttributeValueException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingAttributeValueException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.system.SystemException) {
              throw (de.escidoc.core.common.exceptions.remote.system.SystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.RelationPredicateNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.RelationPredicateNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.LockingException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.LockingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyVersionException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyVersionException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingMdRecordException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingMdRecordException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.ReferencedResourceNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.ReferencedResourceNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String retrieve(java.lang.String in0) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException, de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException, de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "retrieve"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.system.SystemException) {
              throw (de.escidoc.core.common.exceptions.remote.system.SystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String submit(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException, de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException, de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException, de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyVersionException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException, de.escidoc.core.common.exceptions.remote.application.violated.LockingException, de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "submit"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0, in1});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.system.SystemException) {
              throw (de.escidoc.core.common.exceptions.remote.system.SystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyVersionException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyVersionException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.LockingException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.LockingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String createContainer(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.missing.MissingAttributeValueException, de.escidoc.core.common.exceptions.remote.application.notfound.RelationPredicateNotFoundException, de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContextException, de.escidoc.core.common.exceptions.remote.application.missing.MissingElementValueException, de.escidoc.core.common.exceptions.remote.application.violated.LockingException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException, de.escidoc.core.common.exceptions.remote.application.notfound.ContentModelNotFoundException, de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMdRecordException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException, de.escidoc.core.common.exceptions.remote.application.notfound.ReferencedResourceNotFoundException, de.escidoc.core.common.exceptions.remote.application.notfound.ContextNotFoundException, de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "createContainer"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0, in1});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.system.SystemException) {
              throw (de.escidoc.core.common.exceptions.remote.system.SystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingAttributeValueException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingAttributeValueException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.RelationPredicateNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.RelationPredicateNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContextException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContextException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingElementValueException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingElementValueException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.LockingException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.LockingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.ContentModelNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.ContentModelNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingMdRecordException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingMdRecordException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.ReferencedResourceNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.ReferencedResourceNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.ContextNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.ContextNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String retrieveProperties(java.lang.String in0) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException, de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException, de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "retrieveProperties"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.system.SystemException) {
              throw (de.escidoc.core.common.exceptions.remote.system.SystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String retrieveVersionHistory(java.lang.String in0) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException, de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException, de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "retrieveVersionHistory"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.system.SystemException) {
              throw (de.escidoc.core.common.exceptions.remote.system.SystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String retrieveMembers(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSearchQueryException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException, de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[11]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "retrieveMembers"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0, in1});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.system.SystemException) {
              throw (de.escidoc.core.common.exceptions.remote.system.SystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSearchQueryException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSearchQueryException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String retrieveMembers(java.lang.String in0, java.util.HashMap in1) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSearchQueryException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException, de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[12]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "retrieveMembers"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0, in1});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.system.SystemException) {
              throw (de.escidoc.core.common.exceptions.remote.system.SystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSearchQueryException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSearchQueryException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String retrieveTocs(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSearchQueryException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException, de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[13]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "retrieveTocs"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0, in1});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.system.SystemException) {
              throw (de.escidoc.core.common.exceptions.remote.system.SystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSearchQueryException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSearchQueryException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String retrieveTocs(java.lang.String in0, java.util.HashMap in1) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSearchQueryException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException, de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[14]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "retrieveTocs"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0, in1});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.system.SystemException) {
              throw (de.escidoc.core.common.exceptions.remote.system.SystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSearchQueryException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSearchQueryException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String addMembers(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException, de.escidoc.core.common.exceptions.remote.application.missing.MissingAttributeValueException, de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContextException, de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException, de.escidoc.core.common.exceptions.remote.application.violated.LockingException, de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[15]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "addMembers"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0, in1});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.system.SystemException) {
              throw (de.escidoc.core.common.exceptions.remote.system.SystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingAttributeValueException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingAttributeValueException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContextException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContextException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.LockingException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.LockingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String addTocs(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException, de.escidoc.core.common.exceptions.remote.application.missing.MissingAttributeValueException, de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContextException, de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException, de.escidoc.core.common.exceptions.remote.application.violated.LockingException, de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[16]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "addTocs"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0, in1});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.system.SystemException) {
              throw (de.escidoc.core.common.exceptions.remote.system.SystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingAttributeValueException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingAttributeValueException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContextException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContextException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.LockingException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.LockingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String removeMembers(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException, de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.missing.MissingAttributeValueException, de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException, de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException, de.escidoc.core.common.exceptions.remote.application.violated.LockingException, de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[17]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "removeMembers"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0, in1});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.system.SystemException) {
              throw (de.escidoc.core.common.exceptions.remote.system.SystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingAttributeValueException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingAttributeValueException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.LockingException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.LockingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String retrieveMdRecord(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException, de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException, de.escidoc.core.common.exceptions.remote.application.notfound.MdRecordNotFoundException, de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[18]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "retrieveMdRecord"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0, in1});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.system.SystemException) {
              throw (de.escidoc.core.common.exceptions.remote.system.SystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.MdRecordNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.MdRecordNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String retrieveMdRecords(java.lang.String in0) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException, de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException, de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[19]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "retrieveMdRecords"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.system.SystemException) {
              throw (de.escidoc.core.common.exceptions.remote.system.SystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String retrieveStructMap(java.lang.String in0) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException, de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException, de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[20]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "retrieveStructMap"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.system.SystemException) {
              throw (de.escidoc.core.common.exceptions.remote.system.SystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String retrieveRelations(java.lang.String in0) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException, de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException, de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[21]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "retrieveRelations"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.system.SystemException) {
              throw (de.escidoc.core.common.exceptions.remote.system.SystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String withdraw(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException, de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException, de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException, de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyVersionException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException, de.escidoc.core.common.exceptions.remote.application.violated.LockingException, de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException, de.escidoc.core.common.exceptions.remote.application.violated.AlreadyWithdrawnException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[22]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "withdraw"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0, in1});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.system.SystemException) {
              throw (de.escidoc.core.common.exceptions.remote.system.SystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyVersionException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyVersionException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.LockingException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.LockingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.AlreadyWithdrawnException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.AlreadyWithdrawnException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String revise(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException, de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyVersionException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException, de.escidoc.core.common.exceptions.remote.application.violated.LockingException, de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[23]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "revise"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0, in1});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.system.SystemException) {
              throw (de.escidoc.core.common.exceptions.remote.system.SystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyVersionException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyVersionException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.LockingException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.LockingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String moveToContext(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException, de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException, de.escidoc.core.common.exceptions.remote.application.notfound.ContextNotFoundException, de.escidoc.core.common.exceptions.remote.application.violated.LockingException, de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[24]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "moveToContext"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0, in1});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.system.SystemException) {
              throw (de.escidoc.core.common.exceptions.remote.system.SystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.ContextNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.ContextNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.LockingException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.LockingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String createItem(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.notfound.RelationPredicateNotFoundException, de.escidoc.core.common.exceptions.remote.application.missing.MissingAttributeValueException, de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContextException, de.escidoc.core.common.exceptions.remote.application.missing.MissingElementValueException, de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyElementViolationException, de.escidoc.core.common.exceptions.remote.application.violated.LockingException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException, de.escidoc.core.common.exceptions.remote.application.notfound.ContentModelNotFoundException, de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException, de.escidoc.core.common.exceptions.remote.application.notfound.FileNotFoundException, de.escidoc.core.common.exceptions.remote.application.missing.MissingContentException, de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyAttributeViolationException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMdRecordException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException, de.escidoc.core.common.exceptions.remote.application.notfound.ReferencedResourceNotFoundException, de.escidoc.core.common.exceptions.remote.application.notfound.ContextNotFoundException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException, de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[25]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "createItem"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0, in1});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.system.SystemException) {
              throw (de.escidoc.core.common.exceptions.remote.system.SystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.RelationPredicateNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.RelationPredicateNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingAttributeValueException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingAttributeValueException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContextException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContextException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingElementValueException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingElementValueException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyElementViolationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyElementViolationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.LockingException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.LockingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.ContentModelNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.ContentModelNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.FileNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.FileNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingContentException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingContentException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyAttributeViolationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyAttributeViolationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingMdRecordException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingMdRecordException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.ReferencedResourceNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.ReferencedResourceNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.ContextNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.ContextNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String retrieveContainers(java.lang.String in0) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSearchQueryException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[26]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "retrieveContainers"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.system.SystemException) {
              throw (de.escidoc.core.common.exceptions.remote.system.SystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSearchQueryException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSearchQueryException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String retrieveContainers(java.util.HashMap in0) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSearchQueryException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[27]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "retrieveContainers"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.system.SystemException) {
              throw (de.escidoc.core.common.exceptions.remote.system.SystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSearchQueryException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSearchQueryException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String addContentRelations(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException, de.escidoc.core.common.exceptions.remote.application.notfound.RelationPredicateNotFoundException, de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException, de.escidoc.core.common.exceptions.remote.application.missing.MissingElementValueException, de.escidoc.core.common.exceptions.remote.application.violated.LockingException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException, de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException, de.escidoc.core.common.exceptions.remote.application.violated.AlreadyExistsException, de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyVersionException, de.escidoc.core.common.exceptions.remote.application.notfound.ReferencedResourceNotFoundException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException, de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[28]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "addContentRelations"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0, in1});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.system.SystemException) {
              throw (de.escidoc.core.common.exceptions.remote.system.SystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.RelationPredicateNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.RelationPredicateNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingElementValueException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingElementValueException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.LockingException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.LockingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.AlreadyExistsException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.AlreadyExistsException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyVersionException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyVersionException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.ReferencedResourceNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.ReferencedResourceNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String removeContentRelations(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException, de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException, de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException, de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyVersionException, de.escidoc.core.common.exceptions.remote.application.missing.MissingElementValueException, de.escidoc.core.common.exceptions.remote.application.notfound.ContentRelationNotFoundException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException, de.escidoc.core.common.exceptions.remote.application.violated.LockingException, de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[29]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "removeContentRelations"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0, in1});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.system.SystemException) {
              throw (de.escidoc.core.common.exceptions.remote.system.SystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyVersionException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyVersionException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingElementValueException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingElementValueException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.ContentRelationNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.ContentRelationNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.LockingException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.LockingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String assignObjectPid(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException, de.escidoc.core.common.exceptions.remote.application.violated.LockingException, de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[30]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "assignObjectPid"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0, in1});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.system.SystemException) {
              throw (de.escidoc.core.common.exceptions.remote.system.SystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.LockingException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.LockingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String assignVersionPid(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException, de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyVersionException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException, de.escidoc.core.common.exceptions.remote.application.violated.LockingException, de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[31]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.escidoc.de/services/ContainerHandlerService/0.1", "assignVersionPid"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0, in1});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.system.SystemException) {
              throw (de.escidoc.core.common.exceptions.remote.system.SystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyVersionException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyVersionException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.LockingException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.LockingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

}
