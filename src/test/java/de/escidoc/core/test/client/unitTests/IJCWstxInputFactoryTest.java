/**
 * 
 */
package de.escidoc.core.test.client.unitTests;

import static org.junit.Assert.assertTrue;

import javax.xml.stream.XMLInputFactory;

import org.junit.Test;

import de.escidoc.core.common.jibx.IJCWstxInputFactory;

/**
 * @author Marko Voß
 * 
 */
public class IJCWstxInputFactoryTest {

    /**
     * Test if the test resource
     * <tt>/META-INF/services/javax.xml.stream.XMLInputFactory</tt> does
     * configure the {@link IJCWstxInputFactory} to be used.
     * 
     * @throws Exception
     */
    @Test
    public void testFactory01() throws Exception {
        assertTrue(XMLInputFactory.newInstance() instanceof IJCWstxInputFactory);
    }
}
