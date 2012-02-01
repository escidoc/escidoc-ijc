   /**
    * Adminhandler method mappings
    */

        /**
         * DeleteObjects
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource)
  VALUES ('escidoc:mm-adm-delete-objects', 'de.escidoc.core.adm.service.AdminHandler', 'deleteObjects', 'info:escidoc/names:aa:1.0:action:delete-objects',
  true, true);

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-adm-delete-objects', 'info:escidoc/names:aa:1.0:resource:object-type-new', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 3, false, 'none', 'escidoc:mm-adm-delete-objects');


        /**
         * GetPurgeStatus
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource)
  VALUES ('escidoc:mm-adm-get-purge-status', 'de.escidoc.core.adm.service.AdminHandler', 'getPurgeStatus', 'info:escidoc/names:aa:1.0:action:get-purge-status',
  true, true);

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-adm-get-purge-status', 'info:escidoc/names:aa:1.0:resource:object-type-new', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 3, false, 'none', 'escidoc:mm-adm-get-purge-status');

        /**
         * GetRecacheStatus
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource)
  VALUES ('escidoc:mm-adm-get-recache-status', 'de.escidoc.core.adm.service.AdminHandler', 'getRecacheStatus', 'info:escidoc/names:aa:1.0:action:get-recache-status',
  true, true);

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-adm-get-recache-status', 'info:escidoc/names:aa:1.0:resource:object-type-new', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 3, false, 'none', 'escidoc:mm-adm-get-recache-status');

        /**
         * GetReindexStatus
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource)
  VALUES ('escidoc:mm-adm-get-reindex-status', 'de.escidoc.core.adm.service.AdminHandler', 'getReindexStatus', 'info:escidoc/names:aa:1.0:action:get-reindex-status',
  true, true);

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-adm-get-reindex-status', 'info:escidoc/names:aa:1.0:resource:object-type-new', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 3, false, 'none', 'escidoc:mm-adm-get-reindex-status');

        /**
         * DecreaseReindexStatus
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource)
  VALUES ('escidoc:mm-adm-decrease-reindex-status', 'de.escidoc.core.adm.service.AdminHandler', 'decreaseReindexStatus', 'info:escidoc/names:aa:1.0:action:decrease-reindex-status',
  true, true);

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-adm-decrease-reindex-status', 'info:escidoc/names:aa:1.0:resource:object-type-new', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 3, false, 'none', 'escidoc:mm-adm-decrease-reindex-status');

        /**
         * Recache
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource)
  VALUES ('escidoc:mm-adm-recache', 'de.escidoc.core.adm.service.AdminHandler', 'recache', 'info:escidoc/names:aa:1.0:action:recache', 
  true, true);

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-adm-recache', 'info:escidoc/names:aa:1.0:resource:object-type-new', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 3, false, 'none', 'escidoc:mm-adm-recache');

        /**
         * Reindex
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource)
  VALUES ('escidoc:mm-adm-reindex', 'de.escidoc.core.adm.service.AdminHandler', 'reindex', 'info:escidoc/names:aa:1.0:action:reindex',
  true, true);

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-adm-reindex', 'info:escidoc/names:aa:1.0:resource:object-type-new', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 3, false, 'none', 'escidoc:mm-adm-reindex');

        /**
         * getRepositoryInfo
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource)
  VALUES ('escidoc:mm-adm-get-repository-info', 'de.escidoc.core.adm.service.AdminHandler', 'getRepositoryInfo', 'info:escidoc/names:aa:1.0:action:get-repository-info',
  true, true);									

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-adm-get-repository-info', 'info:escidoc/names:aa:1.0:resource:object-type-new', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 3, false, 'none', 'escidoc:mm-adm-get-repository-info');

 
        /**
         * Load Examples
         */
INSERT INTO aa.method_mappings (id, class_name, method_name, action_name, exec_before, single_resource)
  VALUES ('escidoc:mm-adm-load-examples', 'de.escidoc.core.adm.service.AdminHandler', 'loadExamples', 'info:escidoc/names:aa:1.0:action:load-examples',
  true, true);

INSERT INTO aa.invocation_mappings (id, attribute_id, path, position, attribute_type, mapping_type, multi_value, value, method_mapping)
  VALUES ('escidoc-im-adm-load-examples', 'info:escidoc/names:aa:1.0:resource:object-type-new', '', 0, 
          'http://www.w3.org/2001/XMLSchema#string', 3, false, 'none', 'escidoc:mm-adm-load-examples');

