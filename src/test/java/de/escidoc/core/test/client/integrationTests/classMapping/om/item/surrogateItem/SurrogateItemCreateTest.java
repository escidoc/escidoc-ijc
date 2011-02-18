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

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.ItemHandlerClient;
import de.escidoc.core.client.interfaces.ItemHandlerClientInterface;
import de.escidoc.core.resources.aa.useraccount.UserAccount;
import de.escidoc.core.resources.cmm.ContentModel;
import de.escidoc.core.resources.common.MetadataRecord;
import de.escidoc.core.resources.common.MetadataRecords;
import de.escidoc.core.resources.common.Result;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.resources.common.reference.ContentModelRef;
import de.escidoc.core.resources.common.reference.ContextRef;
import de.escidoc.core.resources.common.reference.ItemRef;
import de.escidoc.core.resources.om.context.Context;
import de.escidoc.core.resources.om.item.Item;
import de.escidoc.core.resources.oum.OrganizationalUnit;
import de.escidoc.core.test.client.Constants;
import de.escidoc.core.test.client.EscidocClientTestBase;
import de.escidoc.core.test.client.integrationTests.classMapping.om.ResourceUtility;
import de.escidoc.core.test.client.util.SetupDataUtil;

/**
 * Test create a Surrogate Item.
 * 
 * @author SWA
 * 
 */
public class SurrogateItemCreateTest {

    private Authentication auth;

    private ItemHandlerClientInterface ihc;

    @Before
    public void init() throws Exception {
        auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);
        ihc = new ItemHandlerClient(auth.getServiceAddress());
        ihc.setHandle(auth.getHandle());
    }

    @After
    public void post() throws Exception {
        if (auth != null)
            auth.logout();
    }

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

        // -------------------------------------------------
        // Create Organizational Unit (and set status to open)
        OrganizationalUnit organizationalUnit =
            SetupDataUtil.createOrganizationalUnit(auth, true);

        // -------------------------------------------------
        // create Context 1
        Context context1 =
            SetupDataUtil.createContext(auth, organizationalUnit, true);

        // create Context 2
        Context context2 =
            SetupDataUtil.createContext(auth, organizationalUnit, true);

        // -------------------------------------------------
        // create Content Model
        ContentModel contentModel = SetupDataUtil.createContentModel(auth);

        // -------------------------------------------------
        // Create User Account
        String password = String.valueOf(System.nanoTime());

        UserAccount userAccount =
            SetupDataUtil.createUserWithDepositorRole(auth, password,
                context1.getReference());

        UserAccount userAccount2 =
            SetupDataUtil.createUserWithDepositorRole(auth, password,
                context2.getReference());

        // -------------------------------------------------
        // create origin Item

        // authenticate User Account
        Authentication depositorAuth =
            new Authentication(auth.getServiceAddress(), userAccount
                .getProperties().getLoginName(), password);

        // create
        ItemHandlerClientInterface ihc =
            new ItemHandlerClient(depositorAuth.getServiceAddress());
        ihc.setHandle(depositorAuth.getHandle());

        Item originItem = new Item();

        originItem.getProperties().setContext(
            new ContextRef(context1.getObjid()));
        originItem.getProperties().setContentModel(
            new ContentModelRef(contentModel.getObjid()));

        // Metadata Record(s)
        MetadataRecords mdRecords = new MetadataRecords();
        MetadataRecord mdrecord = ResourceUtility.getMdRecord("escidoc");
        mdRecords.add(mdrecord);
        MetadataRecord mdrecord2 = ResourceUtility.getMdRecord("test2");
        mdRecords.add(mdrecord2);
        MetadataRecord mdrecord3 = ResourceUtility.getMdRecord("test3");
        mdRecords.add(mdrecord3);
        originItem.setMetadataRecords(mdRecords);

        originItem = ihc.create(originItem);

        // submit --------------------------------------------------------------
        TaskParam taskParam = new TaskParam();
        taskParam.setLastModificationDate(originItem.getLastModificationDate());
        taskParam.setComment("submitted as java client lib test");

        Result result = ihc.submit(originItem, taskParam);

        // assign object PID ---------------------------------------------------
        taskParam = new TaskParam();
        taskParam.setLastModificationDate(result.getLastModificationDate());
        taskParam.setUrl(new URL(
            "http://url.to.the.solution/path/for/this/resource/"
                + System.nanoTime()));
        taskParam.setComment("Test Object PID");

        result = ihc.assignObjectPid(originItem, taskParam);

        // assign version PID --------------------------------------------------
        taskParam = new TaskParam();
        taskParam.setLastModificationDate(result.getLastModificationDate());
        taskParam.setUrl(new URL(
            "http://url.to.the.solution/path/for/this/resource/"
                + System.nanoTime()));
        taskParam.setComment("Test Version PID");

        result = ihc.assignVersionPid(originItem, taskParam);

        // release -------------------------------------------------------------
        taskParam = new TaskParam();
        taskParam.setLastModificationDate(result.getLastModificationDate());
        taskParam.setComment("Release as java client lib test");

        result = ihc.release(originItem, taskParam);

        // ====================================================================
        // prepare a new Item as Surrogate Item
        // authenticate User Account
        Authentication depositorAuth2 =
            new Authentication(auth.getServiceAddress(), userAccount2
                .getProperties().getLoginName(), password);

        ItemHandlerClientInterface ihc2 =
            new ItemHandlerClient(depositorAuth2.getServiceAddress());
        ihc2.setHandle(depositorAuth2.getHandle());

        Item surrogateItem = new Item();

        surrogateItem.getProperties().setContext(
            new ContextRef(context2.getObjid()));
        surrogateItem.getProperties().setContentModel(
            new ContentModelRef(contentModel.getObjid()));

        // Metadata Record(s)
        mdRecords = new MetadataRecords();
        mdrecord = ResourceUtility.getMdRecord("escidoc");
        mdRecords.add(mdrecord);
        surrogateItem.setMetadataRecords(mdRecords);

        surrogateItem.getProperties().setOrigin(
            new ItemRef(originItem.getObjid()));
        surrogateItem = ihc2.create(surrogateItem);

        /*
         * compare the Surrogate Item with the origin Item
         */
        assertThat("Context refererence does not differ", originItem
            .getProperties().getContext().getObjid(), not(surrogateItem
            .getProperties().getContext().getObjid()));
        assertEquals("Number of metadata records differ", originItem
            .getMetadataRecords().size(), surrogateItem
            .getMetadataRecords().size());
    }
}
