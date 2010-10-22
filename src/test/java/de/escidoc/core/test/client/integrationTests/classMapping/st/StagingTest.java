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
package de.escidoc.core.test.client.integrationTests.classMapping.st;

import static org.junit.Assert.assertEquals;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.StagingHandlerClient;
import de.escidoc.core.client.interfaces.StagingHandlerClientInterface;
import de.escidoc.core.test.client.Constants;
import de.escidoc.core.test.client.EscidocClientTestBase;
import de.escidoc.core.test.client.util.Template;

/**
 * Test of Staging Handler.
 * 
 * @author SWA
 * 
 */
public class StagingTest {

    private Authentication auth;

    private StagingHandlerClientInterface sthc;
    
    @Before
    public void init() throws Exception {
        auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);
        sthc = new StagingHandlerClient(auth.getServiceAddress());
        sthc.setHandle(auth.getHandle());
    }

    @After
    public void post() throws Exception {
        auth.logout();
    }
    
    /**
     * Test to search repository.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void uploadFileTest() throws Exception {

        InputStream inputStream = Template.load("/soap/om/item/0.6/item.xml");
        URL url = sthc.upload(inputStream);

        // assert file
        String stagingFile = "";
        String s;
        InputStream ins = url.openStream();
        DataInputStream dis = new DataInputStream(new BufferedInputStream(ins));
        while ((s = dis.readLine()) != null) {
            stagingFile += s;
        }

        inputStream = Template.load("/soap/om/item/0.6/item.xml");
        
        String localFile = "";
        BufferedReader in =
            new BufferedReader(new InputStreamReader(inputStream));
        try {
            String line;
            while ((line = in.readLine()) != null) {
                localFile += line;
            }
        }
        finally {
            in.close();
        }

        // compare both
        assertEquals("Uploaded File differs from local", localFile, stagingFile);
    }
}
