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
 * Copyright 2006-2010 Fachinformationszentrum Karlsruhe Gesellschaft
 * fuer wissenschaftlich-technische Information mbH and Max-Planck-
 * Gesellschaft zur Foerderung der Wissenschaft e.V.  
 * All rights reserved.  Use is subject to license terms.
 */
package de.escidoc.core.test.client.util;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Utility class to handle template.
 * 
 * @author SWA
 * 
 */
public class Template {

    public static final String TEMPLATES_PATH = "/templates";

    /**
     * Get FileInputStream from template.
     * 
     * @param path
     *            Path to template file
     * @return FileInputStream
     * 
     * @throws FileNotFoundException
     *             If file could not be found
     */
    public static InputStream load(final String subPath)
        throws FileNotFoundException {

        if (subPath == null)
            return null;

        if (subPath.indexOf('/') == 0) {
            return Template.class.getResourceAsStream(TEMPLATES_PATH + subPath);
        }
        else {
            return Template.class.getResourceAsStream(TEMPLATES_PATH + '/'
                + subPath);
        }

        // File f = null;
        // if(subPath.indexOf('/') == 0) {
        // f = new File(TEMPLATES_PATH + subPath);
        // } else {
        // f = new File(TEMPLATES_PATH + '/' + subPath);
        // }
        // return new FileInputStream(f);
    }

}
