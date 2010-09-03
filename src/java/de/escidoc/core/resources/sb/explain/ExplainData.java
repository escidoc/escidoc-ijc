package de.escidoc.core.resources.sb.explain;

import de.escidoc.core.resources.sb.RecordData;


/**
 * 
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
public class ExplainData implements RecordData {
	
    private boolean authoritative;

    private ServerInfo serverInfo;

    private DatabaseInfo databaseInfo;

    private IndexInfo indexInfo;

    private Schema schema;

    private ConfigInfo configInfo;
    
    /**
     * Constructor for REST response.
     */
    protected ExplainData() {}
    
    public boolean isAuthoritative() {
        return authoritative;
    }

    public ServerInfo getServerInfo() {
        return serverInfo;
    }

    public DatabaseInfo getDatabaseInfo() {
        return databaseInfo;
    }

    public IndexInfo getIndexInfo() {
        return indexInfo;
    }

    public Schema getSchema() {
        return schema;
    }

    public ConfigInfo getConfigInfo() {
        return configInfo;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public void toString(StringBuilder builder) {
		builder.append("ExplainData [authoritative=");
		builder.append(authoritative);
		builder.append(", serverInfo=");
		
		builder.append("ServerInfo [database=");
		builder.append(serverInfo.getDatabase());
		builder.append(", host=");
		builder.append(serverInfo.getHost());
		builder.append(", port=");
		builder.append(serverInfo.getPort());
		builder.append(", method=");
		builder.append(serverInfo.getMethod());
		builder.append(", version=");
		builder.append(serverInfo.getVersion());
		builder.append("]");
		
		// TODO
		builder.append(", databaseInfo=");
		builder.append(databaseInfo);
		builder.append(", indexInfo=");
		builder.append(indexInfo);
		builder.append(", schema=");
		builder.append(schema);
		builder.append(", configInfo=");
		builder.append(configInfo);
		builder.append("]");
	}	
}
