    /** IF CHANGING THE POLICY, PLEASE ADAPT THIS SECTION!!!
      <section>
        <title>System Inspector</title>
        
        <para>internal id: escidoc:role-system-inspector</para>
        <para>The System Inspector is the eSciDoc read-only super user that is
        allowed to retrieve any resource object, but he/she is not allowed to
        create or update objects.</para>
      </section>
     */
INSERT INTO aa.escidoc_role
    (id, role_name, creator_id, creation_date, modified_by_id, last_modification_date)
     VALUES
    ('escidoc:role-system-inspector', 'System-Inspector', 'escidoc:exuser1', CURRENT_TIMESTAMP, 'escidoc:exuser1',
    CURRENT_TIMESTAMP);
    
INSERT INTO aa.escidoc_policies
  (id, role_id, xml)
     VALUES
  ('escidoc:policy-system-inspector', 'escidoc:role-system-inspector',
'<Policy PolicyId="System-Inspector-policy" RuleCombiningAlgId="urn:oasis:names:tc:xacml:1.0:rule-combining-algorithm:ordered-permit-overrides">
  <Target>
    <Subjects>
      <AnySubject/>
    </Subjects>
    <Resources>
      <AnyResource/>
    </Resources>
    <Actions>
      <Action>
        <ActionMatch MatchId="info:escidoc/names:aa:1.0:function:string-contains">
          <AttributeValue DataType="http://www.w3.org/2001/XMLSchema#string">
            info:escidoc/names:aa:1.0:action:retrieve-role 
            info:escidoc/names:aa:1.0:action:retrieve-roles 
            info:escidoc/names:aa:1.0:action:evaluate 
            info:escidoc/names:aa:1.0:action:retrieve-user-account 
            info:escidoc/names:aa:1.0:action:retrieve-grant 
            info:escidoc/names:aa:1.0:action:retrieve-user-group 
            info:escidoc/names:aa:1.0:action:retrieve-user-group-grant 
            info:escidoc/names:aa:1.0:action:logout 
            info:escidoc/names:aa:1.0:action:retrieve-unsecured-actions 
            info:escidoc/names:aa:1.0:action:retrieve-metadata-schema 
            info:escidoc/names:aa:1.0:action:retrieve-container 
            info:escidoc/names:aa:1.0:action:retrieve-content-model 
            info:escidoc/names:aa:1.0:action:retrieve-context 
            info:escidoc/names:aa:1.0:action:retrieve-item 
            info:escidoc/names:aa:1.0:action:retrieve-content 
            info:escidoc/names:aa:1.0:action:retrieve-toc 
            info:escidoc/names:aa:1.0:action:retrieve-content-relation 
            info:escidoc/names:aa:1.0:action:query-semantic-store 
            info:escidoc/names:aa:1.0:action:retrieve-xml-schema 
            info:escidoc/names:aa:1.0:action:retrieve-organizational-unit 
            info:escidoc/names:aa:1.0:action:retrieve-children-of-organizational-unit 
            info:escidoc/names:aa:1.0:action:retrieve-parents-of-organizational-unit 
            info:escidoc/names:aa:1.0:action:retrieve-aggregation-definition 
            info:escidoc/names:aa:1.0:action:retrieve-report-definition 
            info:escidoc/names:aa:1.0:action:retrieve-report 
            info:escidoc/names:aa:1.0:action:retrieve-scope 
            info:escidoc/names:aa:1.0:action:retrieve-staging-file 
          </AttributeValue>
          <ActionAttributeDesignator AttributeId="urn:oasis:names:tc:xacml:1.0:action:action-id" DataType="http://www.w3.org/2001/XMLSchema#string"/>
        </ActionMatch>
      </Action>
    </Actions>
  </Target>
  <Rule RuleId="System-Inspector-policy-rule-0" Effect="Permit">
    <Target>
      <Subjects>
        <AnySubject/>
      </Subjects>
      <Resources>
        <AnyResource/>
      </Resources>
      <Actions>
        <AnyAction/>
      </Actions>
    </Target>
  </Rule>
</Policy>');
