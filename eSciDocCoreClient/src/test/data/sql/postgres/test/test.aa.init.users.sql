/**
 * System-Administrator: 
 *      -No scope-defs, role valid for all object-types
 *      -May do everything
 * System-Inspector: 
 *      -No Scope-Defs, role valid for all object-types
 *      -May do all retrieve-operations
 * Administrator: 
 *      -Scope-Defs on item, container, context with attribute context
 *      -Scope-defs on grant with attribute assigned-on + context
 *      -retrieve, update, open, and close the context,
 *      -create, retrieve and revoke grants for objects,
 *      -retrieve, revise, and withdraw containers and items
 *      -retrieve user-accounts.
 * Author: 
 *      -Scope-Defs on container, item with attribute collection
 *      -Scope-Def on staging-file with no attributes
 *      -May create and retrieve container and items
 *      -May update,delete... items and containers in status pending or in-revision
 *      -May retrieve content
 * Collaborator: 
 *      -Scope-Def on item with attributes context, container, item, component
 *      -Scope-Def on component with attribute component
 *      -May retrieve items and content
 * Audience: 
 *      -Scope-Def on component with attribute component
 *      -May retrieve content with visibility='audience'
 * Depositor: 
 *      -Scope-Defs on context, container, item, component with attribute context
 *      -Scope-Def on staging-file with no attributes
 *      -May create containers and items
 *      -May update, delete items + containers in status pending or in-revision he created
 *      -May submit items + containers
 *      -May retrieve the items, containers he created
 * Ingester: 
 *      -No Scope-Defs, role valid for all object-types
 *      -May ingest
 * MD-Editor: 
 *      -Scope-Defs on context, container, item, component with attribute context
 *      -May retrieve, update and lock containers + items in state submitted + released
 *      -May submit an item he modified
 * Moderator: 
 *      -Scope-Defs on context, container, item, component with attribute context
 *      -May retrieve + release containers + items
 * Privileged-Viewer: 
 *      -Scope-Def on component with attribute context
 *      -May retrieve content
 * Statistics-Editor: 
 *      -Scope-Defs on scope, aggregation-definition, report-definition, statistic-data with attribute scope
 *      -May create, retrieve, update + delete scopes, aggregation-definitions, report-definitions + statistic-data
 * Statistics-Reader: 
 *      -Scope-def on report with attribute scope
 *      -May retrieve Reports
 */

/**
 * Used persistent objects:
 * context: /ir/context/escidoc:persistent3
 * orgUnit: /oum/organizational-unit/escidoc:persistent31
 */
/**
 * User initialization
 */
    /**
     * System Administrator user (Super user).
     */   
INSERT INTO aa.user_account
	(id, active, name, loginName, password, creator_id, creation_date, modified_by_id, last_modification_date)
	 VALUES
	('escidoc:testsystemadministrator',
	true,
    'Test System Administrator User',
    'testsystemadministrator',
    'escidoc',
    'escidoc:exuser1',
    CURRENT_TIMESTAMP,
    'escidoc:exuser1',
    CURRENT_TIMESTAMP);
    
INSERT INTO aa.user_attribute
    (id, user_id, name, value, internal)
     VALUES
    ('escidoc:testsystemadministratoremailattribute', 'escidoc:testsystemadministrator','email', 'test.systemadministrator@user', 'TRUE');

INSERT INTO aa.user_login_data
    (id, user_id, handle, expiryts)
     VALUES
    ('escidoc:testsystemadministrator', 'escidoc:testsystemadministrator', 'testsystemadministrator', 1999999999999);

INSERT INTO aa.role_grant 
    (id, user_id, role_id, creator_id, creation_date)
     VALUES
    ('escidoc:testsystemadministratorgrant1', 'escidoc:testsystemadministrator', 'escidoc:role-system-administrator', 'escidoc:exuser1', CURRENT_TIMESTAMP);



    /**
     * System Administrator user1 (Super user1).
     */   
INSERT INTO aa.user_account
    (id, active, name, loginName, password, creator_id, creation_date, modified_by_id, last_modification_date)
     VALUES
    ('escidoc:testsystemadministrator1',
    true,
    'Test System Administrator User1',
    'testsystemadministrator1',
    'escidoc',
    'escidoc:exuser1',
    CURRENT_TIMESTAMP,
    'escidoc:exuser1',
    CURRENT_TIMESTAMP);
    
INSERT INTO aa.user_attribute
    (id, user_id, name, value, internal)
     VALUES
    ('escidoc:testsystemadministratoremailattribute1', 'escidoc:testsystemadministrator1','email', 'test.systemadministrator@user', 'TRUE');

