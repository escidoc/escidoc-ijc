/**
 * 
 */
package de.escidoc.core.resources.sb;

import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.impl.UnmarshallingContext;

import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.client.TransportProtocol;

/**
 * Read-only class.
 * 
 * This class is a representation of the configInfo of the response of an
 * explain request.
 * 
 * This class may be initialized either by a SOAP response instance or by JiBX
 * if and only if the REST protocol is being used for the explain request.
 * 
 * @author MVO
 * 
 */
@JiBX
public abstract class Response {

    protected String version;

    protected TransportProtocol protocol;

    @JiBX
    protected Response() {
    }

    /**
     * Constructor for SOAP response.
     * 
     * @param version
     */
    protected Response(final String version) {
        this.version = version;
    }

    public String getVersion() {
        return this.version;
    }

    /**
     * @return the protocol
     */
    public TransportProtocol getProtocol() {
        return protocol;
    }

    /**
     * This method will be called by the binding definition as post-set method.
     * 
     * @param ictx
     */
    protected void setProtocol(final IUnmarshallingContext ictx) {
        UnmarshallingContext ctx = (UnmarshallingContext) ictx;
        protocol = TransportProtocol.valueOf(ctx.getFactory().getBindingName());
    }
}
