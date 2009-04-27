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
package de.escidoc.core.resources.om.item.component;

import de.escidoc.core.resources.common.MetadataRecords;
import de.escidoc.core.resources.om.GenericResource;
import de.escidoc.core.resources.om.item.Content;

public class Component extends GenericResource {

    private ComponentProperties properties;

    protected Content content;

    public MetadataRecords mdRecords = null;

    public Component() {
    }

    public Component(final String id) {
        super(id);
    }

    /**
     * @return the content
     */
    public Content getContent() {
        return content;
    }

    /**
     * @param content
     *            the content to set
     */
    public void setContent(Content content) {
        this.content = content;
    }

    /**
     * @return the mdRecords
     */
    public MetadataRecords getMetadataRecords() {
        return mdRecords;
    }

    /**
     * @param mdRecords
     *            the mdRecords to set
     */
    public void setMetadataRecords(MetadataRecords mdRecords) {
        this.mdRecords = mdRecords;
    }

    public ComponentProperties getProperties() {
        return properties;
    }

    public void setProperties(ComponentProperties properties) {
        this.properties = properties;
    }

}
