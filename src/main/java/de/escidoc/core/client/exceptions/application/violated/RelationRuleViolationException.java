package de.escidoc.core.client.exceptions.application.violated;

import java.io.Serializable;

public class RelationRuleViolationException extends RuleViolationException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -3238677469977787592L;

    public RelationRuleViolationException() {
    }

    public RelationRuleViolationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public RelationRuleViolationException(final int httpStatusCode, final String httpStatusLine,
        final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public RelationRuleViolationException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}