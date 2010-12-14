   /**
    * Content model method mappings
    */
        /**
         * Content model mm - retrieve
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-content-model-retrieve', 'de.escidoc.core.cmm.service.ContentModelHandler', 'retrieve', 'info:escidoc/names:aa:1.0:action:retrieve-content-model',
  true, true, 'de.escidoc.core.common.exceptions.application.notfound.ContentModelNotFoundException');

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-mm-content-model-retrieve-1', 'urn:oasis:names:tc:xacml:1.0:resource:resource-id', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-content-model-retrieve');
         
        /**
         * Content model mm - retrieveProperties
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-content-model-retrieve-properties', 'de.escidoc.core.cmm.service.ContentModelHandler', 'retrieveProperties', 'info:escidoc/names:aa:1.0:action:retrieve-content-model',
  true, true, 'de.escidoc.core.common.exceptions.application.notfound.ContentModelNotFoundException');

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-content-model-retrieve-properties', 'urn:oasis:names:tc:xacml:1.0:resource:resource-id', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-content-model-retrieve-properties');
         
        /**
         * Content model mm - retrieveResources
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-content-model-retrieve-resources', 'de.escidoc.core.cmm.service.ContentModelHandler', 'retrieveResources', 'info:escidoc/names:aa:1.0:action:retrieve-content-model',
  true, true, 'de.escidoc.core.common.exceptions.application.notfound.ContentModelNotFoundException');

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-content-model-retrieve-resources', 'urn:oasis:names:tc:xacml:1.0:resource:resource-id', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-content-model-retrieve-resources');
         
        /**
         * Content model mm - retrieveVersionHistory
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-content-model-retrieve-resources-version-history', 'de.escidoc.core.cmm.service.ContentModelHandler', 'retrieveVersionHistory', 'info:escidoc/names:aa:1.0:action:retrieve-content-model',
  true, true, 'de.escidoc.core.common.exceptions.application.notfound.ContentModelNotFoundException');

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-content-model-retrieve-resources-version-history', 'urn:oasis:names:tc:xacml:1.0:resource:resource-id', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-content-model-retrieve-resources-version-history');
         
        /**
         * Content model mm - retrieveContentStreams
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-content-model-retrieve-content-streams', 'de.escidoc.core.cmm.service.ContentModelHandler', 'retrieveContentStreams', 'info:escidoc/names:aa:1.0:action:retrieve-content-model',
  true, true, 'de.escidoc.core.common.exceptions.application.notfound.ContentModelNotFoundException');

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-content-model-retrieve-content-streams', 'urn:oasis:names:tc:xacml:1.0:resource:resource-id', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-content-model-retrieve-content-streams');
         
        /**
         * Content model mm - retrieveContentStream
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-content-model-retrieve-content-stream', 'de.escidoc.core.cmm.service.ContentModelHandler', 'retrieveContentStream', 'info:escidoc/names:aa:1.0:action:retrieve-content-model',
  true, true, 'de.escidoc.core.common.exceptions.application.notfound.ContentModelNotFoundException');

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-content-model-retrieve-content-stream', 'urn:oasis:names:tc:xacml:1.0:resource:resource-id', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-content-model-retrieve-content-stream');
         
        /**
         * Content model mm - retrieveContentStreamContent
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-content-model-retrieve-content-stream-content', 'de.escidoc.core.cmm.service.ContentModelHandler', 'retrieveContentStreamContent', 'info:escidoc/names:aa:1.0:action:retrieve-content-model',
  true, true, 'de.escidoc.core.common.exceptions.application.notfound.ContentModelNotFoundException');

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-content-model-retrieve-content-stream-content', 'urn:oasis:names:tc:xacml:1.0:resource:resource-id', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-content-model-retrieve-content-stream-content');
         
        /**
         * Content model mm - retrieveMdRecordDefinitionSchemaContent
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-content-model-retrieve-md-record-definition-schema-content', 'de.escidoc.core.cmm.service.ContentModelHandler', 'retrieveMdRecordDefinitionSchemaContent', 'info:escidoc/names:aa:1.0:action:retrieve-content-model',
  true, true, 'de.escidoc.core.common.exceptions.application.notfound.ContentModelNotFoundException');

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-content-model-retrieve-md-record-definition-schema-content', 'urn:oasis:names:tc:xacml:1.0:resource:resource-id', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-content-model-retrieve-md-record-definition-schema-content');
         
        /**
         * Content model mm - retrieveResourceDefinitionXsltContent
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-content-model-retrieve-resource-definition-xslt-content', 'de.escidoc.core.cmm.service.ContentModelHandler', 'retrieveResourceDefinitionXsltContent', 'info:escidoc/names:aa:1.0:action:retrieve-content-model',
  true, true, 'de.escidoc.core.common.exceptions.application.notfound.ContentModelNotFoundException');

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-content-model-retrieve-resource-definition-xslt-content', 'urn:oasis:names:tc:xacml:1.0:resource:resource-id', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-content-model-retrieve-resource-definition-xslt-content');
         

          
          
        /**
         * Content model mm - create
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-content-model-create', 'de.escidoc.core.cmm.service.ContentModelHandler', 'create', 'info:escidoc/names:aa:1.0:action:create-content-model',
  true, true, 'de.escidoc.core.common.exceptions.application.notfound.ContentModelNotFoundException');

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-mm-content-model-create-1', 'urn:oasis:names:tc:xacml:1.0:resource:resource-id', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-content-model-create');
         
        /**
         * Content model mm - update
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-content-model-update', 'de.escidoc.core.cmm.service.ContentModelHandler', 'update', 'info:escidoc/names:aa:1.0:action:update-content-model',
  true, true, 'de.escidoc.core.common.exceptions.application.notfound.ContentModelNotFoundException');

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-mm-content-model-update-1', 'urn:oasis:names:tc:xacml:1.0:resource:resource-id', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-content-model-update');
          
        /**
         * Content model mm - delete
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-content-model-delete', 'de.escidoc.core.cmm.service.ContentModelHandler', 'delete', 'info:escidoc/names:aa:1.0:action:delete-content-model',
  true, true, 'de.escidoc.core.common.exceptions.application.notfound.ContentModelNotFoundException');

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-mm-content-model-delete-1', 'urn:oasis:names:tc:xacml:1.0:resource:resource-id', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-content-model-delete');
         
         