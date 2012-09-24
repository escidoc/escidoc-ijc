    /** IF CHANGING THE POLICY, PLEASE ADAPT THIS SECTION!!!
      <section>
        <title>UserAccountInspector</title>

        <para>internal id: escidoc:role-user-account-inspector</para>
        <para>A UserAccountInspector is allowed to: <itemizedlist mark="opencircle"
            spacing="compact">
            <listitem>
              <para>retrieve user-accounts.</para>
            </listitem>
          </itemizedlist></para>

        <para>This role is a limited role. 
        It is restricted to a user-account.</para>
      </section>
     */
INSERT INTO aa.escidoc_role 
    (id, role_name, description, creator_id, creation_date, modified_by_id, last_modification_date) 
    VALUES 
    ('escidoc:role-user-account-inspector', 'User-Account-Inspector', NULL, 'escidoc:exuser1', 
    CURRENT_TIMESTAMP, 'escidoc:exuser1', CURRENT_TIMESTAMP);

INSERT INTO aa.scope_def 
    (id, role_id, object_type, attribute_id) 
    VALUES 
    ('escidoc:scope-def-role-user-account-inspector', 'escidoc:role-user-account-inspector', 'user-account', 
    'urn:oasis:names:tc:xacml:1.0:resource:resource-id');

INSERT INTO aa.escidoc_policies (id, role_id, xml) VALUES ('escidoc:policy-user-account-inspector', 'escidoc:role-user-account-inspector', '<Policy PolicyId="User-Account-Inspector-policy" RuleCombiningAlgId="urn:oasis:names:tc:xacml:1.0:rule-combining-algorithm:ordered-permit-overrides">
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
                        info:escidoc/names:aa:1.0:action:retrieve-user-account
                   </AttributeValue>
                    <ActionAttributeDesignator AttributeId="urn:oasis:names:tc:xacml:1.0:action:action-id" DataType="http://www.w3.org/2001/XMLSchema#string"/>
                </ActionMatch>
            </Action>
        </Actions>
    </Target>
  <Rule RuleId="Depositor-policy-rule-1a" Effect="Permit">
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


  
