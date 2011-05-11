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
package de.escidoc.core.resources.oum;

import static de.escidoc.core.common.Precondition.checkNotNull;
import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.resources.common.reference.OrganizationalUnitRef;

/**
 * Organizational Unit as Predecessor reference.
 * 
 * @author SWA
 * 
 */
@JiBX
public class Predecessor extends OrganizationalUnitRef {

    private PredecessorForm form;

    /**
     * JiBX Constructor
     */
    @JiBX
    protected Predecessor() {
    }

    /**
     * @param objid
     * @param form
     *            succession form
     */
    public Predecessor(final String objid, final PredecessorForm form) {
        super(objid);

        checkNotNull(form);
        this.form = form;
    }

    /**
     * @param xLinkHref
     * @param xLinkTitle
     * @param form
     *            succession form
     */
    public Predecessor(final String xLinkHref, final String xLinkTitle,
        final PredecessorForm form) {
        super(xLinkHref, xLinkTitle);

        checkNotNull(form);
        this.form = form;
    }

    /**
     * Set form of successor.
     * 
     * @param form
     *            succession form
     */
    public void setForm(final PredecessorForm form) {
        this.form = form;
    }

    /**
     * @return
     */
    public PredecessorForm getForm() {
        return form;
    }
}