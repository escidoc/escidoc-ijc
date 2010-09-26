/**
 * SoapExceptionGenerationServiceSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.escidoc.core.common;

public class SoapExceptionGenerationServiceSoapBindingStub extends org.apache.axis.client.Stub implements de.escidoc.core.common.SoapExceptionGeneration {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[1];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("generateExceptions");
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ActionNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ActionNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidItemStatusException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidItemStatusException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.UserGroupNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "UserGroupNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.ResourceInUseException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "ResourceInUseException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.IngestionSourceNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "IngestionSourceNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSearchQueryException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidSearchQueryException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.AdminDescriptorNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "AdminDescriptorNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ContentRelationNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ContentRelationNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.AdminDescriptorViolationException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "AdminDescriptorViolationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.WorkflowTemplateNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "WorkflowTemplateNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.RelationRuleViolationException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "RelationRuleViolationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.TaskNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "TaskNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ScopeNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ScopeNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.RoleInUseViolationException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "RoleInUseViolationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidStatusException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.RuleViolationException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "RuleViolationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.XmlParserSystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "XmlParserSystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.TaskListNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "TaskListNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSqlException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidSqlException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.RevisionNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "RevisionNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidRelationPropertiesException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidRelationPropertiesException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.FileNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "FileNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidTripleStoreOutputFormatException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidTripleStoreOutputFormatException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.ScopeContextViolationException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "ScopeContextViolationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.WorkflowDefinitionNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "WorkflowDefinitionNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.FileSystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "FileSystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "OptimisticLockingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.TmeException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "TmeException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.FedoraSystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "FedoraSystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.XmlSchemaNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "XmlSchemaNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.OrganizationalUnitHierarchyViolationException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "OrganizationalUnitHierarchyViolationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingWorkflowVariableException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingWorkflowVariableException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidContentException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.XmlCorruptedException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "XmlCorruptedException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.AlreadyExistsException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "AlreadyExistsException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.UserGroupHierarchyViolationException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "UserGroupHierarchyViolationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.ReferenceCycleException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "ReferenceCycleException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ItemReferenceNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ItemReferenceNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingUserListException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingUserListException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.ApplicationException",
                      new javax.xml.namespace.QName("http://application.exceptions.common.core.escidoc.de", "ApplicationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.SystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingAttributeValueException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingAttributeValueException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.ValidationException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "ValidationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.StructuralMapEntryNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "StructuralMapEntryNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.TargetBasketNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "TargetBasketNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingElementValueException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingElementValueException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ResourceNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ResourceNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyAttributeViolationException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "ReadonlyAttributeViolationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.UserNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "UserNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.TripleStoreSystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "TripleStoreSystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContextStatusException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidContextStatusException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.WorkflowEngineSystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "WorkflowEngineSystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.WebserverSystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "WebserverSystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.RelationNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "RelationNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingParameterException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.EscidocException",
                      new javax.xml.namespace.QName("http://exceptions.common.core.escidoc.de", "EscidocException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.ApplicationServerSystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "ApplicationServerSystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyViolationException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "ReadonlyViolationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidWorkflowTypeException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidWorkflowTypeException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.IngestionDefinitionNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "IngestionDefinitionNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ContainerNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.AlreadyWithdrawnException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "AlreadyWithdrawnException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.UserAccountNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "UserAccountNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.VersionNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "VersionNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.AggregationDefinitionNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "AggregationDefinitionNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.PidNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "PidNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.RelationTypeNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "RelationTypeNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidTripleStoreQueryException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidTripleStoreQueryException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.IngestionTaskNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "IngestionTaskNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyElementViolationException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "ReadonlyElementViolationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ComponentNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ComponentNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.NotPublishedException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "NotPublishedException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.WorkflowTaskViolationException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "WorkflowTaskViolationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingMdRecordException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingMdRecordException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ReferencedResourceNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ReferencedResourceNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidPidException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidPidException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.IndexNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "IndexNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.XmlSchemaValidationException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "XmlSchemaValidationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.RelationPredicateNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "RelationPredicateNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.TransitionNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "TransitionNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidAggregationTypeException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidAggregationTypeException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.AlreadyDeletedException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "AlreadyDeletedException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.SqlDatabaseSystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "SqlDatabaseSystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.TimeFrameViolationException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "TimeFrameViolationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.WorkflowTypeNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "WorkflowTypeNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ReportDefinitionNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ReportDefinitionNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingContentException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingContentException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.WorkflowInstanceNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "WorkflowInstanceNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidXmlException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ContextNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ContextNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.RoleNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "RoleNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.system.IntegritySystemException",
                      new javax.xml.namespace.QName("http://system.exceptions.common.core.escidoc.de", "IntegritySystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.AggregationTypeNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "AggregationTypeNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingLicenceException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingLicenceException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContextException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidContextException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.AlreadyPublishedException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "AlreadyPublishedException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.MdRecordNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "MdRecordNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.LockingException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "LockingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidWorkflowDefinitionException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidWorkflowDefinitionException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.WorkflowViolationException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "WorkflowViolationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ContentModelNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ContentModelNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyVersionException",
                      new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "ReadonlyVersionException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentModelException",
                      new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidContentModelException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.ItemNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ItemNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.notfound.SearchNotFoundException",
                      new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "SearchNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "fault"),
                      "de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException",
                      new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingMethodParameterException"), 
                      true
                     ));
        _operations[0] = oper;

    }

    public SoapExceptionGenerationServiceSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public SoapExceptionGenerationServiceSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public SoapExceptionGenerationServiceSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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
        addBindings0();
        addBindings1();
    }

    private void addBindings0() {
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

            qName = new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidAggregationTypeException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.invalid.InvalidAggregationTypeException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidContentException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidContentModelException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentModelException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidContextException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContextException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidContextStatusException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContextStatusException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidItemStatusException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.invalid.InvalidItemStatusException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidPidException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.invalid.InvalidPidException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidRelationPropertiesException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.invalid.InvalidRelationPropertiesException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidResourceException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.invalid.InvalidResourceException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidSearchQueryException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSearchQueryException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidSqlException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSqlException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidStatusException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidTripleStoreOutputFormatException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.invalid.InvalidTripleStoreOutputFormatException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidTripleStoreQueryException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.invalid.InvalidTripleStoreQueryException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidWorkflowDefinitionException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.invalid.InvalidWorkflowDefinitionException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidWorkflowTypeException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.invalid.InvalidWorkflowTypeException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "InvalidXmlException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "ReferenceCycleException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.invalid.ReferenceCycleException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://invalid.application.exceptions.common.core.escidoc.de", "TmeException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.invalid.TmeException.class;
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

            qName = new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingLicenceException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.missing.MissingLicenceException.class;
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

            qName = new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingUserListException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.missing.MissingUserListException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://missing.application.exceptions.common.core.escidoc.de", "MissingWorkflowVariableException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.missing.MissingWorkflowVariableException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ActionNotFoundException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.notfound.ActionNotFoundException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "AdminDescriptorNotFoundException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.notfound.AdminDescriptorNotFoundException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "AggregationDefinitionNotFoundException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.notfound.AggregationDefinitionNotFoundException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "AggregationTypeNotFoundException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.notfound.AggregationTypeNotFoundException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ComponentNotFoundException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.notfound.ComponentNotFoundException.class;
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

            qName = new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "GrantNotFoundException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.notfound.GrantNotFoundException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "IndexNotFoundException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.notfound.IndexNotFoundException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "IngestionDefinitionNotFoundException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.notfound.IngestionDefinitionNotFoundException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "IngestionSourceNotFoundException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.notfound.IngestionSourceNotFoundException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "IngestionTaskNotFoundException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.notfound.IngestionTaskNotFoundException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ItemNotFoundException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.notfound.ItemNotFoundException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ItemReferenceNotFoundException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.notfound.ItemReferenceNotFoundException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "MdRecordNotFoundException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.notfound.MdRecordNotFoundException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "OrganizationalUnitNotFoundException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.notfound.OrganizationalUnitNotFoundException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "PidNotFoundException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.notfound.PidNotFoundException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "PreferenceNotFoundException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.notfound.PreferenceNotFoundException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ReferencedResourceNotFoundException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.notfound.ReferencedResourceNotFoundException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "RelationNotFoundException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.notfound.RelationNotFoundException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "RelationPredicateNotFoundException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.notfound.RelationPredicateNotFoundException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "RelationTypeNotFoundException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.notfound.RelationTypeNotFoundException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ReportDefinitionNotFoundException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.notfound.ReportDefinitionNotFoundException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ResourceNotFoundException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.notfound.ResourceNotFoundException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "RevisionNotFoundException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.notfound.RevisionNotFoundException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "RoleNotFoundException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.notfound.RoleNotFoundException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "ScopeNotFoundException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.notfound.ScopeNotFoundException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "SearchNotFoundException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.notfound.SearchNotFoundException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "StagingFileNotFoundException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.notfound.StagingFileNotFoundException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "StructuralMapEntryNotFoundException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.notfound.StructuralMapEntryNotFoundException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "TargetBasketNotFoundException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.notfound.TargetBasketNotFoundException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "TaskListNotFoundException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.notfound.TaskListNotFoundException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "TaskNotFoundException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.notfound.TaskNotFoundException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "TransitionNotFoundException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.notfound.TransitionNotFoundException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "UserAccountNotFoundException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.notfound.UserAccountNotFoundException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "UserAttributeNotFoundException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.notfound.UserAttributeNotFoundException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "UserGroupNotFoundException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.notfound.UserGroupNotFoundException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "UserNotFoundException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.notfound.UserNotFoundException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "VersionNotFoundException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.notfound.VersionNotFoundException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "WorkflowDefinitionNotFoundException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.notfound.WorkflowDefinitionNotFoundException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "WorkflowInstanceNotFoundException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.notfound.WorkflowInstanceNotFoundException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "WorkflowTemplateNotFoundException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.notfound.WorkflowTemplateNotFoundException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "WorkflowTypeNotFoundException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.notfound.WorkflowTypeNotFoundException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://notfound.application.exceptions.common.core.escidoc.de", "XmlSchemaNotFoundException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.notfound.XmlSchemaNotFoundException.class;
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

            qName = new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "AdminDescriptorViolationException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.violated.AdminDescriptorViolationException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "AlreadyActiveException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.violated.AlreadyActiveException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "AlreadyDeactiveException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.violated.AlreadyDeactiveException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "AlreadyDeletedException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.violated.AlreadyDeletedException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "AlreadyExistsException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.violated.AlreadyExistsException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "AlreadyPublishedException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.violated.AlreadyPublishedException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }
    private void addBindings1() {
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
            qName = new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "AlreadyRevokedException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.violated.AlreadyRevokedException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "AlreadyWithdrawnException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.violated.AlreadyWithdrawnException.class;
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

            qName = new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "NotPublishedException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.violated.NotPublishedException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "OptimisticLockingException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "OrganizationalUnitHasChildrenException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.violated.OrganizationalUnitHasChildrenException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "OrganizationalUnitHierarchyViolationException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.violated.OrganizationalUnitHierarchyViolationException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "OrganizationalUnitNameNotUniqueException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.violated.OrganizationalUnitNameNotUniqueException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "PidAlreadyAssignedException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.violated.PidAlreadyAssignedException.class;
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

            qName = new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "RelationRuleViolationException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.violated.RelationRuleViolationException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "ResourceInUseException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.violated.ResourceInUseException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "RoleInUseViolationException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.violated.RoleInUseViolationException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "RuleViolationException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.violated.RuleViolationException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "ScopeContextViolationException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.violated.ScopeContextViolationException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "TimeFrameViolationException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.violated.TimeFrameViolationException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "UniqueConstraintViolationException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.violated.UniqueConstraintViolationException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "UserGroupHierarchyViolationException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.violated.UserGroupHierarchyViolationException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "WorkflowTaskViolationException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.violated.WorkflowTaskViolationException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://violated.application.exceptions.common.core.escidoc.de", "WorkflowViolationException");
            cachedSerQNames.add(qName);
            cls = de.escidoc.core.common.exceptions.remote.application.violated.WorkflowViolationException.class;
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

    public void generateExceptions() throws java.rmi.RemoteException, de.escidoc.core.common.exceptions.remote.application.notfound.ActionNotFoundException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidItemStatusException, de.escidoc.core.common.exceptions.remote.application.notfound.UserGroupNotFoundException, de.escidoc.core.common.exceptions.remote.application.violated.ResourceInUseException, de.escidoc.core.common.exceptions.remote.application.notfound.IngestionSourceNotFoundException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSearchQueryException, de.escidoc.core.common.exceptions.remote.application.notfound.AdminDescriptorNotFoundException, de.escidoc.core.common.exceptions.remote.application.notfound.ContentRelationNotFoundException, de.escidoc.core.common.exceptions.remote.application.violated.AdminDescriptorViolationException, de.escidoc.core.common.exceptions.remote.application.notfound.WorkflowTemplateNotFoundException, de.escidoc.core.common.exceptions.remote.application.violated.RelationRuleViolationException, de.escidoc.core.common.exceptions.remote.application.notfound.TaskNotFoundException, de.escidoc.core.common.exceptions.remote.application.notfound.ScopeNotFoundException, de.escidoc.core.common.exceptions.remote.application.violated.RoleInUseViolationException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException, de.escidoc.core.common.exceptions.remote.application.violated.RuleViolationException, de.escidoc.core.common.exceptions.remote.system.XmlParserSystemException, de.escidoc.core.common.exceptions.remote.application.notfound.TaskListNotFoundException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSqlException, de.escidoc.core.common.exceptions.remote.application.notfound.RevisionNotFoundException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidRelationPropertiesException, de.escidoc.core.common.exceptions.remote.application.notfound.FileNotFoundException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidTripleStoreOutputFormatException, de.escidoc.core.common.exceptions.remote.application.violated.ScopeContextViolationException, de.escidoc.core.common.exceptions.remote.application.notfound.WorkflowDefinitionNotFoundException, de.escidoc.core.common.exceptions.remote.system.FileSystemException, de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException, de.escidoc.core.common.exceptions.remote.application.invalid.TmeException, de.escidoc.core.common.exceptions.remote.system.FedoraSystemException, de.escidoc.core.common.exceptions.remote.application.notfound.XmlSchemaNotFoundException, de.escidoc.core.common.exceptions.remote.application.violated.OrganizationalUnitHierarchyViolationException, de.escidoc.core.common.exceptions.remote.application.missing.MissingWorkflowVariableException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException, de.escidoc.core.common.exceptions.remote.application.invalid.XmlCorruptedException, de.escidoc.core.common.exceptions.remote.application.violated.AlreadyExistsException, de.escidoc.core.common.exceptions.remote.application.violated.UserGroupHierarchyViolationException, de.escidoc.core.common.exceptions.remote.application.invalid.ReferenceCycleException, de.escidoc.core.common.exceptions.remote.application.notfound.ItemReferenceNotFoundException, de.escidoc.core.common.exceptions.remote.application.missing.MissingUserListException, de.escidoc.core.common.exceptions.remote.application.ApplicationException, de.escidoc.core.common.exceptions.remote.system.SystemException, de.escidoc.core.common.exceptions.remote.application.missing.MissingAttributeValueException, de.escidoc.core.common.exceptions.remote.application.invalid.ValidationException, de.escidoc.core.common.exceptions.remote.application.notfound.StructuralMapEntryNotFoundException, de.escidoc.core.common.exceptions.remote.application.notfound.TargetBasketNotFoundException, de.escidoc.core.common.exceptions.remote.application.missing.MissingElementValueException, de.escidoc.core.common.exceptions.remote.application.notfound.ResourceNotFoundException, de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyAttributeViolationException, de.escidoc.core.common.exceptions.remote.application.notfound.UserNotFoundException, de.escidoc.core.common.exceptions.remote.system.TripleStoreSystemException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContextStatusException, de.escidoc.core.common.exceptions.remote.system.WorkflowEngineSystemException, de.escidoc.core.common.exceptions.remote.system.WebserverSystemException, de.escidoc.core.common.exceptions.remote.application.notfound.RelationNotFoundException, de.escidoc.core.common.exceptions.remote.application.missing.MissingParameterException, de.escidoc.core.common.exceptions.remote.EscidocException, de.escidoc.core.common.exceptions.remote.system.ApplicationServerSystemException, de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyViolationException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidWorkflowTypeException, de.escidoc.core.common.exceptions.remote.application.notfound.IngestionDefinitionNotFoundException, de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException, de.escidoc.core.common.exceptions.remote.application.violated.AlreadyWithdrawnException, de.escidoc.core.common.exceptions.remote.application.notfound.UserAccountNotFoundException, de.escidoc.core.common.exceptions.remote.application.notfound.VersionNotFoundException, de.escidoc.core.common.exceptions.remote.application.notfound.AggregationDefinitionNotFoundException, de.escidoc.core.common.exceptions.remote.application.notfound.PidNotFoundException, de.escidoc.core.common.exceptions.remote.application.notfound.RelationTypeNotFoundException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidTripleStoreQueryException, de.escidoc.core.common.exceptions.remote.application.notfound.IngestionTaskNotFoundException, de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyElementViolationException, de.escidoc.core.common.exceptions.remote.application.notfound.ComponentNotFoundException, de.escidoc.core.common.exceptions.remote.application.violated.NotPublishedException, de.escidoc.core.common.exceptions.remote.application.violated.WorkflowTaskViolationException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMdRecordException, de.escidoc.core.common.exceptions.remote.application.notfound.ReferencedResourceNotFoundException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidPidException, de.escidoc.core.common.exceptions.remote.application.notfound.IndexNotFoundException, de.escidoc.core.common.exceptions.remote.application.invalid.XmlSchemaValidationException, de.escidoc.core.common.exceptions.remote.application.notfound.RelationPredicateNotFoundException, de.escidoc.core.common.exceptions.remote.application.notfound.TransitionNotFoundException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidAggregationTypeException, de.escidoc.core.common.exceptions.remote.application.violated.AlreadyDeletedException, de.escidoc.core.common.exceptions.remote.system.SqlDatabaseSystemException, de.escidoc.core.common.exceptions.remote.application.violated.TimeFrameViolationException, de.escidoc.core.common.exceptions.remote.application.notfound.WorkflowTypeNotFoundException, de.escidoc.core.common.exceptions.remote.application.notfound.ReportDefinitionNotFoundException, de.escidoc.core.common.exceptions.remote.application.missing.MissingContentException, de.escidoc.core.common.exceptions.remote.application.notfound.WorkflowInstanceNotFoundException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException, de.escidoc.core.common.exceptions.remote.application.notfound.ContextNotFoundException, de.escidoc.core.common.exceptions.remote.application.notfound.RoleNotFoundException, de.escidoc.core.common.exceptions.remote.system.IntegritySystemException, de.escidoc.core.common.exceptions.remote.application.notfound.AggregationTypeNotFoundException, de.escidoc.core.common.exceptions.remote.application.missing.MissingLicenceException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContextException, de.escidoc.core.common.exceptions.remote.application.violated.AlreadyPublishedException, de.escidoc.core.common.exceptions.remote.application.notfound.MdRecordNotFoundException, de.escidoc.core.common.exceptions.remote.application.violated.LockingException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidWorkflowDefinitionException, de.escidoc.core.common.exceptions.remote.application.violated.WorkflowViolationException, de.escidoc.core.common.exceptions.remote.application.notfound.ContentModelNotFoundException, de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyVersionException, de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentModelException, de.escidoc.core.common.exceptions.remote.application.notfound.ItemNotFoundException, de.escidoc.core.common.exceptions.remote.application.notfound.SearchNotFoundException, de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.escidoc.de/services/SoapExceptionGenerationService/0.1", "generateExceptions"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.ActionNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.ActionNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidItemStatusException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidItemStatusException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.UserGroupNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.UserGroupNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.ResourceInUseException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.ResourceInUseException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.IngestionSourceNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.IngestionSourceNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSearchQueryException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSearchQueryException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.AdminDescriptorNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.AdminDescriptorNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.ContentRelationNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.ContentRelationNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.AdminDescriptorViolationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.AdminDescriptorViolationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.WorkflowTemplateNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.WorkflowTemplateNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.RelationRuleViolationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.RelationRuleViolationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.TaskNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.TaskNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.ScopeNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.ScopeNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.RoleInUseViolationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.RoleInUseViolationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.RuleViolationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.RuleViolationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.system.XmlParserSystemException) {
              throw (de.escidoc.core.common.exceptions.remote.system.XmlParserSystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.TaskListNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.TaskListNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSqlException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSqlException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.RevisionNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.RevisionNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidRelationPropertiesException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidRelationPropertiesException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.FileNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.FileNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidTripleStoreOutputFormatException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidTripleStoreOutputFormatException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.ScopeContextViolationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.ScopeContextViolationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.WorkflowDefinitionNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.WorkflowDefinitionNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.system.FileSystemException) {
              throw (de.escidoc.core.common.exceptions.remote.system.FileSystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.TmeException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.TmeException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.system.FedoraSystemException) {
              throw (de.escidoc.core.common.exceptions.remote.system.FedoraSystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.XmlSchemaNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.XmlSchemaNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.OrganizationalUnitHierarchyViolationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.OrganizationalUnitHierarchyViolationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingWorkflowVariableException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingWorkflowVariableException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.XmlCorruptedException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.XmlCorruptedException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.AlreadyExistsException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.AlreadyExistsException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.UserGroupHierarchyViolationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.UserGroupHierarchyViolationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.ReferenceCycleException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.ReferenceCycleException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.ItemReferenceNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.ItemReferenceNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingUserListException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingUserListException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.ApplicationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.ApplicationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.system.SystemException) {
              throw (de.escidoc.core.common.exceptions.remote.system.SystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingAttributeValueException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingAttributeValueException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.ValidationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.ValidationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.StructuralMapEntryNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.StructuralMapEntryNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.TargetBasketNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.TargetBasketNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingElementValueException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingElementValueException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.ResourceNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.ResourceNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyAttributeViolationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyAttributeViolationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.UserNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.UserNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.system.TripleStoreSystemException) {
              throw (de.escidoc.core.common.exceptions.remote.system.TripleStoreSystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContextStatusException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContextStatusException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.system.WorkflowEngineSystemException) {
              throw (de.escidoc.core.common.exceptions.remote.system.WorkflowEngineSystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.system.WebserverSystemException) {
              throw (de.escidoc.core.common.exceptions.remote.system.WebserverSystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.RelationNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.RelationNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingParameterException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.EscidocException) {
              throw (de.escidoc.core.common.exceptions.remote.EscidocException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.system.ApplicationServerSystemException) {
              throw (de.escidoc.core.common.exceptions.remote.system.ApplicationServerSystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyViolationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyViolationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidWorkflowTypeException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidWorkflowTypeException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.IngestionDefinitionNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.IngestionDefinitionNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.AlreadyWithdrawnException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.AlreadyWithdrawnException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.UserAccountNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.UserAccountNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.VersionNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.VersionNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.AggregationDefinitionNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.AggregationDefinitionNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.PidNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.PidNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.RelationTypeNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.RelationTypeNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidTripleStoreQueryException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidTripleStoreQueryException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.IngestionTaskNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.IngestionTaskNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyElementViolationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyElementViolationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.ComponentNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.ComponentNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.NotPublishedException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.NotPublishedException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.WorkflowTaskViolationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.WorkflowTaskViolationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingMdRecordException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingMdRecordException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.ReferencedResourceNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.ReferencedResourceNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidPidException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidPidException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.IndexNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.IndexNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.XmlSchemaValidationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.XmlSchemaValidationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.RelationPredicateNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.RelationPredicateNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.TransitionNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.TransitionNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidAggregationTypeException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidAggregationTypeException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.AlreadyDeletedException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.AlreadyDeletedException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.system.SqlDatabaseSystemException) {
              throw (de.escidoc.core.common.exceptions.remote.system.SqlDatabaseSystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.TimeFrameViolationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.TimeFrameViolationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.WorkflowTypeNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.WorkflowTypeNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.ReportDefinitionNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.ReportDefinitionNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingContentException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingContentException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.WorkflowInstanceNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.WorkflowInstanceNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.ContextNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.ContextNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.RoleNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.RoleNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.system.IntegritySystemException) {
              throw (de.escidoc.core.common.exceptions.remote.system.IntegritySystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.AggregationTypeNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.AggregationTypeNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingLicenceException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingLicenceException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContextException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContextException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.AlreadyPublishedException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.AlreadyPublishedException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.MdRecordNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.MdRecordNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.LockingException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.LockingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidWorkflowDefinitionException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidWorkflowDefinitionException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.WorkflowViolationException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.WorkflowViolationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.ContentModelNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.ContentModelNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyVersionException) {
              throw (de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyVersionException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentModelException) {
              throw (de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentModelException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.ItemNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.ItemNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.notfound.SearchNotFoundException) {
              throw (de.escidoc.core.common.exceptions.remote.application.notfound.SearchNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) {
              throw (de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

}
