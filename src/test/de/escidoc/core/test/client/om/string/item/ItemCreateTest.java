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
package de.escidoc.core.test.client.om.string.item;

import org.apache.log4j.Logger;

import de.escidoc.core.client.soap.SoapItemHandlerClient;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * Test create Item.
 * 
 * @author SWA
 * 
 */
public class ItemCreateTest extends EscidocClientTestBase {

    private final Logger logger =
        Logger.getLogger(ItemCreateTest.class.getName());

    /**
     * Test if the right exception is thrown if calling create with an
     * incomplete Item.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    public void testCreateItem01() throws Exception {

        SoapItemHandlerClient cc = new SoapItemHandlerClient();
        cc.setHandle(EscidocClientTestBase.DEFAULT_HANDLE);

        String item = cc.retrieve(EXAMPLE_ITEM_ID);

    }
}