INSERT INTO aa.user_login_data
    (id, user_id, handle, expiryts)
     VALUES
    ('escidoc:testsystemadministrator1', 'escidoc:testsystemadministrator1', 'testsystemadministrator1', 1999999999999);

INSERT INTO aa.role_grant 
    (id, user_id, role_id, creator_id, creation_date)
     VALUES
    ('escidoc:testsystemadministratorgrant11', 'escidoc:testsystemadministrator1', 'escidoc:role-system-administrator', 'escidoc:exuser1', CURRENT_TIMESTAMP);



    /**
     * Administrator user.
     */   
INSERT INTO aa.user_account
    (id, active, name, loginName, password, creator_id, creation_date, modified_by_id, last_modification_date)
     VALUES
    ('escidoc:testadministrator',
    true,
    'Test Administrator User',
    'testadministrator',
    'escidoc',
    'escidoc:exuser1',
    CURRENT_TIMESTAMP,
    'escidoc:exuser1',
    CURRENT_TIMESTAMP);
    
INSERT INTO aa.user_attribute
    (id, user_id, name, value, internal)
     VALUES
    ('escidoc:testadministratoremailattribute', 'escidoc:testadministrator','email', 'test.administrator@user', 'TRUE');

INSERT INTO aa.user_login_data
    (id, user_id, handle, expiryts)
     VALUES
    ('escidoc:testadministrator', 'escidoc:testadministrator', 'testadministrator', 1999999999999);

INSERT INTO aa.role_grant 
    (id, user_id, role_id, object_id, object_title, object_href, creator_id, creation_date)
     VALUES
    ('escidoc:testadministratorgrant1', 
    'escidoc:testadministrator', 
    'escidoc:role-administrator', 
    'escidoc:persistent3', 
    'PubMan Test Collection', 
    '/ir/context/escidoc:persistent3',
    'escidoc:exuser1', 
    CURRENT_TIMESTAMP);



    /**
     * Audience user.
     */   
INSERT INTO aa.user_account
    (id, active, name, loginName, password, creator_id, creation_date, modified_by_id, last_modification_date)
     VALUES
    ('escidoc:testaudience',
    true,
    'Test Audience User',
    'testaudience',
    'escidoc',
    'escidoc:exuser1',
    CURRENT_TIMESTAMP,
    'escidoc:exuser1',
    CURRENT_TIMESTAMP);
    
INSERT INTO aa.user_attribute
    (id, user_id, name, value, internal)
     VALUES
    ('escidoc:testaudienceemailattribute', 'escidoc:testaudience','email', 'test.audience@user', 'TRUE');

INSERT INTO aa.user_login_data
    (id, user_id, handle, expiryts)
     VALUES
    ('escidoc:testaudience', 'escidoc:testaudience', 'testaudience', 1999999999999);



    /**
     * Author user.
     */   
INSERT INTO aa.user_account
    (id, active, name, loginName, password, creator_id, creation_date, modified_by_id, last_modification_date)
     VALUES
    ('escidoc:testauthor',
    true,
    'Test Author User',
    'testauthor',
    'escidoc',
    'escidoc:exuser1',
    CURRENT_TIMESTAMP,
    'escidoc:exuser1',
    CURRENT_TIMESTAMP);
    
INSERT INTO aa.user_attribute
    (id, user_id, name, value, internal)
     VALUES
    ('escidoc:testauthoremailattribute', 'escidoc:testauthor','email', 'test.author@user', 'TRUE');

INSERT INTO aa.user_login_data
    (id, user_id, handle, expiryts)
     VALUES
    ('escidoc:testauthor', 'escidoc:testauthor', 'testauthor', 1999999999999);



    /**
     * Collaborator user.
     */   
INSERT INTO aa.user_account
    (id, active, name, loginName, password, creator_id, creation_date, modified_by_id, last_modification_date)
     VALUES
    ('escidoc:testcollaborator',
    true,
    'Test Collaborator User',
    'testcollaborator',
    'escidoc',
    'escidoc:exuser1',
    CURRENT_TIMESTAMP,
    'escidoc:exuser1',
    CURRENT_TIMESTAMP);
    
INSERT INTO aa.user_attribute
    (id, user_id, name, value, internal)
     VALUES
    ('escidoc:testcollaboratoremailattribute', 'escidoc:testcollaborator','email', 'test.collaborator@user', 'TRUE');

INSERT INTO aa.user_login_data
    (id, user_id, handle, expiryts)
     VALUES
    ('escidoc:testcollaborator', 'escidoc:testcollaborator', 'testcollaborator', 1999999999999);



    /**
     * Depositor user.
     */   
