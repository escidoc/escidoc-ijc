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

import de.escidoc.core.resources.ResourceRef;

/**
 * Abstract references for Container member.
 * 
 * @author SWA
 * 
 */
public abstract class MemberRef extends ResourceRef {

    /**
     * 
     * @param ref
     */
    protected MemberRef() {
    }

    /**
     * 
     * @param ref
     */
    protected MemberRef(final String ref) {
        super(ref);
    }

    protected MemberRef(String objid, RESOURCE_TYPE type, String title) {
		super(objid, type, title);
	}

    protected MemberRef(String objid, RESOURCE_TYPE type) {
		super(objid, type);
	}

    protected MemberRef(String objid, String href, String title,
			RESOURCE_TYPE resourceType) {
		super(objid, href, title, resourceType);
	}

    protected MemberRef(String objid, String href, String title) {
		super(objid, href, title);
	}

    protected MemberRef(String href, String title) {
		super(href, title);
	}
    
    
}