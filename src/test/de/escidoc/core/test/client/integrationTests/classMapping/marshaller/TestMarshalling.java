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
package de.escidoc.core.test.client.integrationTests.classMapping.marshaller;

import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.resources.aa.useraccount.PropertiesUserAccount;
import de.escidoc.core.resources.aa.useraccount.UserAccount;
import de.escidoc.core.resources.common.MetadataRecords;
import de.escidoc.core.resources.common.Result;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.resources.common.properties.Properties;
import de.escidoc.core.resources.common.structmap.StructMap;
import de.escidoc.core.resources.om.container.Container;
import de.escidoc.core.resources.om.context.Context;
import de.escidoc.core.resources.om.item.Item;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * Test un-/marshalling the resources.
 * 
 * @author SWA
 * 
 */
public class TestMarshalling extends EscidocClientTestBase {

    /**
     * Test un-/marshalling Item.
     * 
     * @throws Exception
     *             Thrown if un-/marshalling failed.
     */
    public void testMarshallingItem01() throws Exception {

        Item item = new Item();
        String xml = Factory.getItemMarshaller().marshalDocument(item);
        Factory.getItemMarshaller().unmarshalDocument(xml);
    }

    /* ********************************************************************** */

    /**
     * Test un-/marshalling Container.
     * 
     * @throws Exception
     *             Thrown if un-/marshalling failed.
     */
    public void testMarshallingContainer01() throws Exception {

        Container container = new Container();
        String xml =
            Factory.getContainerMarshaller().marshalDocument(container);
        Factory.getContainerMarshaller().unmarshalDocument(xml);
    }

    /**
     * Test un-/marshalling Container.
     * 
     * @throws Exception
     *             Thrown if un-/marshalling failed.
     */
    public void testMarshallingContainer02() throws Exception {

        Container container = new Container();
        StructMap structMap = new StructMap();
        container.setStructMap(structMap);
        String xml =
            Factory.getContainerMarshaller().marshalDocument(container);
        Factory.getContainerMarshaller().unmarshalDocument(xml);
    }

    /* ********************************************************************** */

    /**
     * Test un-/marshalling Context.
     * 
     * @throws Exception
     *             Thrown if un-/marshalling failed.
     */
    public void testMarshallingContext01() throws Exception {

        Context context = new Context();
        String xml = Factory.getContextMarshaller().marshalDocument(context);
        Factory.getContextMarshaller().unmarshalDocument(xml);
    }

    /* ********************************************************************** */

    /**
     * Test un-/marshalling Result.
     * 
     * @throws Exception
     *             Thrown if un-/marshalling failed.
     */
    public void testMarshallingResult01() throws Exception {

        Result result = new Result();
        String xml = Factory.getResultMarshaller().marshalDocument(result);
        Factory.getResultMarshaller().unmarshalDocument(xml);
    }

    /* ********************************************************************** */

    /**
     * Test un-/marshalling TaskParam.
     * 
     * @throws Exception
     *             Thrown if un-/marshalling failed.
     */
    public void testMarshallingTaskParam01() throws Exception {

        TaskParam taskParam = new TaskParam();
        String xml =
            Factory.getTaskParamMarshaller().marshalDocument(taskParam);
        Factory.getTaskParamMarshaller().unmarshalDocument(xml);
    }

    /* ********************************************************************** */

    /**
     * Test un-/marshalling StructMap.
     * 
     * @throws Exception
     *             Thrown if un-/marshalling failed.
     */
    public void testMarshallingStructMap01() throws Exception {

        StructMap structMap = new StructMap();
        String xml =
            Factory.getStructMapMarshaller().marshalDocument(structMap);
        Factory.getStructMapMarshaller().unmarshalDocument(xml);
    }

    /* ********************************************************************** */

    /**
     * Test un-/marshalling MetadataRecords.
     * 
     * @throws Exception
     *             Thrown if un-/marshalling failed.
     */
    public void testMarshallingMetadataRecords01() throws Exception {

        MetadataRecords metadataRecords = new MetadataRecords();
        String xml =
            Factory.getMetadataRecordsMarshaller().marshalDocument(
                metadataRecords);
        Factory.getMetadataRecordsMarshaller().unmarshalDocument(xml);
    }

    /* ********************************************************************** */

    /**
     * Test un-/marshalling Properties.
     * 
     * @throws Exception
     *             Thrown if un-/marshalling failed.
     */
    public void testMarshallingProperties01() throws Exception {

        Properties properties = new Properties();
        String xml =
            Factory.getPropertiesMarshaller().marshalDocument(properties);
        Factory.getPropertiesMarshaller().unmarshalDocument(xml);
    }

    /* ********************************************************************** */

    /**
     * Test un-/marshalling UserAccount.
     * 
     * @throws Exception
     *             Thrown if un-/marshalling failed.
     */
    public void testMarshallingUserAccount01() throws Exception {

        UserAccount userAccount = new UserAccount();
        String xml =
            Factory.getUserAccountMarshaller().marshalDocument(userAccount);
        Factory.getUserAccountMarshaller().unmarshalDocument(xml);
    }

    /* ********************************************************************** */

    /**
     * Test un-/marshalling PropertiesUserAccount.
     * 
     * @throws Exception
     *             Thrown if un-/marshalling failed.
     */
    public void testMarshallingUserAccountProperties01() throws Exception {

        PropertiesUserAccount userAccountProperties =
            new PropertiesUserAccount();
        String xml =
            Factory.getUserAccountPropertiesMarshaller().marshalDocument(
                userAccountProperties);
        Factory.getUserAccountPropertiesMarshaller().unmarshalDocument(xml);
    }

    /* ********************************************************************** */
}