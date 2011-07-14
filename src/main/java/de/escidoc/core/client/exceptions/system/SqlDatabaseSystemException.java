package de.escidoc.core.client.exceptions.system;

import java.io.Serializable;

public class SqlDatabaseSystemException extends SystemException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 4845883726312385889L;

    public SqlDatabaseSystemException() {
    }

    public SqlDatabaseSystemException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public SqlDatabaseSystemException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public SqlDatabaseSystemException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}