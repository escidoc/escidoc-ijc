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
package de.escidoc.core.test.client.RESTHandler.om.container;

import de.escidoc.core.client.rest.RestContainerHandlerClient;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * Test methods for Container on REST interface of client lib.
 * 
 * @author SWA
 * 
 */
public class ContainerTestRest extends EscidocClientTestBase {

    /**
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    public void testRetrieveContainer01() throws Exception {

        RestContainerHandlerClient cc = new RestContainerHandlerClient();
        cc.setHandle(EscidocClientTestBase.DEFAULT_HANDLE);

        String container = cc.retrieve(EXAMPLE_CONTAINER_ID);
        System.out.println(container);
    }

    /**
     * Test successful creating a Container.
     *  
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    public void testCreateContainer01() throws Exception {

        RestContainerHandlerClient cc = new RestContainerHandlerClient();
        cc.setHandle(EscidocClientTestBase.DEFAULT_HANDLE);

        // get a valid container
        String container = cc.retrieve(EXAMPLE_CONTAINER_ID);
        
        // create a new one (on basis of the retrieved)
        cc.create(container);
    }

}
