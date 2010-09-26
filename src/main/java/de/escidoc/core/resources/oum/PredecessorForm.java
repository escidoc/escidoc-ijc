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
 * Copyright 2010 Fachinformationszentrum Karlsruhe Gesellschaft
 * fuer wissenschaftlich-technische Information mbH and Max-Planck-
 * Gesellschaft zur Foerderung der Wissenschaft e.V.
 * All rights reserved.  Use is subject to license terms.
 */
package de.escidoc.core.resources.oum;

import de.escidoc.core.client.exceptions.EscidocClientException;
import de.escidoc.core.client.exceptions.InternalClientException;

/**
 * Enumeration to describe all forms of Predecessors.
 * 
 * @author SWA
 */
public enum PredecessorForm {
    SPLITTING("splitting"), FUSION("fusion"), SPINOFF("spin-off"), AFFILIATION(
        "affiliation"), REPLACEMENT("replacement");

    private final String label;

    /**
     * Create a new object.
     * 
     * @param label
     *            object label
     */
    PredecessorForm(final String label) {
        this.label = label;
    }

    /**
     * Get the label of the object.
     * 
     * @return object label
     */
    @Override
    public String toString() {
        return label;
    }

    /**
     * Convert from String to enum type PredecessorForm.
     * 
     * @param form
     *            form as String
     * @return PredecessorForm
     * @throws EscidocClientException
     *             Thrown if form String has no equivalent enum type
     */
    public static PredecessorForm fromString(final String form)
        throws EscidocClientException {

        if (form.equals(PredecessorForm.AFFILIATION.toString())) {
            return PredecessorForm.AFFILIATION;
        }
        else if (form.equals(PredecessorForm.FUSION.toString())) {
            return PredecessorForm.FUSION;
        }
        else if (form.equals(PredecessorForm.SPINOFF.toString())) {
            return PredecessorForm.SPINOFF;
        }
        else if (form.equals(PredecessorForm.SPLITTING.toString())) {
            return PredecessorForm.SPLITTING;
        }
        else if (form.equals(PredecessorForm.REPLACEMENT.toString())) {
            return PredecessorForm.REPLACEMENT;
        }
        else {
            throw new InternalClientException("Unsupported successor form type");
        }

    }
}