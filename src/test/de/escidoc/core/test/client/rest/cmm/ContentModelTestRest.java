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
package de.escidoc.core.test.client.rest.cmm;

import java.io.File;

import org.apache.log4j.Logger;

import de.escidoc.core.client.rest.RestContentModelHandlerClient;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * Test methods for ContentModel on REST interface of client lib.
 * 
 * @author SWA
 * 
 */
public class ContentModelTestRest extends EscidocClientTestBase {

    private final Logger logger =
        Logger.getLogger(ContentModelTestRest.class.getName());

    /**
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    public void testRetrieveContentModel01() throws Exception {

        RestContentModelHandlerClient rcmhc =
            new RestContentModelHandlerClient();
        rcmhc.login(DEFAULT_SERVICE_URL, SYSTEM_ADMIN_USER,
            SYSTEM_ADMIN_PASSWORD);

        // load XML template of organizational unit
        File templOu =
            new File("./templates/rest/content-model/0.1/content-model.xml");
        String ouXml = getXmlFileAsString(templOu);

        // create
        String crtdOuXML = rcmhc.create(ouXml);
        String[] objidLmd = obtainObjidAndLmd(crtdOuXML);

        System.out.println("Content Model with objid='" + objidLmd[0]
            + "' at '" + objidLmd[1] + "' created");
    }

}
