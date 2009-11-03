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
package de.escidoc.core.test.client.rest.om.item;

import org.apache.log4j.Logger;

import de.escidoc.core.client.rest.RestItemHandlerClient;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * Test create Item.
 * 
 * @author SWA
 * 
 */
public class ItemTestRest extends EscidocClientTestBase {

    private final Logger logger =
        Logger.getLogger(ItemTestRest.class.getName());

    /**
     * Test if retrieve Item via REST is successful.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    public void testRetrieveItem01() throws Exception {

        RestItemHandlerClient cc = new RestItemHandlerClient();
        cc.setHandle(EscidocClientTestBase.DEFAULT_HANDLE);

        String item = cc.retrieve(EXAMPLE_ITEM_ID);
        System.out.println(item);
    }

    /**
     * Test if the right exception is thrown if calling create with an
     * incomplete Item.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    public void testCreateItem01() throws Exception {

        RestItemHandlerClient cc = new RestItemHandlerClient();
        cc.setHandle(EscidocClientTestBase.DEFAULT_HANDLE);

        String item = cc.retrieve(EXAMPLE_ITEM_ID);
        cc.create(item);
    }

}
