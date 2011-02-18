package de.escidoc.core.client.rest.serviceLocator;

import static de.escidoc.core.common.Precondition.checkNotNull;

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

    public static final String PATH = "/statistic/preprocessing";

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.sm.PreprocessingHandler#preprocess(java.lang.String,
     * java.lang.String)
     */
    @Override
    public void preprocess(
        final String aggregationDefinitionId, final String xmlData)
        throws RemoteException, XmlSchemaValidationException, SystemException,
        XmlCorruptedException, AuthorizationException, AuthenticationException,
        MissingMethodParameterException {

        checkNotNull(aggregationDefinitionId);
        checkNotNull(xmlData);

        post(PATH + "/" + aggregationDefinitionId, xmlData);
    }
}