package de.escidoc.core.resources.sb.explain;

public class ExplainRecord {
    private boolean authoritative;

    private ServerInfo serverInfo;

    private DatabaseInfo databaseInfo;

    private IndexInfo indexInfo;

    private Schema schema;

    private ConfigInfo configInfo;

    public boolean isAuthoritative() {
        return authoritative;
    }

    public void setAuthoritative(final boolean authoritative) {
        this.authoritative = authoritative;
    }

    public ServerInfo getServerInfo() {
        return serverInfo;
    }

    public void setServerInfo(final ServerInfo serverInfo) {
        this.serverInfo = serverInfo;
    }

    public DatabaseInfo getDatabaseInfo() {
        return databaseInfo;
    }

    public void setDatabaseInfo(final DatabaseInfo databaseInfo) {
        this.databaseInfo = databaseInfo;
    }

    public IndexInfo getIndexInfo() {
        return indexInfo;
    }

    public void setIndexInfo(final IndexInfo indexInfo) {
        this.indexInfo = indexInfo;
    }

    public Schema getSchema() {
        return schema;
    }

    public void setSchema(final Schema schema) {
        this.schema = schema;
    }

    public ConfigInfo getConfigInfo() {
        return configInfo;
    }

    public void setConfigInfo(final ConfigInfo configInfo) {
        this.configInfo = configInfo;
    }

}
