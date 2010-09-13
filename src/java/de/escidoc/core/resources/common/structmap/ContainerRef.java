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
package de.escidoc.core.resources.common.structmap;


/**
 * Reference to a Container.
 * 
 * @author SWA
 * 
 */
public class ContainerRef extends MemberRef {

    /**
     * ContainerRef.
     */
    public ContainerRef() {
        setResourceType(RESOURCE_TYPE.Container);
    }

    /**
     * ContainerRef.
     */
    public ContainerRef(final String refId) {

        super(refId);
        setResourceType(RESOURCE_TYPE.Container);
    }

	/**
	 * @param refId
	 * @param type
	 */
	public ContainerRef(String refId, RESOURCE_TYPE type) {
		super(refId, type);
	}

	/**
	 * @param refId
	 * @param href
	 * @param title
	 * @param resourceType
	 */
	public ContainerRef(String refId, String href, String title,
			RESOURCE_TYPE resourceType) {
		super(refId, href, title, resourceType);
	}

	/**
	 * @param refId
	 * @param href
	 * @param title
	 */
	public ContainerRef(String refId, String href, String title) {
		super(refId, href, title);
	}

	/**
	 * @param href
	 * @param title
	 */
	public ContainerRef(String href, String title) {
		super(href, title);
	}
}