INSERT INTO aa.user_account
    (id, active, name, loginName, password, creator_id, creation_date, modified_by_id, last_modification_date)
     VALUES
    ('escidoc:testdepositor',
    true,
    'Test Depositor User',
    'testdepositor',
    'escidoc',
    'escidoc:exuser1',
    CURRENT_TIMESTAMP,
    'escidoc:exuser1',
    CURRENT_TIMESTAMP);
    
INSERT INTO aa.user_attribute
    (id, user_id, name, value, internal)
     VALUES
    ('escidoc:testdepositoremailattribute', 'escidoc:testdepositor','email', 'test.depositor@user', 'TRUE');

INSERT INTO aa.user_login_data
    (id, user_id, handle, expiryts)
     VALUES
    ('escidoc:testdepositor', 'escidoc:testdepositor', 'testdepositor', 1999999999999);

INSERT INTO aa.role_grant 
    (id, user_id, role_id, object_id, object_title, object_href, creator_id, creation_date)
     VALUES
    ('escidoc:testdepositorgrant1', 
    'escidoc:testdepositor', 
    'escidoc:role-depositor', 
    'escidoc:persistent3', 
    'PubMan Test Collection', 
    '/ir/context/escidoc:persistent3',
    'escidoc:exuser1', 
    CURRENT_TIMESTAMP);



    /**
     * Depositor user2.
     */   
INSERT INTO aa.user_account
    (id, active, name, loginName, password, creator_id, creation_date, modified_by_id, last_modification_date)
     VALUES
    ('escidoc:testdepositor2',
    true,
    'Test Depositor User2',
    'testdepositor2',
    'escidoc',
    'escidoc:exuser1',
    CURRENT_TIMESTAMP,
    'escidoc:exuser1',
    CURRENT_TIMESTAMP);
    
INSERT INTO aa.user_attribute
    (id, user_id, name, value, internal)
     VALUES
    ('escidoc:testdepositor2emailattribute', 'escidoc:testdepositor2','email', 'test.depositor2@user', 'TRUE');

INSERT INTO aa.user_login_data
    (id, user_id, handle, expiryts)
     VALUES
    ('escidoc:testdepositor2', 'escidoc:testdepositor2', 'testdepositor2', 1999999999999);

INSERT INTO aa.role_grant 
    (id, user_id, role_id, object_id, object_title, object_href, creator_id, creation_date)
     VALUES
    ('escidoc:testdepositorgrant2', 
    'escidoc:testdepositor2', 
    'escidoc:role-depositor', 
    'escidoc:persistent3', 
    'PubMan Test Collection', 
    '/ir/context/escidoc:persistent3',
    'escidoc:exuser1', 
    CURRENT_TIMESTAMP);



    /**
     * Ingester user.
     */   
INSERT INTO aa.user_account
    (id, active, name, loginName, password, creator_id, creation_date, modified_by_id, last_modification_date)
     VALUES
    ('escidoc:testingester',
    true,
    'Test Ingester User',
    'testingester',
    'escidoc',
    'escidoc:exuser1',
    CURRENT_TIMESTAMP,
    'escidoc:exuser1',
    CURRENT_TIMESTAMP);
    
INSERT INTO aa.user_attribute
    (id, user_id, name, value, internal)
     VALUES
    ('escidoc:testingesteremailattribute', 'escidoc:testingester','email', 'test.ingester@user', 'TRUE');

INSERT INTO aa.user_login_data
    (id, user_id, handle, expiryts)
     VALUES
    ('escidoc:testingester', 'escidoc:testingester', 'testingester', 1999999999999);



    /**
     * MD-Editor user.
     */   
INSERT INTO aa.user_account
    (id, active, name, loginName, password, creator_id, creation_date, modified_by_id, last_modification_date)
     VALUES
    ('escidoc:testmdeditor',
    true,
    'Test Md-Editor User',
    'testmdeditor',
    'escidoc',
    'escidoc:exuser1',
    CURRENT_TIMESTAMP,
    'escidoc:exuser1',
    CURRENT_TIMESTAMP);
    
INSERT INTO aa.user_attribute
    (id, user_id, name, value, internal)
     VALUES
    ('escidoc:testsymdeditoremailattribute', 'escidoc:testmdeditor','email', 'test.mdeditor@user', 'TRUE');

INSERT INTO aa.user_login_data
    (id, user_id, handle, expiryts)
     VALUES
    ('escidoc:testmdeditor', 'escidoc:testmdeditor', 'testmdeditor', 1999999999999);

