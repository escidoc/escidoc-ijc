/*
 * CDDL HEADER START
 *
 * The contents of this file are subject to the terms of the
 * Common Development and Distribution License, Version 1.0 only
 * (the "License").  You may not use this file except in compliance
 * with the License.
 *
 * You can obtain a copy of the license at license/ESCIDOC.LICENSE
 * or http://www.escidoc.de/license.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL HEADER in each
 * file and include the License file at license/ESCIDOC.LICENSE.
 * If applicable, add the following below this CDDL HEADER, with the
 * fields enclosed by brackets "[]" replaced with your own identifying
 * information: Portions Copyright [yyyy] [name of copyright owner]
 *
 * CDDL HEADER END
 */

/*
 * Copyright 2006-2010 Fachinformationszentrum Karlsruhe Gesellschaft
 * fuer wissenschaftlich-technische Information mbH and Max-Planck-
 * Gesellschaft zur Foerderung der Wissenschaft e.V.  
 * All rights reserved.  Use is subject to license terms.
 */
package de.escidoc.core.test.client.util;

import java.net.URISyntaxException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.ContentModelHandlerClient;
import de.escidoc.core.client.ContextHandlerClient;
import de.escidoc.core.client.ItemHandlerClient;
import de.escidoc.core.client.OrganizationalUnitHandlerClient;
import de.escidoc.core.client.UserAccountHandlerClient;
import de.escidoc.core.client.exceptions.EscidocClientException;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.ContentModelHandlerClientInterface;
import de.escidoc.core.client.interfaces.ContextHandlerClientInterface;
import de.escidoc.core.client.interfaces.ItemHandlerClientInterface;
import de.escidoc.core.client.interfaces.OrganizationalUnitHandlerClientInterface;
import de.escidoc.core.client.interfaces.UserAccountHandlerClientInterface;
import de.escidoc.core.resources.aa.useraccount.Grant;
import de.escidoc.core.resources.aa.useraccount.GrantProperties;
import de.escidoc.core.resources.aa.useraccount.UserAccount;
import de.escidoc.core.resources.aa.useraccount.UserAccountProperties;
import de.escidoc.core.resources.cmm.ContentModel;
import de.escidoc.core.resources.cmm.ContentModelProperties;
import de.escidoc.core.resources.cmm.ResourceDefinition;
import de.escidoc.core.resources.cmm.ResourceDefinitions;
import de.escidoc.core.resources.cmm.Xslt;
import de.escidoc.core.resources.common.MetadataRecord;
import de.escidoc.core.resources.common.MetadataRecords;
import de.escidoc.core.resources.common.Result;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.resources.common.properties.PublicStatus;
import de.escidoc.core.resources.common.reference.ContentModelRef;
import de.escidoc.core.resources.common.reference.ContextRef;
import de.escidoc.core.resources.common.reference.OrganizationalUnitRef;
import de.escidoc.core.resources.common.reference.Reference;
import de.escidoc.core.resources.common.reference.RoleRef;
import de.escidoc.core.resources.om.context.Context;
import de.escidoc.core.resources.om.context.ContextProperties;
import de.escidoc.core.resources.om.context.OrganizationalUnitRefs;
import de.escidoc.core.resources.om.item.Item;
import de.escidoc.core.resources.oum.OrganizationalUnit;
import de.escidoc.core.test.client.integrationTests.classMapping.om.ResourceUtility;

/**
 * Utility class to create data structures as pre-condition for test. You find
 * for example methods to create all required resources to create an Item.
 * 
 * It is every time assumed that the repository is empty.
 * 
 * @author SWA
 * 
 */
public class SetupDataUtil {

