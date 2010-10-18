/**
 * 
 */
package de.escidoc.core.test.client;

import java.util.Arrays;
import java.util.Collection;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import de.escidoc.core.client.TransportProtocol;

/**
 * @author MVO
 * 
 */
@RunWith(Parameterized.class)
public abstract class AbstractParameterizedTestBase {

    private static final Collection<Object[]> PARAMETERS = Arrays
        .asList(new Object[][] { /*{ TransportProtocol.SOAP },*/
            { TransportProtocol.REST } });

    protected final TransportProtocol transport;

    public AbstractParameterizedTestBase(final TransportProtocol transport) {
        this.transport = transport;
    }

    @Parameters
    public static Collection<Object[]> data() {
        return PARAMETERS;
    }
}
