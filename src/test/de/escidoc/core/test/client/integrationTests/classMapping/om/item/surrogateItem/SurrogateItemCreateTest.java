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
package de.escidoc.core.test.client.integrationTests.classMapping.om.item.surrogateItem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.ContextHandlerClient;
import de.escidoc.core.client.ItemHandlerClient;
import de.escidoc.core.resources.ResourceRef;
import de.escidoc.core.resources.aa.useraccount.UserAccount;
import de.escidoc.core.resources.common.MetadataRecord;
import de.escidoc.core.resources.common.MetadataRecords;
import de.escidoc.core.resources.common.Result;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.resources.common.properties.ContentModelSpecific;
import de.escidoc.core.resources.om.context.Context;
import de.escidoc.core.resources.om.context.OrganizationalUnitRefs;
import de.escidoc.core.resources.om.context.Properties;
import de.escidoc.core.resources.om.item.Item;
import de.escidoc.core.resources.oum.OrganizationalUnit;
import de.escidoc.core.test.client.Constants;
import de.escidoc.core.test.client.EscidocClientTestBase;
import de.escidoc.core.test.client.integrationTests.classMapping.om.ResourceUtility;
import de.escidoc.core.test.client.util.TestDataUtil;

/**
 * Test create a Surrogate Item.
 * 
 * @author SWA
 * 
 */
public class SurrogateItemCreateTest {

    /**
     * Test create of a surrogate Item.
     * 
     * @throws Exception
     *             Thrown if creation failed or non-volatile Item values differ.
     */
    @Test
    public void createSurrogateItem01() throws Exception {

        /*
         * We need an environment to test surrogate Item. This environment
         * consists of an UserAccount, Grant, an Organizational Unit with two
         * Contexts, and one Item in one Context and the surrogate Item is to
         * create in the other Context.
         * 
         * UserAccount, Grant, Organizational Unit, and Context has to be
         * created with system-administrator role
         * (escidoc:role-system-administrator)
         * 
         * Item can be created with role-depositor.
         * 
         * <pre> User -> Organizational Unit </pre>
         */

        Authentication sysadminAuth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        // Create User Account
        String password = String.valueOf(System.nanoTime());
        UserAccount userAccount =
            TestDataUtil.createUserWithDepositorRole(sysadminAuth, password);

        // Create Organizational Unit
        OrganizationalUnit organizationalUnit =
            TestDataUtil.createOrganizationalUnit(sysadminAuth);

        // create Context 1 -------------------------------------------------
        Context context1 =
            TestDataUtil.createContext(sysadminAuth, organizationalUnit);

        // create Context 2
        Context context2 =
            TestDataUtil.createContext(sysadminAuth, organizationalUnit);


        // authenticate User Account
        Authentication depositorAuth =
            new Authentication(sysadminAuth.getServiceAddress(), userAccount
                .getProperties().getLoginName(), password);

//        // create, submit and release Item (item1)
//        Item originItem = TestDataUtil.createItem(depositorAuth, context1, contentModel);
//
//        
//        ItemHandlerClient ihc = new ItemHandlerClient();
//        ihc.setServiceAddress(depositorAuth.getServiceAddress());
//        ihc.setHandle(depositorAuth.getHandle());
//
//        // create
//        Item createdItem = ihc.create(item);
//        // submit --------------------------------------------------------------
//        TaskParam taskParam = new TaskParam();
//        taskParam
//            .setLastModificationDate(createdItem.getLastModificationDate());
//        taskParam.setComment("submitted as java client lib test");
//
//        Result result = ihc.submit(createdItem, taskParam);
//
//        // assign object PID ---------------------------------------------------
//        taskParam = new TaskParam();
//        taskParam.setLastModificationDate(result.getLastModificationDate());
//        taskParam.setUrl("http://url.to.the.solution/path/for/this/resource/"
//            + System.nanoTime());
//        taskParam.setComment("Test Object PID");
//
//        result = ihc.assignObjectPid(createdItem, taskParam);
//
//        // assign version PID --------------------------------------------------
//        taskParam = new TaskParam();
//        taskParam.setLastModificationDate(result.getLastModificationDate());
//        taskParam.setUrl("http://url.to.the.solution/path/for/this/resource/"
//            + System.nanoTime());
//        taskParam.setComment("Test Version PID");
//
//        result = ihc.assignVersionPid(createdItem, taskParam);
//
//        // release -------------------------------------------------------------
//        taskParam = new TaskParam();
//        taskParam.setLastModificationDate(result.getLastModificationDate());
//        taskParam.setComment("Release as java client lib test");
//
//        result = ihc.release(createdItem, taskParam);
//
//        // ====================================================================
//        // prepare a new Item as Surrogate Item
//        Item surrogateItem = new Item();
//
//        surrogateItem.getProperties().setContext(
//            new ResourceRef(Constants.EXAMPLE_CONTEXT_ID));
//        surrogateItem.getProperties().setContentModel(
//            new ResourceRef(Constants.EXAMPLE_CONTENT_MODEL_ID));
//
//        // Content Model Specific
//        surrogateItem.getProperties().setContentModelSpecific(cms);
//
//        // Metadata Record(s)
//        mdRecords = new MetadataRecords();
//        mdrecord = ResourceUtility.getMdRecord("escidoc");
//        mdRecords.add(mdrecord);
//        surrogateItem.setMetadataRecords(mdRecords);
//
//        surrogateItem.getProperties().setOrigin(
//            new ResourceRef(createdItem.getObjid()));
//        surrogateItem = ihc.create(surrogateItem);
//
//        /*
//         * compare the Surrogate Item with the origin Item
//         */
//        String objId = createdItem.getObjid();
//
//        assertNotNull("Object id is null", objId);
//        assertEquals(createdItem.getProperties().getContext().getObjid(),
//            surrogateItem.getProperties().getContext().getObjid());
//        assertEquals(createdItem.getProperties().getContentModel().getObjid(),
//            surrogateItem.getProperties().getContentModel().getObjid());
//        assertEquals(createdItem.getObjid(), surrogateItem
//            .getProperties().getOrigin().getObjid());
    }
}
