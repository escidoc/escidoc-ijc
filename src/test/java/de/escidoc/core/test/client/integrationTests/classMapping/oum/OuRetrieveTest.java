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
package de.escidoc.core.test.client.integrationTests.classMapping.oum;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.OrganizationalUnitHandlerClient;
import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.client.interfaces.OrganizationalUnitHandlerClientInterface;
import de.escidoc.core.resources.common.MetadataRecord;
import de.escidoc.core.resources.oum.OrganizationalUnit;
import de.escidoc.core.test.client.AbstractParameterizedTestBase;
import de.escidoc.core.test.client.Constants;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * Test retrieving OrganizationalUnit.
 * 
 * @author SWA
 * 
 */
public class OuRetrieveTest extends AbstractParameterizedTestBase {

    public OuRetrieveTest(TransportProtocol transport) {
        super(transport);
    }

    /**
     * Test retrieving one OU of the example set.
     * 
     * @throws Exception
     *             Thrown if retrieving failed.
     */
    @Test
    public void testRetrieveExampleOu01() throws Exception {

        OrganizationalUnitHandlerClientInterface cc =
            new OrganizationalUnitHandlerClient();
        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);
        cc.setHandle(auth.getHandle());
        cc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        cc.setTransport(transport);

        OrganizationalUnit ou = cc.retrieve("escidoc:ex3");

        MetadataRecord mdRecord = ou.getMetadataRecords().get("escidoc");
        assertNotNull(ou.getMetadataRecords().get("escidoc"));
        assertEquals("wrong name", "escidoc", mdRecord.getName());
    }

}