    /**
     * Create an Organizational Unit within the infrastructure.
     * 
     * @param auth
     *            A valid Authentication to create an Organizational Unit
     * @param setToOpen
     *            Set true if status of Organizational Unit is to set to open,
     *            false otherwise.
     * @return Created Organizational Unit
     * 
     * @throws InternalClientException
     * @throws ParserConfigurationException
     * @throws EscidocException
     * @throws TransportException
     */
    public static OrganizationalUnit createOrganizationalUnit(
        final Authentication auth, final boolean setToOpen)
        throws InternalClientException, ParserConfigurationException,
        EscidocException, TransportException {

        final String ouName = "name" + System.currentTimeMillis();
        final String ouDescription = "Just a generic organizational unit.";

        OrganizationalUnit organizationalUnit = new OrganizationalUnit();
        // de.escidoc.core.resources.oum.Properties properties = new
        // de.escidoc.core.resources.oum.Properties();
        // properties.setName(ouName);
        // organizationalUnit.setProperties(properties);

        // md-record "escidoc"
        MetadataRecord mdRecord = new MetadataRecord("escidoc");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        factory.setCoalescing(true);
        factory.setValidating(true);
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document doc = builder.newDocument();
        Element mdRecordContent = doc.createElementNS(null, "myMdRecord");
        mdRecord.setContent(mdRecordContent);

        // title
        Element title =
            doc.createElementNS("http://purl.org/dc/elements/1.1/", "title");
        title.setTextContent(ouName);
        mdRecordContent.appendChild(title);

        // dc:description
        Element description =
            doc.createElementNS("http://purl.org/dc/elements/1.1/",
                "description");
        description.setTextContent(ouDescription);
        mdRecordContent.appendChild(description);
        mdRecord.setContent(mdRecordContent);

        MetadataRecords mdRecords = new MetadataRecords();
        mdRecords.add(mdRecord);

        organizationalUnit.setMetadataRecords(mdRecords);

        OrganizationalUnitHandlerClientInterface ouhc =
            new OrganizationalUnitHandlerClient(auth.getServiceAddress());
        ouhc.setHandle(auth.getHandle());

        organizationalUnit = ouhc.create(organizationalUnit);

        if (setToOpen) {
            TaskParam tp = new TaskParam();
            tp.setComment("Open OU just after create");
            tp.setLastModificationDate(organizationalUnit
                .getLastModificationDate());

            Result result = ouhc.open(organizationalUnit.getObjid(), tp);
            // get latest status of OU
            organizationalUnit = ouhc.retrieve(organizationalUnit.getObjid());
        }

        return organizationalUnit;
    }

    /**
     * Create an UserAccount with Role Depositor within the infrastructure.
     * 
     * @param auth
     *            A valid Authentication to fulfill the task (sysadmin)
     * @return UserAccount with required Role
     * @throws EscidocClientException
     */
    public static UserAccount createUserWithDepositorRole(
        final Authentication auth, final String password,
        final Reference assignOn) throws EscidocClientException {

        UserAccount ua = new UserAccount();

        // user properties
        UserAccountProperties properties = new UserAccountProperties();
        String login = String.valueOf(System.nanoTime());
        properties.setName("Name " + login);
        properties.setLoginName(login);

        ua.setProperties(properties);

        // Get service handler (with authentication)
        UserAccountHandlerClientInterface uac =
            new UserAccountHandlerClient(auth.getServiceAddress());
        uac.setHandle(auth.getHandle());

        // create
        UserAccount userAccount = uac.create(ua);

        // update password
        TaskParam taskParam = new TaskParam();
        taskParam
            .setLastModificationDate(userAccount.getLastModificationDate());
        taskParam.setPassword(password);
        uac.updatePassword(userAccount.getObjid(), taskParam);

        // add Grant
        Grant grant = new Grant();
        GrantProperties gProp = new GrantProperties();
        gProp.setRole(new RoleRef("escidoc:role-depositor"));
        gProp.setAssignedOn(assignOn);
        grant.setGrantProperties(gProp);
        uac.createGrant(userAccount.getObjid(), grant);

        return userAccount;
    }

