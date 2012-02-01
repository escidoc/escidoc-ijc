    /** IF CHANGING THE POLICY, PLEASE ADAPT THIS SECTION!!!
      <section>
        <title>UserAccountAdministrator</title>

        <para>internal id: escidoc:role-user-account-administrator</para>
        <para>A UserAccountAdministrator is allowed to: <itemizedlist mark="opencircle"
            spacing="compact">
            <listitem>
              <para>create user-accounts.</para>
            </listitem>
            <listitem>
              <para>retrieve, update, activate, deactivate user-accounts that have same org-unit as requesting user.</para>
            </listitem>
            <listitem>
              <para>retrieve grants of user-accounts that have same org-unit as requesting user.</para>
            </listitem>
            <listitem>
              <para>revoke grants of user-accounts that have same org-unit as requesting user and is not own user-account
                   or user has created the user-account.</para>
            </listitem>
          </itemizedlist></para>

        <para>This role is unlimited (no scope-definitions).</para>
      </section>
     */
INSERT INTO aa.escidoc_role 
    (id, role_name, description, creator_id, creation_date, modified_by_id, last_modification_date) 
    VALUES ('escidoc:role-user-account-administrator', 'User-Account-Administrator', NULL, 'escidoc:exuser1', 
    CURRENT_TIMESTAMP, 'escidoc:exuser1', CURRENT_TIMESTAMP);

INSERT INTO aa.escidoc_policies 
    (id, role_id, xml) 
    VALUES 
    ('escidoc:policy-user-account-administrator', 'escidoc:role-user-account-administrator', 
    '<Policy PolicyId="User-Account-Administrator-policy" RuleCombiningAlgId="urn:oasis:names:tc:xacml:1.0:rule-combining-algorithm:ordered-permit-overrides">
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
                            info:escidoc/names:aa:1.0:action:create-user-account 
                            info:escidoc/names:aa:1.0:action:retrieve-user-account 
                            info:escidoc/names:aa:1.0:action:update-user-account 
                            info:escidoc/names:aa:1.0:action:activate-user-account 
                            info:escidoc/names:aa:1.0:action:deactivate-user-account 
                            info:escidoc/names:aa:1.0:action:revoke-grant 
                            info:escidoc/names:aa:1.0:action:retrieve-grant  
                    </AttributeValue>
                    <ActionAttributeDesignator AttributeId="urn:oasis:names:tc:xacml:1.0:action:action-id" DataType="http://www.w3.org/2001/XMLSchema#string"/>
                </ActionMatch>
            </Action>
        </Actions>
    </Target>
    <Rule RuleId="User-Account-Administrator-policy-rule-1" Effect="Permit">
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
                            info:escidoc/names:aa:1.0:action:create-user-account 
                          </AttributeValue>
                        <ActionAttributeDesignator AttributeId="urn:oasis:names:tc:xacml:1.0:action:action-id" DataType="http://www.w3.org/2001/XMLSchema#string"/>
                    </ActionMatch>
                </Action>
            </Actions>
        </Target>
    </Rule>
    <Rule RuleId="User-Account-Administrator-policy-rule-2" Effect="Permit">
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
                            info:escidoc/names:aa:1.0:action:update-user-account 
                            info:escidoc/names:aa:1.0:action:activate-user-account 
                            info:escidoc/names:aa:1.0:action:deactivate-user-account 
                            info:escidoc/names:aa:1.0:action:retrieve-grant  
                    </AttributeValue>
                        <ActionAttributeDesignator AttributeId="urn:oasis:names:tc:xacml:1.0:action:action-id" DataType="http://www.w3.org/2001/XMLSchema#string"/>
                    </ActionMatch>
                </Action>
            </Actions>
        </Target>
        <Condition FunctionId="urn:oasis:names:tc:xacml:1.0:function:string-at-least-one-member-of">
            <SubjectAttributeDesignator SubjectCategory="urn:oasis:names:tc:xacml:1.0:subject-category:access-subject" AttributeId="info:escidoc/names:aa:1.0:subject:organizational-unit" DataType="http://www.w3.org/2001/XMLSchema#string"/>
            <ResourceAttributeDesignator AttributeId="info:escidoc/names:aa:1.0:resource:user-account:organizational-unit" DataType="http://www.w3.org/2001/XMLSchema#string"/>
        </Condition>
    </Rule>
    <Rule RuleId="User-Account-Administrator-policy-rule-3" Effect="Permit">
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
                            info:escidoc/names:aa:1.0:action:revoke-grant
                          </AttributeValue>
                        <ActionAttributeDesignator AttributeId="urn:oasis:names:tc:xacml:1.0:action:action-id" DataType="http://www.w3.org/2001/XMLSchema#string"/>
                    </ActionMatch>
                </Action>
            </Actions>
        </Target>
        <Condition FunctionId="urn:oasis:names:tc:xacml:1.0:function:or">
            <Apply FunctionId="urn:oasis:names:tc:xacml:1.0:function:and">
                <Apply FunctionId="urn:oasis:names:tc:xacml:1.0:function:string-at-least-one-member-of">
                    <SubjectAttributeDesignator SubjectCategory="urn:oasis:names:tc:xacml:1.0:subject-category:access-subject" AttributeId="info:escidoc/names:aa:1.0:subject:organizational-unit" DataType="http://www.w3.org/2001/XMLSchema#string"/>
                    <ResourceAttributeDesignator AttributeId="info:escidoc/names:aa:1.0:resource:user-account:organizational-unit" DataType="http://www.w3.org/2001/XMLSchema#string"/>
                </Apply>
                <Apply FunctionId="urn:oasis:names:tc:xacml:1.0:function:not">
                    <Apply FunctionId="urn:oasis:names:tc:xacml:1.0:function:string-equal">
                        <Apply FunctionId="urn:oasis:names:tc:xacml:1.0:function:string-one-and-only">
                            <SubjectAttributeDesignator SubjectCategory="urn:oasis:names:tc:xacml:1.0:subject-category:access-subject" AttributeId="urn:oasis:names:tc:xacml:1.0:subject:subject-id" DataType="http://www.w3.org/2001/XMLSchema#string"/>
                        </Apply>
                        <Apply FunctionId="urn:oasis:names:tc:xacml:1.0:function:string-one-and-only">
                            <ResourceAttributeDesignator AttributeId="urn:oasis:names:tc:xacml:1.0:resource:resource-id" DataType="http://www.w3.org/2001/XMLSchema#string"/>
                        </Apply>
                    </Apply>
                </Apply>
            </Apply>
            <Apply FunctionId="urn:oasis:names:tc:xacml:1.0:function:string-equal">
                <Apply FunctionId="urn:oasis:names:tc:xacml:1.0:function:string-one-and-only">
                    <SubjectAttributeDesignator SubjectCategory="urn:oasis:names:tc:xacml:1.0:subject-category:access-subject" AttributeId="urn:oasis:names:tc:xacml:1.0:subject:subject-id" DataType="http://www.w3.org/2001/XMLSchema#string"/>
                </Apply>
                <Apply FunctionId="urn:oasis:names:tc:xacml:1.0:function:string-one-and-only">
                    <ResourceAttributeDesignator AttributeId="info:escidoc/names:aa:1.0:resource:user-account:grant:created-by" DataType="http://www.w3.org/2001/XMLSchema#string"/>
                </Apply>
            </Apply>
        </Condition>
    </Rule>
</Policy>
');


  
