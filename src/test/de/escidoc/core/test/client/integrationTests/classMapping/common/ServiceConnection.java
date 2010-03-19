package de.escidoc.core.test.client.classMapping.common;

import static org.junit.Assert.assertNotNull;
import de.escidoc.core.client.ContainerHandlerClient;
import de.escidoc.core.client.ContextHandlerClient;
import de.escidoc.core.client.ItemHandlerClient;
import de.escidoc.core.resources.om.container.Container;
import de.escidoc.core.resources.om.context.Context;
import de.escidoc.core.resources.om.item.Item;
import de.escidoc.core.test.client.Constants;

/**
 * Test the Service Connection.
 * 
 * @author SWA
 * 
 */
public class ServiceConnection {

    private static final String ITEM_SERVICE =
        "http://escidev2:8080/axis/services/ItemHandlerService";

    private static final String CONTAINER_SERVICE =
        "http://escidev2:8080/axis/services/ContainerHandlerService";

    private static final String CONTEXT_SERVICE =
        "http://escidev2:8080/axis/services/ContextHandlerService";

    private static final String ITEM_ID = "escidoc:ex5";

    private static final String CONTAINER_ID = "escidoc:ex7";

    private static final String CONTEXT_ID = "escidoc:ex1";

    /**
     * Test if retrieve of an Item from a non http://localhost:8080 service is
     * possible.
     * 
     * @throws Exception
     *             Thrown if retrieve failed.
     */
    public void testItem01() throws Exception {

        ItemHandlerClient cc = new ItemHandlerClient();
        cc.setServiceAddress(ITEM_SERVICE);
        cc.setHandle(Constants.DEFAULT_HANDLE);

        Item item = cc.retrieve(ITEM_ID);
        String lmd = item.getLastModificationDateAsString();
        assertNotNull("Item timestamp is null", lmd);

    }

    /**
     * Test if retrieve of an Container from a non http://localhost:8080 service
     * is possible.
     * 
     * @throws Exception
     *             Thrown if retrieve failed.
     */
    public void testContainer01() throws Exception {

        ContainerHandlerClient cc = new ContainerHandlerClient();
        cc.setServiceAddress(CONTAINER_SERVICE);
        cc.setHandle(Constants.DEFAULT_HANDLE);

        Container container = cc.retrieve(CONTAINER_ID);
        String lmd = container.getLastModificationDateAsString();
        assertNotNull("Container timestamp is null", lmd);

    }

    /**
     * Test if retrieve of an Context from a non http://localhost:8080 service
     * is possible.
     * 
     * @throws Exception
     *             Thrown if retrieve failed.
     */
    public void testContext01() throws Exception {

        ContextHandlerClient cc = new ContextHandlerClient();
        cc.setServiceAddress(CONTEXT_SERVICE);
        cc.setHandle(Constants.DEFAULT_HANDLE);

        Context context = cc.retrieve(CONTEXT_ID);
        String lmd = context.getLastModificationDateAsString();
        assertNotNull("Context timestamp is null", lmd);

    }

}
