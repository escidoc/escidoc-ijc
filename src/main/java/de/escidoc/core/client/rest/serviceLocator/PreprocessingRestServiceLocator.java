package de.escidoc.core.client.rest.serviceLocator;

import static de.escidoc.core.common.Precondition.checkNotNull;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.application.invalid.XmlCorruptedException;
import de.escidoc.core.client.exceptions.application.invalid.XmlSchemaValidationException;
import de.escidoc.core.client.exceptions.application.missing.MissingMethodParameterException;
import de.escidoc.core.client.exceptions.application.security.AuthenticationException;
import de.escidoc.core.client.exceptions.application.security.AuthorizationException;
import de.escidoc.core.client.exceptions.system.SystemException;
import de.escidoc.core.client.interfaces.handler.PreprocessingHandler;

public class PreprocessingRestServiceLocator extends RestServiceMethod implements PreprocessingHandler {

    public static final String PATH = "/statistic/preprocessing";

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.sm.PreprocessingHandler#preprocess(java.lang.String,
     * java.lang.String)
     */
    @Override
    public void preprocess(final String aggregationDefinitionId, final String xmlData) throws EscidocException,
        XmlSchemaValidationException, SystemException, XmlCorruptedException, AuthorizationException,
        AuthenticationException, MissingMethodParameterException {

        checkNotNull(aggregationDefinitionId);
        checkNotNull(xmlData);

        post(PATH + "/" + aggregationDefinitionId, xmlData);
    }
}