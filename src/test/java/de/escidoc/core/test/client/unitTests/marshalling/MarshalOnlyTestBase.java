/**
 * 
 */
package de.escidoc.core.test.client.unitTests.marshalling;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

import de.escidoc.core.common.jibx.Marshaller;

/**
 * @author Marko Voß
 * 
 */
public abstract class MarshalOnlyTestBase<T> extends MarshallerTestBase<T> {

    public MarshalOnlyTestBase(final Class<T> clazz, final String basePath, final String xsdVersion,
        final String resourceFile) throws IOException, ParserConfigurationException, SAXException {
        super(clazz, basePath, xsdVersion, resourceFile);
    }

    /**
     * @throws Exception
     */
    @Override
    @Test
    public void testLifecycle() throws Exception {

        final Marshaller<T> m = Marshaller.getMarshaller(getResultType());
        final T obj = m.unmarshalDocument(getXML());
        setCheckMarshalledDoc(false);
        validate(obj);

        testSubResources(obj);

        testResourceWithoutSubResources(obj);
    }
}