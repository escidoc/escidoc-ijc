package de.escidoc.core.test.client.integrationTests.classMapping.om.context;

import static org.junit.Assert.assertEquals;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.ContextHandlerClient;
import de.escidoc.core.client.exceptions.EscidocClientException;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.ContextHandlerClientInterface;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.resources.common.properties.PublicStatus;
import de.escidoc.core.resources.common.reference.OrganizationalUnitRef;
import de.escidoc.core.resources.om.context.AdminDescriptor;
import de.escidoc.core.resources.om.context.AdminDescriptors;
import de.escidoc.core.resources.om.context.Context;
import de.escidoc.core.resources.om.context.ContextProperties;
import de.escidoc.core.resources.om.context.OrganizationalUnitRefs;
import de.escidoc.core.test.client.EscidocClientTestBase;

public class OpenContextWithEmptyComment {

    private static final String EMPTY_PUBLIC_STATUS_COMMENT = "";

    private Authentication auth;

    private ContextHandlerClientInterface cc;

    @Before
    public void init() throws Exception {
        auth =
            new Authentication(
                EscidocClientTestBase.getDefaultInfrastructureURL(),
                EscidocClientTestBase.SYSTEM_ADMIN_USER,
                EscidocClientTestBase.SYSTEM_ADMIN_PASSWORD);
        cc = new ContextHandlerClient(auth.getServiceAddress());
        cc.setHandle(auth.getHandle());
    }

    @After
    public void post() throws Exception {
        if (auth != null)
            auth.logout();
    }

    /**
     * (see issue INFR-937)
     * 
     * @throws ParserConfigurationException
     * @throws EscidocClientException
     */
    @Test
    public void shouldSetPublicStatusCommentToEmpty()
        throws ParserConfigurationException, EscidocClientException {
        Context createdContext = createNewContext();
        openContext(createdContext, EMPTY_PUBLIC_STATUS_COMMENT);
        Context openedContext = retrieveContext(createdContext);

        assertEquals("Set public status comment to empty fails.",
            EMPTY_PUBLIC_STATUS_COMMENT, openedContext
                .getProperties().getPublicStatusComment());
    }

    /**
     * 
     * @return
     * @throws ParserConfigurationException
     * @throws EscidocClientException
     */
    private Context createNewContext() throws ParserConfigurationException,
        EscidocClientException {

        final Context context = new Context();
        final ContextProperties properties = new ContextProperties();
        properties.setDescription("ContextDescription");
        properties.setName("ContextName" + System.currentTimeMillis());
        properties.setPublicStatus(PublicStatus.OPENED);
        properties.setPublicStatusComment("PublicStatusComment");

        final OrganizationalUnitRefs organizationalUnitRefs =
            new OrganizationalUnitRefs();
        organizationalUnitRefs.add(new OrganizationalUnitRef(
            EscidocClientTestBase.getStaticOrganizationalUnitId()));
        properties.setOrganizationalUnitRefs(organizationalUnitRefs);
        properties.setType("type");
        context.setProperties(properties);

        final AdminDescriptors adminDescriptors = new AdminDescriptors();
        final AdminDescriptor adminDescriptor =
            new AdminDescriptor("AdminDescriptorDemoName");
        final DocumentBuilderFactory factory =
            DocumentBuilderFactory.newInstance();
        final DocumentBuilder builder = factory.newDocumentBuilder();
        final Document doc = builder.newDocument();
        final Element element = doc.createElementNS(null, "admin-descriptor");
        adminDescriptor.setContent(element);

        adminDescriptors.add(adminDescriptor);
        context.setAdminDescriptors(adminDescriptors);

        // create
        return cc.create(context);
    }

    /**
     * 
     * @param context
     * @param publicStatusComment
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    private void openContext(
        final Context context, final String publicStatusComment)
        throws EscidocException, InternalClientException, TransportException {
        final TaskParam taskParam = new TaskParam();
        taskParam.setLastModificationDate(context.getLastModificationDate());
        taskParam.setComment(publicStatusComment);
        cc.open(context.getObjid(), taskParam);
    }

    /**
     * 
     * @param context
     * @return
     * @throws EscidocClientException
     */
    private Context retrieveContext(final Context context)
        throws EscidocClientException {
        return cc.retrieve(context.getObjid());
    }
}