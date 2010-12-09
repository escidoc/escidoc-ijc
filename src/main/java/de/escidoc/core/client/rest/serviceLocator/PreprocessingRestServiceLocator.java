package de.escidoc.core.client.rest.serviceLocator;

import java.rmi.RemoteException;

import de.escidoc.core.common.exceptions.remote.application.invalid.XmlCorruptedException;
import de.escidoc.core.common.exceptions.remote.application.invalid.XmlSchemaValidationException;
import de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException;
import de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException;
import de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException;
import de.escidoc.core.common.exceptions.remote.system.SystemException;
import de.escidoc.core.sm.PreprocessingHandler;

public class PreprocessingRestServiceLocator extends RestServiceMethod
    implements PreprocessingHandler {

    private static final String PATH = "/statistic/preprocessing";

    @Override
    public void preprocess(
        final String aggregationDefinitionId, final String xmlData)
        throws RemoteException, XmlSchemaValidationException, SystemException,
        XmlCorruptedException, AuthorizationException, AuthenticationException,
        MissingMethodParameterException {

        if (aggregationDefinitionId == null)
            throw new IllegalArgumentException(
                "aggregationDefinitionId must not be null.");
        if (xmlData == null)
            throw new IllegalArgumentException("xmlData must not be null.");

        post(PATH + "/" + aggregationDefinitionId, xmlData);
    }
}