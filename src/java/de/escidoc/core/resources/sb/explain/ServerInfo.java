package de.escidoc.core.resources.sb.explain;

/**
 * Read-only class.
 * 
 * This class is a representation of the configInfo of the response
 * of an explain request.
 * 
 * This class may be initialized either by a SOAP response instance
 * or by JiBX if and only if the REST protocol is being used for
 * the explain request.
 * 
 * @author ?, MVO
 *
 */
public class ServerInfo {
	
    private String protocol;

    private String host;

    private String port;

    private String database;
    
    private String version;
    
    private String method;

    public String getProtocol() {
        return protocol;
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public String getDatabase() {
        return database;
    }

	public String getVersion() {
		return version;
	}
	
	public String getMethod() {
		return method;
	}
}