    /**
     * Create a Context in status open and with a relation to an Organizational
     * Unit within the infrastructure.
     * 
     * @param auth
     *            A valid Authentication to fulfill the task (sysadmin)
     * @param organizationalUnit
     *            The Organizational Unit which is to reference
     * @param setToOpen
     *            Set true if status of Context is to set to open, false
     *            otherwise.
     * @return The Context
     * @throws EscidocClientException
     */
    public static Context createContext(
        final Authentication auth, final OrganizationalUnit organizationalUnit,
        final boolean setToOpen) throws EscidocClientException {

        ContextHandlerClientInterface cc =
            new ContextHandlerClient(auth.getServiceAddress());
        cc.setHandle(auth.getHandle());

        Context context = new Context();
        ContextProperties properties = new ContextProperties();
        properties.setDescription("ContextDescription");
        properties.setName("ContextName" + System.currentTimeMillis());
        properties.setPublicStatus(PublicStatus.OPENED);
        properties.setPublicStatusComment("PublicStatusComment");

        OrganizationalUnitRefs organizationalUnitRefs =
            new OrganizationalUnitRefs();
        organizationalUnitRefs.add(new OrganizationalUnitRef(organizationalUnit
            .getObjid()));

        properties.setOrganizationalUnitRefs(organizationalUnitRefs);
        properties.setType("type");
        context.setProperties(properties);

        context = cc.create(context);

        if (setToOpen) {
            TaskParam tp = new TaskParam();
            tp.setComment("Open OU just after create");
            tp.setLastModificationDate(context.getLastModificationDate());

            cc.open(context.getObjid(), tp);
            // get latest status of OU
            context = cc.retrieve(context.getObjid());
        }

        return context;

    }

    /**
     * Create an Item Context within the infrastructure.
     * 
     * @param auth
     *            A valid Authentication to fulfill the task (depositor)
     * @param context
     *            The Context which is to reference
     * @param contentModel
     *            The Content Model which is to reference
     * @return The Context
     * @throws EscidocClientException
     * @throws ParserConfigurationException
     */
    public static Item createItem(
        final Authentication auth, final Context context,
        final ContentModel contentModel) throws EscidocClientException,
        ParserConfigurationException {

        return createItem(auth, context.getObjid(), contentModel.getObjid());
    }

    /**
     * Create an Item Context within the infrastructure.
     * 
     * @param auth
     *            A valid Authentication to fulfill the task (depositor)
     * @param context
     *            The Context which is to reference
     * @param contentModel
     *            The Content Model which is to reference
     * @return The Context
     * @throws EscidocClientException
     * @throws ParserConfigurationException
     */
    public static Item createItem(
        final Authentication auth, final String contextId,
        final String contentModelId) throws EscidocClientException,
        ParserConfigurationException {

        Item item = new Item();

        item.getProperties().setContext(new ContextRef(contextId));
        item.getProperties().setContentModel(
            new ContentModelRef(contentModelId));

        // Metadata Record(s)
        MetadataRecords mdRecords = new MetadataRecords();
        MetadataRecord mdrecord = ResourceUtility.getMdRecord("escidoc");
        mdRecords.add(mdrecord);
        item.setMetadataRecords(mdRecords);

        ItemHandlerClientInterface ihc =
            new ItemHandlerClient(auth.getServiceAddress());
        ihc.setHandle(auth.getHandle());

        return ihc.create(item);
    }

    /**
     * Create a ContentModel within the infrastructure.
     * 
     * @param auth
     *            A valid Authentication to fulfill the task (sysadmin)
     * @return The ContentModel
     * 
     * @throws EscidocClientException
     * @throws URISyntaxException
     *             Thrown if value for XSLT of resource definition is not a
     *             valid URI
     */
    public static ContentModel createContentModel(final Authentication auth)
        throws EscidocClientException, URISyntaxException {

        ContentModelHandlerClientInterface cc =
            new ContentModelHandlerClient(auth.getServiceAddress());
        cc.setHandle(auth.getHandle());

        ContentModel contentModel = new ContentModel();
        ContentModelProperties properties = new ContentModelProperties();
        properties.setDescription("ContentModel Description");
        properties.setName("ContentModelName" + System.currentTimeMillis());

        contentModel.setProperties(properties);

        // resource definition
        ResourceDefinition rd1 = new ResourceDefinition();
        rd1.setName("transX" + System.nanoTime());
        rd1.setMetadataRecordName("escidoc");
        rd1.setXslt(new Xslt(
            "http://localhost:8080/xsl/mapping-unknown2dc-onlyMD.xsl"));
        ResourceDefinitions rds = new ResourceDefinitions();
        rds.add(rd1);
        contentModel.setResourceDefinitions(rds);

        return cc.create(contentModel);
    }

    /**
     * Create all resources to create an Item.
     * 
     * Pre-condition: User and Role exists
     * 
     * @param auth
     *            A valid Authentication
     */
    public static void solveItemPrecondition(final Authentication auth) {

        // create OU
        // open OU
        // create Context
        // open Context
        // create Content Model
        // create useraccount (with depositor role and scope of context)

    }
}