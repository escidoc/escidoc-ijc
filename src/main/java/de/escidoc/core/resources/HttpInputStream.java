/**
 * 
 */
package de.escidoc.core.resources;

import static de.escidoc.core.common.Precondition.checkNotNull;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpRequestBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author MVO
 * 
 */
public class HttpInputStream extends InputStream {

    private static final Logger LOG = LoggerFactory.getLogger(HttpInputStream.class);

    private final HttpRequestBase requestBase;

    private final HttpResponse response;

    private final InputStream inputStream;

    private String contentEncoding;

    private static final String CHARSET_PARAM = "charset";

    /**
     * 
     * @param requestBase
     * @param response
     * @throws IllegalStateException
     * @throws IOException
     */
    public HttpInputStream(final HttpRequestBase requestBase, final HttpResponse response)
        throws IllegalStateException, IOException {

        checkNotNull(requestBase);
        checkNotNull(response);

        this.requestBase = requestBase;
        this.response = response;
        if (response.getEntity() != null) {
            this.inputStream = response.getEntity().getContent();
        }
        else {
            this.inputStream = null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.io.InputStream#read()
     */
    @Override
    public int read() throws IOException {
        return inputStream.read();
    }

    @Override
    public int read(final byte b[], final int off, final int len) throws IOException {
        return inputStream.read(b, off, len);
    }

    @Override
    public long skip(final long n) throws IOException {
        return inputStream.skip(n);
    }

    @Override
    public int available() throws IOException {
        return inputStream.available();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.io.InputStream#close()
     */
    @Override
    public void close() throws IOException {
        try {
            inputStream.close();
        }
        catch (final IOException e) {
            LOG.warn("Unable to close InputStream.", e);
            throw e;
        }
        finally {
            try {
                if (response.getEntity() != null) {
                    response.getEntity().consumeContent();
                }
            }
            catch (final IOException e) {
                LOG.warn("Unable to consumeContent of HttpResponse.", e);
                throw e;
            }
            finally {
                requestBase.abort();
            }
        }
    }

    @Override
    public synchronized void mark(final int readlimit) {
        inputStream.mark(readlimit);
    }

    @Override
    public synchronized void reset() throws IOException {
        inputStream.reset();
    }

    @Override
    public boolean markSupported() {
        return inputStream.markSupported();
    }

    /**
     * @return the contentEncoding
     */
    public String getContentEncoding() {
        if (contentEncoding == null) {

            if (hasContentEncoding()) {
                contentEncoding = response.getEntity().getContentEncoding().getValue();
            }
            else if (hasContentType()) {

                final Header header = response.getEntity().getContentType();
                if (header.getElements() != null) {
                    for (int i = 0; i < header.getElements().length; i++) {
                        final NameValuePair pair = header.getElements()[i].getParameterByName(CHARSET_PARAM);
                        if (pair != null) {
                            contentEncoding = pair.getValue();
                            break;
                        }
                    }

                }
            }
        }
        return contentEncoding;
    }

    /**
     * @return the contentType
     */
    public String getContentType() {
        if (hasContentType())
            return response.getEntity().getContentType().getValue();
        else
            return null;
    }

    /**
     * @return the contentLength
     */
    public long getContentLength() {
        if (response.getEntity() != null)
            return response.getEntity().getContentLength();
        else
            return 0;
    }

    /**
     * @return
     */
    public boolean hasContentEncoding() {
        return response.getEntity() != null && response.getEntity().getContentEncoding() != null;
    }

    /**
     * @return
     */
    public boolean hasContentType() {
        return response.getEntity() != null && response.getEntity().getContentType() != null;
    }
}
