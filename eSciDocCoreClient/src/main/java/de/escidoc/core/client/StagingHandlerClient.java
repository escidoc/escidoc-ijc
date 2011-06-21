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
package de.escidoc.core.client;

import static de.escidoc.core.common.Precondition.checkNotNull;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.StagingHandlerClientInterface;
import de.escidoc.core.client.rest.RestStagingHandlerClient;

/**
 * Handler for Staging Service. REST only!
 * 
 * @author SWA
 * 
 */
public class StagingHandlerClient extends AbstractHandlerClient<RestStagingHandlerClient>
    implements StagingHandlerClientInterface {

    /**
     * 
     * @param serviceAddress
     */
    public StagingHandlerClient(final URL serviceAddress) {
        super(serviceAddress);
    }

    /**
     * 
     * @param serviceAddress
     * @deprecated Use {@link StagingHandlerClient#StagingHandlerClient(URL)}
     *             instead.
     */
    @Deprecated
    public StagingHandlerClient(final String serviceAddress) {
        super(serviceAddress);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.StagingHandlerClientInterface#upload
     * (java.io.File)
     */
    @Override
    public URL upload(final File f) throws EscidocException, InternalClientException, TransportException {

        checkNotNull(f);

        return getClient().upload(f);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.StagingHandlerClientInterface#upload
     * (java.io.InputStream)
     */
    @Override
    public URL upload(final InputStream ins) throws EscidocException, InternalClientException, TransportException {

        checkNotNull(ins);

        return getClient().upload(ins);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.AbstractHandlerClient#getRestHandlerClientInstance
     * ()
     */
    @Override
    protected RestStagingHandlerClient getRestHandlerClientInstance() throws InternalClientException {
        return new RestStagingHandlerClient(getServiceAddress());
    }
}