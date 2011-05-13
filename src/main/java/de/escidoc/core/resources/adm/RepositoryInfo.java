/**
 * 
 */
package de.escidoc.core.resources.adm;

import de.escidoc.core.resources.common.Properties;

/**
 * @author MVO
 * 
 */
public class RepositoryInfo extends Properties {

    /**
     * 
     */
    private static final long serialVersionUID = 3262263521542347206L;

    public static final String KEY_SCHEMA_CONTAINER = "container";

    public static final String KEY_SCHEMA_CONTEXT = "context";

    public static final String KEY_SCHEMA_ORGANIZATIONAL_UNIT = "organizational-unit";

    public static final String KEY_SCHEMA_USER_ACCOUNT = "user-account";

    public static final String KEY_SCHEMA_ITEM = "item";

    public static final String KEY_ESCIDOC_CORE_EARLIEST_DATE = "escidoc-core.earliest-date";

    public static final String KEY_ESCIDOC_CORE_BASEURL = "escidoc-core.baseurl";

    public static final String KEY_ESCIDOC_CORE_DATABASE_VERSION = "escidoc-core.database.version";

    public static final String KEY_ESCIDOC_CORE_ADMIN_EMAIL = "escidoc-core.admin-email";

    public static final String KEY_ESCIDOC_CORE_OM_CONTENT_CHECKSUM_ALGORITHM =
        "escidoc-core.om.content.checksum-algorithm";

    public static final String KEY_ESCIDOC_CORE_DATABASE_CONSISTENT = "escidoc-core.database.consistent";

    public static final String KEY_ESCIDOC_CORE_BUILD = "escidoc-core.build";

    public static final String KEY_ESCIDOC_CORE_REPOSITORY_NAME = "escidoc-core.repository-name";

    public static final String KEY_GSEARCH_URL = "gsearch.url";

    /**
     * 
     * @param properties
     */
    public RepositoryInfo(final Properties properties) {
        this.putAll(properties);
    }
}
