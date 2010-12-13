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
package de.escidoc.core.resources.interfaces.container;

import de.escidoc.core.resources.common.MetadataRecords;
import de.escidoc.core.resources.common.Relations;
import de.escidoc.core.resources.interfaces.GenericResourceInterface;
import de.escidoc.core.resources.oum.OrganizationalUnitProperties;

/**
 * @author SWA
 * 
 */
public interface ContainerInterface extends GenericResourceInterface {

    /**
     * @return The container properties.
     */
    OrganizationalUnitProperties getProperties();

    /**
     * Set the container properties.
     * 
     * @param properties
     *            The new container properties.
     */
    void setProperties(OrganizationalUnitProperties properties);

    /**
     * @return The md records.
     */
    MetadataRecords getMetadataRecords();

    /**
     * Set the md records.
     * 
     * @param mdRecords
     *            The new md records.
     */
    void setMdRecords(MetadataRecords metadataRecords);

    /**
     * @return The relations.
     */
    Relations getRelations();

    /**
     * Set the relations.
     * 
     * @param relations
     *            The new relations.
     */
    void setRelations(Relations relations);
}
