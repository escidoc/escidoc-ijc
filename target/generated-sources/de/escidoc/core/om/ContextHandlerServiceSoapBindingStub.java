/**
 * ContextHandlerServiceSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.escidoc.core.om;

public class ContextHandlerServiceSoapBindingStub extends org.apache.axis.client.Stub implements de.escidoc.core.om.ContextHandler {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[13];
        _initOperationDesc1();
        _initOperationDesc2();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("close");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "closeReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "OptimisticLockingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.SystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthorizationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidStatusException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.StreamNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "StreamNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthenticationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidXmlException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ContextNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ContextNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.LockingException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "LockingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
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
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.SystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthorizationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidStatusException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthenticationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.ContextNotEmptyException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "ContextNotEmptyException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ContextNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ContextNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingMethodParameterException"), 
                      true
                     ));
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("open");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "openReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "OptimisticLockingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.SystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthorizationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidStatusException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.StreamNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "StreamNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthenticationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidXmlException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ContextNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ContextNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.LockingException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "LockingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
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
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingAttributeValueException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingAttributeValueException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.SystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthorizationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidStatusException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingElementValueException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingElementValueException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyElementViolationException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "ReadonlyElementViolationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidContentException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ContentModelNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ContentModelNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.ContextNameNotUniqueException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "ContextNameNotUniqueException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.OrganizationalUnitNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "OrganizationalUnitNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthenticationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyAttributeViolationException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "ReadonlyAttributeViolationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidXmlException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingMethodParameterException"), 
                      true
                     ));
        _operations[3] = oper;

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
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "OptimisticLockingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.SystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthorizationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidStatusException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingElementValueException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingElementValueException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyElementViolationException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "ReadonlyElementViolationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidContentException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.ContextNameNotUniqueException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "ContextNameNotUniqueException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthenticationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyAttributeViolationException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "ReadonlyAttributeViolationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidXmlException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ContextNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ContextNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingMethodParameterException"), 
                      true
                     ));
        _operations[4] = oper;

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
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.SystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthorizationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthenticationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ContextNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ContextNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingMethodParameterException"), 
                      true
                     ));
        _operations[5] = oper;

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
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.SystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ContextNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ContextNotFoundException"), 
                      true
                     ));
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("retrieveContexts");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://xml.apache.org/xml-soap", "Map"), java.util.HashMap.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "retrieveContextsReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.SystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSearchQueryException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidSearchQueryException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidXmlException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingMethodParameterException"), 
                      true
                     ));
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("retrieveContexts");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "retrieveContextsReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.SystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSearchQueryException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidSearchQueryException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidXmlException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingMethodParameterException"), 
                      true
                     ));
        _operations[8] = oper;

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
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.SystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSearchQueryException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidSearchQueryException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidXmlException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ContextNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ContextNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
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
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.SystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSearchQueryException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidSearchQueryException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidXmlException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ContextNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ContextNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingMethodParameterException"), 
                      true
                     ));
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("retrieveAdminDescriptor");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "retrieveAdminDescriptorReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.SystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthorizationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthenticationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.AdminDescriptorNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "AdminDescriptorNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ContextNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ContextNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingMethodParameterException"), 
                      true
                     ));
        _operations[11] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("retrieveAdminDescriptors");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "retrieveAdminDescriptorsReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.SystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthorizationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthenticationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ContextNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ContextNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingMethodParameterException"), 
                      true
                     ));
        _operations[12] = oper;

    }

    public ContextHandlerServiceSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public ContextHandlerServiceSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public ContextHandlerServiceSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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

            qName = new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "ContextNotEmptyException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.invalid.ContextNotEmptyException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidContentException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException.class;
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

            qName = new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingElementValueException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.missing.MissingElementValueException.class;
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

            qName = new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "AdminDescriptorNotFoundException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.notfound.AdminDescriptorNotFoundException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ContentModelNotFoundException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.notfound.ContentModelNotFoundException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ContextNotFoundException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.notfound.ContextNotFoundException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "OrganizationalUnitNotFoundException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.notfound.OrganizationalUnitNotFoundException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ResourceNotFoundException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.notfound.ResourceNotFoundException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "StreamNotFoundException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.notfound.StreamNotFoundException.class;
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

            qName = new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "ContextNameNotUniqueException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.violated.ContextNameNotUniqueException.class;
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

    public java.lang.String close(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException, de.escidoc.core.common.exceptions.remote.application.notfound.StreamNotFoundException, de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException, de.escidoc.core.common.exceptions.remote.application.notfound.ContextNotFoundException, de.escidoc.core.common.exceptions.remote.application.violated.LockingException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "close"));

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
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.StreamNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.StreamNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.ContextNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.ContextNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.LockingException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.LockingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void delete(java.lang.String in0) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException, de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException, de.escidoc.core.common.exceptions.remote.application.invalid.ContextNotEmptyException, de.escidoc.core.common.exceptions.remote.application.notfound.ContextNotFoundException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "delete"));

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
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.ContextNotEmptyException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.ContextNotEmptyException) axisFaultException.detail;
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

    public java.lang.String open(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException, de.escidoc.core.common.exceptions.remote.application.notfound.StreamNotFoundException, de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException, de.escidoc.core.common.exceptions.remote.application.notfound.ContextNotFoundException, de.escidoc.core.common.exceptions.remote.application.violated.LockingException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "open"));

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
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.StreamNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.StreamNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.ContextNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.ContextNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.LockingException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.LockingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String create(java.lang.String in0) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.application.missing.MissingAttributeValueException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException, de.escidoc.core.common.exceptions.remote.application.missing.MissingElementValueException, de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyElementViolationException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException, de.escidoc.core.common.exceptions.remote.application.notfound.ContentModelNotFoundException, de.escidoc.core.common.exceptions.remote.application.violated.ContextNameNotUniqueException, de.escidoc.core.common.exceptions.remote.application.notfound.OrganizationalUnitNotFoundException, de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException, de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyAttributeViolationException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "create"));

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
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingElementValueException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingElementValueException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyElementViolationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyElementViolationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.ContentModelNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.ContentModelNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.ContextNameNotUniqueException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.ContextNameNotUniqueException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.OrganizationalUnitNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.OrganizationalUnitNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyAttributeViolationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyAttributeViolationException) axisFaultException.detail;
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

    public java.lang.String update(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException, de.escidoc.core.common.exceptions.remote.application.missing.MissingElementValueException, de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyElementViolationException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException, de.escidoc.core.common.exceptions.remote.application.violated.ContextNameNotUniqueException, de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException, de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyAttributeViolationException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException, de.escidoc.core.common.exceptions.remote.application.notfound.ContextNotFoundException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "update"));

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
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingElementValueException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingElementValueException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyElementViolationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyElementViolationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.ContextNameNotUniqueException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.ContextNameNotUniqueException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyAttributeViolationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyAttributeViolationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException) axisFaultException.detail;
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

    public java.lang.String retrieve(java.lang.String in0) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException, de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException, de.escidoc.core.common.exceptions.remote.application.notfound.ContextNotFoundException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "retrieve"));

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

    public java.lang.String retrieveProperties(java.lang.String in0) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.notfound.ContextNotFoundException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "retrieveProperties"));

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
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.ContextNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.ContextNotFoundException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String retrieveContexts(java.util.HashMap in0) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSearchQueryException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "retrieveContexts"));

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

    public java.lang.String retrieveContexts(java.lang.String in0) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSearchQueryException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "retrieveContexts"));

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

    public java.lang.String retrieveMembers(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSearchQueryException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException, de.escidoc.core.common.exceptions.remote.application.notfound.ContextNotFoundException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "retrieveMembers"));

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

    public java.lang.String retrieveMembers(java.lang.String in0, java.util.HashMap in1) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSearchQueryException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException, de.escidoc.core.common.exceptions.remote.application.notfound.ContextNotFoundException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "retrieveMembers"));

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

    public java.lang.String retrieveAdminDescriptor(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException, de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException, de.escidoc.core.common.exceptions.remote.application.notfound.AdminDescriptorNotFoundException, de.escidoc.core.common.exceptions.remote.application.notfound.ContextNotFoundException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[11]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "retrieveAdminDescriptor"));

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
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.AdminDescriptorNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.AdminDescriptorNotFoundException) axisFaultException.detail;
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

    public java.lang.String retrieveAdminDescriptors(java.lang.String in0) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException, de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException, de.escidoc.core.common.exceptions.remote.application.notfound.ContextNotFoundException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[12]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.escidoc.de/services/ContextHandlerService/0.1", "retrieveAdminDescriptors"));

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

}
