/**
 * 
 */
package de.escidoc.core.resources;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;

/**
 * @author MVO
 * 
 */
public class HttpInputStream extends InputStream {

    private final HttpRequestBase requestBase;

    private final HttpResponse response;

    private final InputStream inputStream;

    private final String contentEncoding;

    private final String contentType;

    private final long contentLength;

    /**
     * 
     * @param requestBase
     * @param response
     * @throws IllegalStateException
     * @throws IOException
     */
    public HttpInputStream(final HttpRequestBase requestBase,
        final HttpResponse response) throws IllegalStateException, IOException {

        this.requestBase = requestBase;
        this.inputStream = response.getEntity().getContent();
        this.contentEncoding =
            response.getEntity().getContentEncoding().getValue();
        this.contentType = response.getEntity().getContentType().getValue();
        this.contentLength = response.getEntity().getContentLength();
        this.response = response;
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
    public int read(final byte b[], final int off, final int len)
        throws IOException {
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

    @Override
    public void close() throws IOException {
        inputStream.close();
        response.getEntity().consumeContent();
        requestBase.abort();
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
        return contentEncoding;
    }

    /**
     * @return the contentType
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * @return the contentLength
     */
    public long getContentLength() {
        return contentLength;
    }
}
