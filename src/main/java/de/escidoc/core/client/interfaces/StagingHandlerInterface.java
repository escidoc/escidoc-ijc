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
package de.escidoc.core.client.interfaces;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;

/**
 * Interface for Staging Service.
 * 
 * @author SWA
 * 
 */
public interface StagingHandlerInterface {

    /**
     * Upload a resource.
     * 
     * @return XML response
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    URL upload(final File f) throws EscidocException,
        InternalClientException, TransportException;

    /**
     * Upload a resource.
     * 
     * @return XML response
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    URL upload(final InputStream in) throws EscidocException,
        InternalClientException, TransportException;

    /**
     * Set the Service Address.
     * 
     * @param handle
     *            The String containing the Service Address of eSciDocCore).
     * @throws InternalClientException
     *             Thrown if setting failed.
     */
    void setServiceAddress(final String address) throws InternalClientException;

}