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
 * Copyright 2006-2008 Fachinformationszentrum Karlsruhe Gesellschaft
 * fuer wissenschaftlich-technische Information mbH and Max-Planck-
 * Gesellschaft zur Foerderung der Wissenschaft e.V.  
 * All rights reserved.  Use is subject to license terms.
 */
package de.escidoc.core.test.client.RESTHandler.om.ingest;

import org.apache.log4j.Logger;
import org.junit.Test;

import de.escidoc.core.client.ItemHandlerClient;
import de.escidoc.core.client.rest.RestIngestHandlerClient;
import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.resources.om.item.Item;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * Test ingest REST interface.
 * 
 * @author SWA
 * 
 */
public class IngestTest extends EscidocClientTestBase {

    private final Logger logger = Logger.getLogger(IngestTest.class.getName());

    /**
     * Test if the right exception is thrown if calling create with an
     * incomplete Item.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void testIngest01() throws Exception {

        // organize Item
        ItemHandlerClient ic = new ItemHandlerClient();
        ic.setHandle(EscidocClientTestBase.DEFAULT_HANDLE);
        Item item = ic.retrieve(EXAMPLE_ITEM_ID);
        String itemXml = Factory.getItemMarshaller().marshalDocument(item);

        // ingest Item
        RestIngestHandlerClient cc = new RestIngestHandlerClient();
        cc.setHandle(EscidocClientTestBase.DEFAULT_HANDLE);
        cc.ingest(itemXml);
    }

}
