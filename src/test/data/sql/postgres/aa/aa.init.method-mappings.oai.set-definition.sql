   /**
    * set-definition method mappings
    */
    
        /**
         * set-definition mm - create
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-set-definition-create', 'de.escidoc.core.oai.service.SetDefinitionHandler', 'create', 'info:escidoc/names:aa:1.0:action:create-set-definition', 
  true, true, 'de.escidoc.core.common.exceptions.application.notfound.ResourceNotFoundException');

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-set-definition-create-1', 'info:escidoc/names:aa:1.0:resource:object-type-new', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 3, false, 'set-definition', 'escidoc:mm-set-definition-create');



        /**
         * set-definition mm - delete
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-set-definition-delete', 'de.escidoc.core.oai.service.SetDefinitionHandler', 'delete', 'info:escidoc/names:aa:1.0:action:delete-set-definition', 
  true, true, 'de.escidoc.core.common.exceptions.application.notfound.ResourceNotFoundException');

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-set-definition-delete-1', 'urn:oasis:names:tc:xacml:1.0:resource:resource-id', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-set-definition-delete');


        /**
         * set-definition mm - retrieve
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-set-definition-retrieve', 'de.escidoc.core.oai.service.SetDefinitionHandler', 'retrieve', 'info:escidoc/names:aa:1.0:action:retrieve-set-definition', 
  true, true, 'de.escidoc.core.common.exceptions.application.notfound.ResourceNotFoundException');

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-set-definition-retrieve-1', 'urn:oasis:names:tc:xacml:1.0:resource:resource-id', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-set-definition-retrieve');



        /**
         * set-definition mm - update
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-set-definition-update', 'de.escidoc.core.oai.service.SetDefinitionHandler', 'update', 'info:escidoc/names:aa:1.0:action:update-set-definition', 
  true, true, 'de.escidoc.core.common.exceptions.application.notfound.ResourceNotFoundException');

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-set-definition-update-1', 'urn:oasis:names:tc:xacml:1.0:resource:resource-id', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-set-definition-update');


        /**
         * set-definitions mm - retrieve set-definitions
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource)
  VALUES ('escidoc:mm-oai-retrieve-set-definitions', 'de.escidoc.core.oai.service.SetDefinitionHandler', 'retrieveSetDefinitions', 
  'info:escidoc/names:aa:1.0:action:retrieve-objects-filtered', false, true);