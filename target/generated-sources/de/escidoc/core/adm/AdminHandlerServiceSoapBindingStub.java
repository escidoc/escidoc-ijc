/**
 * AdminHandlerServiceSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.escidoc.core.adm;

public class AdminHandlerServiceSoapBindingStub extends org.apache.axis.client.Stub implements de.escidoc.core.adm.AdminHandler {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[9];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deleteObjects");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "deleteObjectsReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/AdminHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.SystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/AdminHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthorizationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/AdminHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthenticationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/AdminHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidXmlException"), 
                      true
                     ));
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getPurgeStatus");
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getPurgeStatusReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/AdminHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.SystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/AdminHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthorizationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/AdminHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthenticationException"), 
                      true
                     ));
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getRecacheStatus");
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getRecacheStatusReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/AdminHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.SystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/AdminHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthorizationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/AdminHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthenticationException"), 
                      true
                     ));
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getReindexStatus");
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getReindexStatusReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/AdminHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.SystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/AdminHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthorizationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/AdminHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthenticationException"), 
                      true
                     ));
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("decreaseReindexStatus");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/AdminHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.SystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/AdminHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthorizationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/AdminHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthenticationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/AdminHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidXmlException"), 
                      true
                     ));
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("recache");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "recacheReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/AdminHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.SystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/AdminHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthorizationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/AdminHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthenticationException"), 
                      true
                     ));
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("reindex");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "reindexReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/AdminHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.SystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/AdminHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSearchQueryException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidSearchQueryException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/AdminHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthorizationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/AdminHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthenticationException"), 
                      true
                     ));
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getRepositoryInfo");
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getRepositoryInfoReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/AdminHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.SystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/AdminHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthorizationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/AdminHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthenticationException"), 
                      true
                     ));
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("loadExamples");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "loadExamplesReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/AdminHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.SystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/AdminHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSearchQueryException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidSearchQueryException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/AdminHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthorizationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/AdminHandlerService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException",
                      new javax.xml.namespace.QName("http://security.application.exceptions.common.core.escidoc.de", "AuthenticationException"), 
                      true
                     ));
        _operations[8] = oper;

    }

    public AdminHandlerServiceSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public AdminHandlerServiceSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public AdminHandlerServiceSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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

            qName = new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidSearchQueryException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSearchQueryException.class;
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

    public java.lang.String deleteObjects(java.lang.String in0) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException, de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.escidoc.de/services/AdminHandlerService/0.1", "deleteObjects"));

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
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String getPurgeStatus() throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException, de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.escidoc.de/services/AdminHandlerService/0.1", "getPurgeStatus"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

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
   }
  throw axisFaultException;
}
    }

    public java.lang.String getRecacheStatus() throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException, de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.escidoc.de/services/AdminHandlerService/0.1", "getRecacheStatus"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

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
   }
  throw axisFaultException;
}
    }

    public java.lang.String getReindexStatus() throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException, de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.escidoc.de/services/AdminHandlerService/0.1", "getReindexStatus"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

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
   }
  throw axisFaultException;
}
    }

    public void decreaseReindexStatus(java.lang.String in0) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException, de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.escidoc.de/services/AdminHandlerService/0.1", "decreaseReindexStatus"));

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
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String recache(java.lang.String in0) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException, de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.escidoc.de/services/AdminHandlerService/0.1", "recache"));

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
   }
  throw axisFaultException;
}
    }

    public java.lang.String reindex(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSearchQueryException, de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException, de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.escidoc.de/services/AdminHandlerService/0.1", "reindex"));

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
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String getRepositoryInfo() throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException, de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.escidoc.de/services/AdminHandlerService/0.1", "getRepositoryInfo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

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
   }
  throw axisFaultException;
}
    }

    public java.lang.String loadExamples(java.lang.String in0) throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSearchQueryException, de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException, de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.escidoc.de/services/AdminHandlerService/0.1", "loadExamples"));

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
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

}
