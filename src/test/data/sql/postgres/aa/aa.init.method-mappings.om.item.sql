   /**
    * Item method mappings
    */
    
        /**
         * Item mm - assignVersionPid
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-item-assign-version-pid', 'de.escidoc.core.om.service.ItemHandler', 'assignVersionPid', 'info:escidoc/names:aa:1.0:action:update-item', 
  true, true, 'de.escidoc.core.common.exceptions.application.notfound.ItemNotFoundException');
         
INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-item-assign-version-pid-1', 'urn:oasis:names:tc:xacml:1.0:resource:resource-id', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-item-assign-version-pid');

        /**
         * Item mm - assignObjectPid
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-item-assign-object-pid', 'de.escidoc.core.om.service.ItemHandler', 'assignObjectPid', 'info:escidoc/names:aa:1.0:action:update-item', 
  true, true, 'de.escidoc.core.common.exceptions.application.notfound.ItemNotFoundException');
         
INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-item-assign-object-pid-1', 'urn:oasis:names:tc:xacml:1.0:resource:resource-id', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-item-assign-object-pid');
   
        /**
         * Item mm - assignContentPid
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-item-assign-content-pid', 'de.escidoc.core.om.service.ItemHandler', 'assignContentPid', 'info:escidoc/names:aa:1.0:action:update-item', 
  true, true, 'de.escidoc.core.common.exceptions.application.notfound.ItemNotFoundException');
         
INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-item-assign-content-pid-1', 'urn:oasis:names:tc:xacml:1.0:resource:resource-id', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-item-assign-content-pid');

        /**
         * Item mm - create
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-item-create', 'de.escidoc.core.om.service.ItemHandler', 'create', 'info:escidoc/names:aa:1.0:action:create-item', 
  true, true, 'de.escidoc.core.common.exceptions.application.notfound.ItemNotFoundException');

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-item-create-1', 'info:escidoc/names:aa:1.0:resource:object-type-new', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 3, false, 'item', 'escidoc:mm-item-create');
INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-item-create-2', 'info:escidoc/names:aa:1.0:resource:item:context-new', 'extractObjid:/item/properties/context/@href|/item/properties/context/@objid', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 2, false, '', 'escidoc:mm-item-create');
INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-item-create-3', 'info:escidoc/names:aa:1.0:resource:item:content-model-new', 'extractObjid:/item/properties/content-model/@href|/item/properties/content-model/@objid', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 2, false, '', 'escidoc:mm-item-create');



        /**
         * Item mm - delete
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-item-2', 'de.escidoc.core.om.service.ItemHandler', 'delete', 'info:escidoc/names:aa:1.0:action:delete-item', 
  true, true, 'de.escidoc.core.common.exceptions.application.notfound.ItemNotFoundException');

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-item-2-1', 'urn:oasis:names:tc:xacml:1.0:resource:resource-id', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-item-2');


        /**
         * Item mm - retrieve
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-item-retrieve', 'de.escidoc.core.om.service.ItemHandler', 'retrieve', 'info:escidoc/names:aa:1.0:action:retrieve-item', 
  true, true, 'de.escidoc.core.common.exceptions.application.notfound.ItemNotFoundException');

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-item-retrieve', 'urn:oasis:names:tc:xacml:1.0:resource:resource-id', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-item-retrieve');

        /**
         * Item mm - retrieve relations
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-item-retrieve-relations', 'de.escidoc.core.om.service.ItemHandler', 'retrieveRelations', 'info:escidoc/names:aa:1.0:action:retrieve-item', 
  true, true, 'de.escidoc.core.common.exceptions.application.notfound.ItemNotFoundException');

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-item-retrieve-relations', 'urn:oasis:names:tc:xacml:1.0:resource:resource-id', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-item-retrieve-relations');

        /**
         * Item mm - retrieve component
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-item-retrieve-component', 'de.escidoc.core.om.service.ItemHandler', 'retrieveComponent', 
  'info:escidoc/names:aa:1.0:action:retrieve-item', 
  true, true, 'de.escidoc.core.common.exceptions.application.notfound.ItemNotFoundException');

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-item-retrieve-component', 'urn:oasis:names:tc:xacml:1.0:resource:resource-id', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-item-retrieve-component');
INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-item-retrieve-component-2', 'info:escidoc/names:aa:1.0:resource:component-id', '', 1, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-item-retrieve-component');

        /**
         * Item mm - retrieve component properties
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-item-retrieve-component-properties', 'de.escidoc.core.om.service.ItemHandler', 'retrieveComponentProperties', 
  'info:escidoc/names:aa:1.0:action:retrieve-item', 
  true, true, 'de.escidoc.core.common.exceptions.application.notfound.ItemNotFoundException');

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-item-retrieve-component-properties', 'urn:oasis:names:tc:xacml:1.0:resource:resource-id', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-item-retrieve-component-properties');
INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-item-retrieve-component-properties-2', 'info:escidoc/names:aa:1.0:resource:component-id', '', 1, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-item-retrieve-component-properties');


/**
         * Item mm - retrieve component md-records
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-item-retrieve-component-md-records', 'de.escidoc.core.om.service.ItemHandler', 'retrieveComponentMdRecords', 
  'info:escidoc/names:aa:1.0:action:retrieve-item', 
  true, true, 'de.escidoc.core.common.exceptions.application.notfound.ItemNotFoundException');

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-item-retrieve-component-md-records', 'urn:oasis:names:tc:xacml:1.0:resource:resource-id', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-item-retrieve-component-md-records');
INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-item-retrieve-component-md-records-2', 'info:escidoc/names:aa:1.0:resource:component-id', '', 1, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-item-retrieve-component-md-records');

/**
         * Item mm - retrieve component md-record
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-item-retrieve-component-md-record', 'de.escidoc.core.om.service.ItemHandler', 'retrieveComponentMdRecord', 
  'info:escidoc/names:aa:1.0:action:retrieve-item', 
  true, true, 'de.escidoc.core.common.exceptions.application.notfound.ItemNotFoundException');

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-item-retrieve-component-md-record', 'urn:oasis:names:tc:xacml:1.0:resource:resource-id', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-item-retrieve-component-md-record');
INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-item-retrieve-component-md-record-2', 'info:escidoc/names:aa:1.0:resource:component-id', '', 1, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-item-retrieve-component-md-records');



        /**
         * Item mm - retrieve components
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-item-retrieve-components', 'de.escidoc.core.om.service.ItemHandler', 'retrieveComponents', 
  'info:escidoc/names:aa:1.0:action:retrieve-item', 
  true, true, 'de.escidoc.core.common.exceptions.application.notfound.ItemNotFoundException');

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-item-retrieve-components', 'urn:oasis:names:tc:xacml:1.0:resource:resource-id', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-item-retrieve-components');


        /**
         * Item mm - retrieve content
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-item-retrieve-content', 'de.escidoc.core.om.service.ItemHandler', 'retrieveContent', 
  'info:escidoc/names:aa:1.0:action:retrieve-content', 
  true, true, 'de.escidoc.core.common.exceptions.application.notfound.ItemNotFoundException');

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-item-retrieve-content', 'urn:oasis:names:tc:xacml:1.0:resource:resource-id', '', 1, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-item-retrieve-content');

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-item-retrieve-content-2', 'info:escidoc/names:aa:1.0:resource:component:item', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-item-retrieve-content');


        /**
         * Item mm - retrieve metadata record
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-item-retrieve-metadata-record', 'de.escidoc.core.om.service.ItemHandler', 'retrieveMdRecord', 
  'info:escidoc/names:aa:1.0:action:retrieve-item', 
  true, true, 'de.escidoc.core.common.exceptions.application.notfound.ItemNotFoundException');

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-item-retrieve-metadata-record', 'urn:oasis:names:tc:xacml:1.0:resource:resource-id', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-item-retrieve-metadata-record');
INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-item-retrieve-metadata-record-2', 'info:escidoc/names:aa:1.0:resource:metadata-record-id', '', 1, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-item-retrieve-metadata-record');


        /**
         * Item mm - retrieve metadata record CONTENT
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-item-retrieve-metadata-record-content', 'de.escidoc.core.om.service.ItemHandler', 'retrieveMdRecordContent', 
  'info:escidoc/names:aa:1.0:action:retrieve-item', 
  true, true, 'de.escidoc.core.common.exceptions.application.notfound.ItemNotFoundException');

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-item-retrieve-metadata-record-content', 'urn:oasis:names:tc:xacml:1.0:resource:resource-id', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-item-retrieve-metadata-record-content');
INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-item-retrieve-metadata-record-content-2', 'info:escidoc/names:aa:1.0:resource:metadata-record-id', '', 1, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-item-retrieve-metadata-record-content');



     /**
         * Item mm - retrieve dc record CONTENT
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-item-retrieve-dc-record-content', 'de.escidoc.core.om.service.ItemHandler', 'retrieveDcRecordContent', 
  'info:escidoc/names:aa:1.0:action:retrieve-item', 
  true, true, 'de.escidoc.core.common.exceptions.application.notfound.ItemNotFoundException');

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-item-retrieve-dc-record-content', 'urn:oasis:names:tc:xacml:1.0:resource:resource-id', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-item-retrieve-dc-record-content');
INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-item-retrieve-dc-record-content-2', 'info:escidoc/names:aa:1.0:resource:dc', '', 1, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-item-retrieve-dc-record-content');



        /**
         * Item mm - retrieve metadata records
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-item-retrieve-metadata-records', 'de.escidoc.core.om.service.ItemHandler', 'retrieveMdRecords', 
  'info:escidoc/names:aa:1.0:action:retrieve-item', 
  true, true, 'de.escidoc.core.common.exceptions.application.notfound.ItemNotFoundException');

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-item-retrieve-metadata-records', 'urn:oasis:names:tc:xacml:1.0:resource:resource-id', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-item-retrieve-metadata-records');


        /**
         * Item mm - retrieve content stream
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-item-retrieve-content-stream', 'de.escidoc.core.om.service.ItemHandler', 'retrieveContentStream', 
  'info:escidoc/names:aa:1.0:action:retrieve-item', 
  true, true, 'de.escidoc.core.common.exceptions.application.notfound.ItemNotFoundException');

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-item-retrieve-content-stream', 'urn:oasis:names:tc:xacml:1.0:resource:resource-id', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-item-retrieve-content-stream');
INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-item-retrieve-content-stream-2', 'info:escidoc/names:aa:1.0:resource:content-stream-id', '', 1, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-item-retrieve-content-stream');

        /**
         * Item mm - redirect content service
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-item-redirect-content-service', 'de.escidoc.core.om.service.ItemHandler', 'redirectContentService', 'info:escidoc/names:aa:1.0:action:retrieve-item', 
  true, true, 'de.escidoc.core.common.exceptions.application.notfound.ItemNotFoundException');

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-item-redirect-content-service', 'urn:oasis:names:tc:xacml:1.0:resource:resource-id', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-item-redirect-content-service');

        /**
         * Item mm - update content stream
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-item-update-content-stream', 'de.escidoc.core.om.service.ItemHandler', 'updateContentStream', 
  'info:escidoc/names:aa:1.0:action:update-item', 
  true, true, 'de.escidoc.core.common.exceptions.application.notfound.ItemNotFoundException');

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-item-update-content-stream', 'urn:oasis:names:tc:xacml:1.0:resource:resource-id', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-item-update-content-stream');


        /**
         * Item mm - delete content stream
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-item-delete-content-stream', 'de.escidoc.core.om.service.ItemHandler', 'deleteContentStream', 
  'info:escidoc/names:aa:1.0:action:update-item', 
  true, true, 'de.escidoc.core.common.exceptions.application.notfound.ItemNotFoundException');

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-item-delete-content-stream', 'urn:oasis:names:tc:xacml:1.0:resource:resource-id', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-item-delete-content-stream');


        /**
         * Item mm - retrieve content stream content
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-item-retrieve-content-stream-content', 'de.escidoc.core.om.service.ItemHandler', 'retrieveContentStreamContent', 
  'info:escidoc/names:aa:1.0:action:retrieve-item', 
  true, true, 'de.escidoc.core.common.exceptions.application.notfound.ItemNotFoundException');

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-item-retrieve-content-stream-content', 'urn:oasis:names:tc:xacml:1.0:resource:resource-id', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-item-retrieve-content-stream-content');


        /**
         * Item mm - retrieve content streams
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-item-retrieve-content-streams', 'de.escidoc.core.om.service.ItemHandler', 'retrieveContentStreams', 
  'info:escidoc/names:aa:1.0:action:retrieve-item', 
  true, true, 'de.escidoc.core.common.exceptions.application.notfound.ItemNotFoundException');

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-item-retrieve-content-streams', 'urn:oasis:names:tc:xacml:1.0:resource:resource-id', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-item-retrieve-content-streams');


        /**
         * Item mm - update content streams
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-item-update-content-streams', 'de.escidoc.core.om.service.ItemHandler', 'updateContentStreams', 
  'info:escidoc/names:aa:1.0:action:update-item', 
  true, true, 'de.escidoc.core.common.exceptions.application.notfound.ItemNotFoundException');

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-item-update-content-streams', 'urn:oasis:names:tc:xacml:1.0:resource:resource-id', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-item-update-content-streams');


        /**
         * Item mm - delete content streams
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-item-delete-content-streams', 'de.escidoc.core.om.service.ItemHandler', 'deleteContentStreams', 
  'info:escidoc/names:aa:1.0:action:update-item', 
  true, true, 'de.escidoc.core.common.exceptions.application.notfound.ItemNotFoundException');

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-item-delete-content-streams', 'urn:oasis:names:tc:xacml:1.0:resource:resource-id', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-item-delete-content-streams');


        /**
         * Item mm - retrieve properties
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-item-retrieve-properties', 'de.escidoc.core.om.service.ItemHandler', 'retrieveProperties', 
  'info:escidoc/names:aa:1.0:action:retrieve-item', 
  true, true, 'de.escidoc.core.common.exceptions.application.notfound.ItemNotFoundException');

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-item-retrieve-properties', 'urn:oasis:names:tc:xacml:1.0:resource:resource-id', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-item-retrieve-properties');


        /**
         * Item mm - retrieve resources
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-item-retrieve-resources', 'de.escidoc.core.om.service.ItemHandler', 'retrieveResources', 
  'info:escidoc/names:aa:1.0:action:retrieve-item', true, true, 
  'de.escidoc.core.common.exceptions.application.notfound.ItemNotFoundException');

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-item-retrieve-resources', 'urn:oasis:names:tc:xacml:1.0:resource:resource-id', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-item-retrieve-resources');

        /**
         * Item mm - retrieve version-history
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-item-retrieve-resources-version-history', 'de.escidoc.core.om.service.ItemHandler', 'retrieveVersionHistory', 
  'info:escidoc/names:aa:1.0:action:retrieve-item', true, true, 
  'de.escidoc.core.common.exceptions.application.notfound.ItemNotFoundException');

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-item-retrieve-resources-version-history', 'urn:oasis:names:tc:xacml:1.0:resource:resource-id', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-item-retrieve-resources-version-history');


        /**
         * Item mm - retrieve resource
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-item-retrieve-resources-resource', 'de.escidoc.core.om.service.ItemHandler', 'retrieveResource', 
  'info:escidoc/names:aa:1.0:action:retrieve-item', true, true, 
  'de.escidoc.core.common.exceptions.application.notfound.ItemNotFoundException');

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-item-retrieve-resources-resource', 'urn:oasis:names:tc:xacml:1.0:resource:resource-id', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-item-retrieve-resources-resource');

        /**
         * Item mm - update
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-item-update', 'de.escidoc.core.om.service.ItemHandler', 'update', 'info:escidoc/names:aa:1.0:action:update-item', 
  true, true, 'de.escidoc.core.common.exceptions.application.notfound.ItemNotFoundException');

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-item-update-1', 'urn:oasis:names:tc:xacml:1.0:resource:resource-id', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-item-update');

        /**
         * Item - update metadata record content
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-item-md-record-content-update', 'de.escidoc.core.om.service.ItemHandler', 'updateMdRecordContent', 'info:escidoc/names:aa:1.0:action:update-item', 
  true, true, 'de.escidoc.core.common.exceptions.application.notfound.ItemNotFoundException');

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-item-md-record-content-update-1', 'urn:oasis:names:tc:xacml:1.0:resource:resource-id', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-item-md-record-content-update');


  		/**
         * Item mm - addContentRelations
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-item-addContentRelations', 'de.escidoc.core.om.service.ItemHandler', 'addContentRelations', 'info:escidoc/names:aa:1.0:action:update-item', 
  true, true, 'de.escidoc.core.common.exceptions.application.notfound.ItemNotFoundException');

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc:im-item-addContentRelations', 'urn:oasis:names:tc:xacml:1.0:resource:resource-id', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-item-addContentRelations');


		/**
         * Item mm - removeContentRelations
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-item-removeContentRelations', 'de.escidoc.core.om.service.ItemHandler', 'removeContentRelations', 'info:escidoc/names:aa:1.0:action:update-item', 
  true, true, 'de.escidoc.core.common.exceptions.application.notfound.ItemNotFoundException');

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc:im-item-removeContentRelations', 'urn:oasis:names:tc:xacml:1.0:resource:resource-id', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-item-removeContentRelations');

        /**
         * Item mm - create component
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-item-create-component', 'de.escidoc.core.om.service.ItemHandler', 'createComponent', 
  'info:escidoc/names:aa:1.0:action:update-item', 
  true, true, 'de.escidoc.core.common.exceptions.application.notfound.ItemNotFoundException');

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-item-create-component', 'urn:oasis:names:tc:xacml:1.0:resource:resource-id', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-item-create-component');


        /**
         * Item mm - create metadata record
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-item-create-metadata-record', 'de.escidoc.core.om.service.ItemHandler', 'createMetadataRecord', 
  'info:escidoc/names:aa:1.0:action:update-item', 
  true, true, 'de.escidoc.core.common.exceptions.application.notfound.ItemNotFoundException');

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-item-create-metadata-record', 'urn:oasis:names:tc:xacml:1.0:resource:resource-id', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-item-create-metadata-record');

        /**
         * Item mm - delete component
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-item-delete-component', 'de.escidoc.core.om.service.ItemHandler', 'deleteComponent', 
  'info:escidoc/names:aa:1.0:action:update-item', 
  true, true, 'de.escidoc.core.common.exceptions.application.notfound.ItemNotFoundException');

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-item-delete-component', 'urn:oasis:names:tc:xacml:1.0:resource:resource-id', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-item-delete-component');
INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-item-delete-component-2', 'info:escidoc/names:aa:1.0:resource:component-id', '', 1, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-item-delete-component');

        /**
         * Item mm - update component
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-item-update-component', 'de.escidoc.core.om.service.ItemHandler', 'updateComponent', 
  'info:escidoc/names:aa:1.0:action:update-item', 
  true, true, 'de.escidoc.core.common.exceptions.application.notfound.ItemNotFoundException');

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-item-update-component', 'urn:oasis:names:tc:xacml:1.0:resource:resource-id', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-item-update-component');
INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-item-update-component-2', 'info:escidoc/names:aa:1.0:resource:component-id', '', 1, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-item-update-component');


        /**
         * Item mm - update metadata record
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-item-update-metadata-record', 'de.escidoc.core.om.service.ItemHandler', 'updateMetadataRecord', 
  'info:escidoc/names:aa:1.0:action:update-item', 
  true, true, 'de.escidoc.core.common.exceptions.application.notfound.ItemNotFoundException');

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-item-update-metadata-record', 'urn:oasis:names:tc:xacml:1.0:resource:resource-id', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-item-update-metadata-record');
INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-item-update-metadata-record-2', 'info:escidoc/names:aa:1.0:resource:metadata-record-id', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-item-update-metadata-record');


        /**
         * Item mm - submit
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-item-submit', 'de.escidoc.core.om.service.ItemHandler', 'submit', 'info:escidoc/names:aa:1.0:action:submit-item',
  true, true, 'de.escidoc.core.common.exceptions.application.notfound.ItemNotFoundException');

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-item-submit-1', 'urn:oasis:names:tc:xacml:1.0:resource:resource-id', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-item-submit');


        /**
         * Item mm - release
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-item-release', 'de.escidoc.core.om.service.ItemHandler', 'release', 'info:escidoc/names:aa:1.0:action:release-item', 
  true, true, 'de.escidoc.core.common.exceptions.application.notfound.ItemNotFoundException');

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-item-release-1', 'urn:oasis:names:tc:xacml:1.0:resource:resource-id', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-item-release');

        /**
         * Item mm - revise
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-item-revise', 'de.escidoc.core.om.service.ItemHandler', 'revise', 'info:escidoc/names:aa:1.0:action:revise-item', 
  true, true, 'de.escidoc.core.common.exceptions.application.notfound.ItemNotFoundException');

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-item-revise-1', 'urn:oasis:names:tc:xacml:1.0:resource:resource-id', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-item-revise');


        /**
         * Item mm - withdraw
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-item-withdraw', 'de.escidoc.core.om.service.ItemHandler', 'withdraw', 'info:escidoc/names:aa:1.0:action:withdraw-item', 
  true, true, 'de.escidoc.core.common.exceptions.application.notfound.ItemNotFoundException');

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-item-withdraw-1', 'urn:oasis:names:tc:xacml:1.0:resource:resource-id', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-item-withdraw');


        /**
         * Item mm - lock
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-item-lock', 'de.escidoc.core.om.service.ItemHandler', 'lock', 'info:escidoc/names:aa:1.0:action:lock-item',
  true, true, 'de.escidoc.core.common.exceptions.application.notfound.ItemNotFoundException');

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-item-lock-1', 'urn:oasis:names:tc:xacml:1.0:resource:resource-id', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-item-lock');


        /**
         * Item mm - unlock
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource, resource_not_found_exception)
  VALUES ('escidoc:mm-item-unlock', 'de.escidoc.core.om.service.ItemHandler', 'unlock', 'info:escidoc/names:aa:1.0:action:unlock-item',
  true, true, 'de.escidoc.core.common.exceptions.application.notfound.ItemNotFoundException');

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-item-unlock-1', 'urn:oasis:names:tc:xacml:1.0:resource:resource-id', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 0, false, '', 'escidoc:mm-item-unlock');


        /**
         * Item mm - retrieve items
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource)
  VALUES ('escidoc:mm-container-retrieve-items', 'de.escidoc.core.om.service.ItemHandler', 'retrieveItems', 
  'info:escidoc/names:aa:1.0:action:retrieve-objects-filtered', false, true);

  