INSERT INTO aa.role_grant 
    (id, user_id, role_id, object_id, object_title, object_href, creator_id, creation_date)
     VALUES
    ('escidoc:testmdeditorgrant1', 
    'escidoc:testmdeditor', 
    'escidoc:role-md-editor', 
    'escidoc:persistent3', 
    'PubMan Test Collection', 
    '/ir/context/escidoc:persistent3',
    'escidoc:exuser1', 
    CURRENT_TIMESTAMP);



    /**
     * Moderator user.
     */   
INSERT INTO aa.user_account
    (id, active, name, loginName, password, creator_id, creation_date, modified_by_id, last_modification_date)
     VALUES
    ('escidoc:testmoderator',
    true,
    'Test Moderator User',
    'testmoderator',
    'escidoc',
    'escidoc:exuser1',
    CURRENT_TIMESTAMP,
    'escidoc:exuser1',
    CURRENT_TIMESTAMP);
    
INSERT INTO aa.user_attribute
    (id, user_id, name, value, internal)
     VALUES
    ('escidoc:testmoderatoremailattribute', 'escidoc:testmoderator','email', 'test.moderator@user', 'TRUE');

INSERT INTO aa.user_login_data
    (id, user_id, handle, expiryts)
     VALUES
    ('escidoc:testmoderator', 'escidoc:testmoderator', 'testmoderator', 1999999999999);

INSERT INTO aa.role_grant 
    (id, user_id, role_id, object_id, object_title, object_href, creator_id, creation_date)
     VALUES
    ('escidoc:testmoderatorgrant1', 
    'escidoc:testmoderator', 
    'escidoc:role-moderator', 
    'escidoc:persistent3', 
    'PubMan Test Collection', 
    '/ir/context/escidoc:persistent3',
    'escidoc:exuser1', 
    CURRENT_TIMESTAMP);



    /**
     * Privileged-Viewer user.
     */   
INSERT INTO aa.user_account
    (id, active, name, loginName, password, creator_id, creation_date, modified_by_id, last_modification_date)
     VALUES
    ('escidoc:testprivilegedviewer',
    true,
    'Test Privileged-Viewer User',
    'testprivilegedviewer',
    'escidoc',
    'escidoc:exuser1',
    CURRENT_TIMESTAMP,
    'escidoc:exuser1',
    CURRENT_TIMESTAMP);
    
INSERT INTO aa.user_attribute
    (id, user_id, name, value, internal)
     VALUES
    ('escidoc:testprivilegedvieweremailattribute', 'escidoc:testprivilegedviewer','email', 'test.privilegedviewer@user', 'TRUE');

INSERT INTO aa.user_login_data
    (id, user_id, handle, expiryts)
     VALUES
    ('escidoc:testprivilegedviewer', 'escidoc:testprivilegedviewer', 'testprivilegedviewer', 1999999999999);

INSERT INTO aa.role_grant 
    (id, user_id, role_id, object_id, object_title, object_href, creator_id, creation_date)
     VALUES
    ('escidoc:testprivilegedviewergrant1', 
    'escidoc:testprivilegedviewer', 
    'escidoc:role-privileged-viewer', 
    'escidoc:persistent3', 
    'PubMan Test Collection', 
    '/ir/context/escidoc:persistent3',
    'escidoc:exuser1', 
    CURRENT_TIMESTAMP);



    /**
     * Statistics-Editor user.
     */   
INSERT INTO aa.user_account
    (id, active, name, loginName, password, creator_id, creation_date, modified_by_id, last_modification_date)
     VALUES
    ('escidoc:teststatisticseditor',
    true,
    'Test Statistics-Editor User',
    'teststatisticseditor',
    'escidoc',
    'escidoc:exuser1',
    CURRENT_TIMESTAMP,
    'escidoc:exuser1',
    CURRENT_TIMESTAMP);
    
INSERT INTO aa.user_attribute
    (id, user_id, name, value, internal)
     VALUES
    ('escidoc:teststatisticseditoremailattribute', 'escidoc:teststatisticseditor','email', 'test.statisticseditor@user', 'TRUE');

INSERT INTO aa.user_login_data
    (id, user_id, handle, expiryts)
     VALUES
    ('escidoc:teststatisticseditor', 'escidoc:teststatisticseditor', 'teststatisticseditor', 1999999999999);

