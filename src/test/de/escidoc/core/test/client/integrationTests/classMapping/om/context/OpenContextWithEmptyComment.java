package de.escidoc.core.test.client.integrationTests.classMapping.om.context;

import static org.junit.Assert.assertEquals;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.ContextHandlerClient;
import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.client.exceptions.EscidocClientException;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.ContextHandlerClientInterface;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.resources.common.reference.OrganizationalUnitRef;
import de.escidoc.core.resources.om.context.AdminDescriptor;
import de.escidoc.core.resources.om.context.AdminDescriptors;
import de.escidoc.core.resources.om.context.Context;
import de.escidoc.core.resources.om.context.OrganizationalUnitRefs;
import de.escidoc.core.resources.om.context.Properties;
import de.escidoc.core.test.client.AbstractParameterizedTestBase;
import de.escidoc.core.test.client.Constants;
import de.escidoc.core.test.client.EscidocClientTestBase;

public class OpenContextWithEmptyComment extends AbstractParameterizedTestBase {

    public OpenContextWithEmptyComment(TransportProtocol transport) {
        super(transport);
    }

    private static final String EMPTY_PUBLIC_STATUS_COMMENT = "";

    private ContextHandlerClientInterface cc;

    /**
     * (see issue INFR-937)
     * 
     * @throws ParserConfigurationException
     * @throws EscidocClientException
     */
    @Test
    public void shouldSetPublicStatusCommentToEmpty()
        throws ParserConfigurationException, EscidocClientException {

        loginAsSysAdmin();
        Context createdContext = createNewContext();
        openContext(createdContext, EMPTY_PUBLIC_STATUS_COMMENT);
        Context openedContext = retrieveContext(createdContext);

        assertEquals("Set public status comment to empty fails.",
            EMPTY_PUBLIC_STATUS_COMMENT, openedContext
                .getProperties().getPublicStatusComment());
    }

    private Context createNewContext() throws ParserConfigurationException,
        EscidocClientException {

        final Context context = new Context();
        final Properties properties = new Properties();
        properties.setDescription("ContextDescription");
        properties.setName("ContextName" + System.currentTimeMillis());
        properties.setPublicStatus("opened");
        properties.setPublicStatusComment("PublicStatusComment");

        final OrganizationalUnitRefs organizationalUnitRefs =
            new OrganizationalUnitRefs();
        organizationalUnitRefs.add(new OrganizationalUnitRef("escidoc:ex3"));
        properties.setOrganizationalUnitRefs(organizationalUnitRefs);
        properties.setType("type");
        context.setProperties(properties);

        final AdminDescriptors adminDescriptors = new AdminDescriptors();
        final AdminDescriptor adminDescriptor = new AdminDescriptor();
        adminDescriptor.setName("AdminDescriptorDemoName");
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

    private void loginAsSysAdmin() throws EscidocClientException {
        final Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        cc = new ContextHandlerClient();
        cc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        cc.setHandle(auth.getHandle());
        cc.setTransport(transport);
    }

    private void openContext(Context context, String publicStatusComment)
        throws EscidocException, InternalClientException, TransportException {
        final TaskParam taskParam = new TaskParam();
        taskParam.setLastModificationDate(context.getLastModificationDate());
        taskParam.setComment(publicStatusComment);
        cc.open(context.getObjid(), taskParam);
    }

    private Context retrieveContext(Context context)
        throws EscidocClientException {
        return cc.retrieve(context.getObjid());
    }
}