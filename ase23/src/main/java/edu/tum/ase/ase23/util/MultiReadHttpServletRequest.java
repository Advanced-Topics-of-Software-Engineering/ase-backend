package edu.tum.ase.ase23.util;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.apache.commons.io.IOUtils;

import java.io.*;

public class MultiReadHttpServletRequest extends HttpServletRequestWrapper {

    private byte[] body;

    public MultiReadHttpServletRequest(HttpServletRequest httpServletRequest) throws IOException {
        super(httpServletRequest);
        // Read the request body and save it as a byte array
        InputStream is = super.getInputStream();
        body = IOUtils.toByteArray(is);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        return new ServletInputStreamImpl(new ByteArrayInputStream(body));
    }

    @Override
    public BufferedReader getReader() throws IOException {
        String enc = getCharacterEncoding();
        if(enc == null) enc = "UTF-8";
        return new BufferedReader(new InputStreamReader(getInputStream(), enc));
    }

    private class ServletInputStreamImpl extends ServletInputStream {

        private InputStream is;

        public ServletInputStreamImpl(InputStream is) {
            this.is = is;
        }

        public int read() throws IOException {
            return is.read();
        }

        public boolean markSupported() {
            return false;
        }

        public synchronized void mark(int i) {
            throw new RuntimeException(new IOException("mark/reset not supported"));
        }

        public synchronized void reset() throws IOException {
            throw new IOException("mark/reset not supported");
        }

        @Override
        public boolean isFinished() {
            return false;
        }

        @Override
        public boolean isReady() {
            return false;
        }

        @Override
        public void setReadListener(ReadListener readListener) {

        }
    }

}
