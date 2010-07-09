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

import java.io.IOException;

import de.escidoc.core.resources.om.item.Content;

/**
 * Content of Item component.
 * 
 * @author ROF, SWA
 * 
 */
public class ComponentContent extends Content {

    private String base64EncodedContent;

    /**
     * 
     * @param base64EncodedContent
     */
    public void setBase64EncodedContent(final String base64EncodedContent) {
        this.base64EncodedContent = base64EncodedContent;
    }

    /**
     * 
     * @return
     */
    public String getBase64EncodedContent() {
        return base64EncodedContent;
    }

    /**
     * 
     * @param inlineContent
     * @return
     */
    public String encodeBinaryContent(final byte[] inlineContent) {
        
        sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
        return encoder.encode(inlineContent);
    }

    /**
     * 
     * @param base64EncodedContent
     * @return
     * @throws IOException
     */
    public byte[] decodeBinaryContent(final String base64EncodedContent)
        throws IOException {
        
        sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
        return decoder.decodeBuffer(base64EncodedContent);
    }
}