INSERT INTO aa.role_grant 
    (id, user_id, role_id, object_id, object_title, object_href, creator_id, creation_date)
     VALUES
    ('escidoc:teststatisticseditorgrant1', 
    'escidoc:teststatisticseditor', 
    'escidoc:role-statistics-editor', 
    'escidoc:scope3', 
    'Scope with id escidoc:scope3', 
    '/statistic/scope/escidoc:scope3',
    'escidoc:exuser1', 
    CURRENT_TIMESTAMP);



    /**
     * Statistics-Reader user.
     */   
INSERT INTO aa.user_account
    (id, active, name, loginName, password, creator_id, creation_date, modified_by_id, last_modification_date)
     VALUES
    ('escidoc:teststatisticsreader',
    true,
    'Test Statistics-Reader User',
    'teststatisticsreader',
    'escidoc',
    'escidoc:exuser1',
    CURRENT_TIMESTAMP,
    'escidoc:exuser1',
    CURRENT_TIMESTAMP);
    
INSERT INTO aa.user_attribute
    (id, user_id, name, value, internal)
     VALUES
    ('escidoc:teststatisticsreaderemailattribute', 'escidoc:teststatisticsreader','email', 'test.statisticsreader@user', 'TRUE');

INSERT INTO aa.user_login_data
    (id, user_id, handle, expiryts)
     VALUES
    ('escidoc:teststatisticsreader', 'escidoc:teststatisticsreader', 'teststatisticsreader', 1999999999999);

INSERT INTO aa.role_grant 
    (id, user_id, role_id, object_id, object_title, object_href, creator_id, creation_date)
     VALUES
    ('escidoc:teststatisticsreadergrant1', 
    'escidoc:teststatisticsreader', 
    'escidoc:role-statistics-reader', 
    'escidoc:scope3', 
    'Scope with id escidoc:scope3', 
    '/statistic/scope/escidoc:scope3',
    'escidoc:exuser1', 
    CURRENT_TIMESTAMP);



    /**
     * System-Inspector user.
     */   
INSERT INTO aa.user_account
    (id, active, name, loginName, password, creator_id, creation_date, modified_by_id, last_modification_date)
     VALUES
    ('escidoc:testsysteminspector',
    true,
    'Test System-Inspector User',
    'testsysteminspector',
    'escidoc',
    'escidoc:exuser1',
    CURRENT_TIMESTAMP,
    'escidoc:exuser1',
    CURRENT_TIMESTAMP);
    
INSERT INTO aa.user_attribute
    (id, user_id, name, value, internal)
     VALUES
    ('escidoc:testsysteminspectoremailattribute', 'escidoc:testsysteminspector','email', 'test.systeminspector@user', 'TRUE');

INSERT INTO aa.user_login_data
    (id, user_id, handle, expiryts)
     VALUES
    ('escidoc:testsysteminspector', 'escidoc:testsysteminspector', 'testsysteminspector', 1999999999999);


    /**
     * Grant Test user.
     */   
INSERT INTO aa.user_account
    (id, active, name, loginName, password, creator_id, creation_date, modified_by_id, last_modification_date)
     VALUES
    ('escidoc:test',
    true,
    'Test User',
    'test',
    'escidoc',
    'escidoc:exuser1',
    CURRENT_TIMESTAMP,
    'escidoc:exuser1',
    CURRENT_TIMESTAMP);
    
INSERT INTO aa.user_attribute
    (id, user_id, name, value, internal)
     VALUES
    ('escidoc:testemailattribute', 'escidoc:test','email', 'test.test@user', 'TRUE');

INSERT INTO aa.user_login_data
    (id, user_id, handle, expiryts)
     VALUES
    ('escidoc:test', 'escidoc:test', 'test', 1999999999999);

INSERT INTO aa.user_attribute
    (id, user_id, name, value, internal)
     VALUES
    ('escidoc:testouattribute', 'escidoc:test','o', 'escidoc:persistent31', 'TRUE');



    /**
     * Grant Test user1.
     */   
INSERT INTO aa.user_account
    (id, active, name, loginName, password, creator_id, creation_date, modified_by_id, last_modification_date)
     VALUES
    ('escidoc:test1',
    true,
    'Test User1',
    'test1',
    'escidoc',
    'escidoc:exuser1',
    CURRENT_TIMESTAMP,
    'escidoc:exuser1',
    CURRENT_TIMESTAMP);
    
INSERT INTO aa.user_attribute
    (id, user_id, name, value, internal)
     VALUES
    ('escidoc:test1emailattribute', 'escidoc:test1','email', 'test.test1@user', 'TRUE');

INSERT INTO aa.user_login_data
    (id, user_id, handle, expiryts)
     VALUES
    ('escidoc:test1', 'escidoc:test1', 'test1', 1999999999999);

