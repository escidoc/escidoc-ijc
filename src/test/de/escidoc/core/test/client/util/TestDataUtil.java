package de.escidoc.core.test.client.util;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.ContextHandlerClient;
import de.escidoc.core.client.ItemHandlerClient;
import de.escidoc.core.client.OrganizationalUnitHandlerClient;
import de.escidoc.core.client.UserAccountHandlerClient;
import de.escidoc.core.client.exceptions.EscidocClientException;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.resources.ResourceRef;
import de.escidoc.core.resources.aa.useraccount.Grant;
import de.escidoc.core.resources.aa.useraccount.GrantProperties;
import de.escidoc.core.resources.aa.useraccount.UserAccount;
import de.escidoc.core.resources.aa.useraccount.UserAccountProperties;
import de.escidoc.core.resources.cmm.ContentModel;
import de.escidoc.core.resources.common.MetadataRecord;
import de.escidoc.core.resources.common.MetadataRecords;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.resources.common.properties.ContentModelSpecific;
import de.escidoc.core.resources.om.context.Context;
import de.escidoc.core.resources.om.context.OrganizationalUnitRefs;
import de.escidoc.core.resources.om.context.Properties;
import de.escidoc.core.resources.om.item.Item;
import de.escidoc.core.resources.oum.OrganizationalUnit;
import de.escidoc.core.test.client.Constants;
import de.escidoc.core.test.client.EscidocClientTestBase;
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
public class TestDataUtil {

    /**
     * Create an Organizational Unit within the infrastructure.
     * 
     * @param auth
     *            A valid Authentication to create an Organizational Unit
     * @return Created Organizational Unit
     * 
     * @throws InternalClientException
     * @throws ParserConfigurationException
     * @throws EscidocException
     * @throws TransportException
     */
    public static OrganizationalUnit createOrganizationalUnit(
        Authentication auth) throws InternalClientException,
        ParserConfigurationException, EscidocException, TransportException {

        final String ouName = "name" + System.currentTimeMillis();
        final String ouDescription = "Just a generic organizational unit.";

        OrganizationalUnit organizationalUnit = new OrganizationalUnit();
        // de.escidoc.core.resources.oum.Properties properties = new
        // de.escidoc.core.resources.oum.Properties();
        // properties.setName(ouName);
        // organizationalUnit.setProperties(properties);

        // md-record "escidoc"
        MetadataRecord mdRecord = new MetadataRecord();
        mdRecord.setName("escidoc");

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

        OrganizationalUnitHandlerClient ouhc =
            new OrganizationalUnitHandlerClient();
        ouhc.setServiceAddress(auth.getServiceAddress());
        ouhc.setHandle(auth.getHandle());

        return ouhc.create(organizationalUnit);
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
        final Authentication auth, final String password)
        throws EscidocClientException {

        UserAccount ua = new UserAccount();

        // user properties
        UserAccountProperties properties = new UserAccountProperties();
        String login = String.valueOf(System.nanoTime());
        properties.setName("Name " + login);
        properties.setLoginName(login);

        ua.setProperties(properties);

        // Get service handler (with authentication)
        UserAccountHandlerClient uac = new UserAccountHandlerClient();
        uac.setServiceAddress(auth.getServiceAddress());
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
        gProp.setRole(new ResourceRef("escidoc:role-depositor"));
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
     * @return The Context
     * @throws EscidocClientException
     */
    public static Context createContext(
        final Authentication auth, final OrganizationalUnit organizationalUnit)
        throws EscidocClientException {

        ContextHandlerClient cc = new ContextHandlerClient();
        cc.setServiceAddress(auth.getServiceAddress());
        cc.setHandle(auth.getHandle());

        Context context = new Context();
        Properties properties = new Properties();
        properties.setDescription("ContextDescription");
        properties.setName("ContextName" + System.currentTimeMillis());
        properties.setPublicStatus("opened");
        properties.setPublicStatusComment("PublicStatusComment");

        OrganizationalUnitRefs organizationalUnitRefs =
            new OrganizationalUnitRefs();
        ResourceRef organizationalUnitRef =
            new ResourceRef(organizationalUnit.getObjid());
        organizationalUnitRefs.addOrganizationalUnitRef(organizationalUnitRef);
        properties.setOrganizationalUnitRefs(organizationalUnitRefs);
        properties.setType("type");
        context.setProperties(properties);

        return cc.create(context);
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

        Item item = new Item();

        item.getProperties().setContext(new ResourceRef(context.getObjid()));
        item.getProperties().setContentModel(
            new ResourceRef(contentModel.getObjid()));

        // Content Model Specific
        ContentModelSpecific cms = ResourceUtility.getContentModelSpecific();
        item.getProperties().setContentModelSpecific(cms);

        // Metadata Record(s)
        MetadataRecords mdRecords = new MetadataRecords();
        MetadataRecord mdrecord = ResourceUtility.getMdRecord("escidoc");
        mdRecords.add(mdrecord);
        item.setMetadataRecords(mdRecords);

        ItemHandlerClient ihc = new ItemHandlerClient();
        ihc.setServiceAddress(auth.getServiceAddress());
        ihc.setHandle(auth.getHandle());

        return ihc.create(item);
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
        // create Context

    }
}