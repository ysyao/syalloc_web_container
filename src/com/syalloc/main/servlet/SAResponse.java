package com.syalloc.main.servlet;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Collection;
import java.util.Locale;

public class SAResponse implements HttpServletResponse {

    private static final int BUFFER_SIZE = 1024;
    private SARequest request;
    private PrintWriter writer;
    private String contentType;
    private int status;

    public SAResponse(PrintWriter output) {
        this.writer = output;
    }

    public void setRequest(SARequest request) {
        this.request = request;
    }

    /* This method is used to serve a static page */
//    public void sendStaticResource() throws IOException {
////        byte[] bytes = new byte[BUFFER_SIZE];
////        FileInputStream fis = null;
////        try {
////            /* request.getUri has been replaced by request.getRequestURI */
////            File file = new File(Constants.WEB_ROOT, request.getUri());
////            fis = new FileInputStream(file);
////      /*
////         HTTP Response = Status-Line
////           *(( general-header | response-header | entity-header ) CRLF)
////           CRLF
////           [ message-body ]
////         Status-Line = HTTP-Version SP Status-Code SP Reason-Phrase CRLF
////      */
////            int ch = fis.read(bytes, 0, BUFFER_SIZE);
////            while (ch!=-1) {
////                output.write(bytes, 0, ch);
////                ch = fis.read(bytes, 0, BUFFER_SIZE);
////            }
////        }
////        catch (FileNotFoundException e) {
////            String errorMessage = "HTTP/1.1 404 File Not Found\r\n" +
////                    "Content-Type: text/html\r\n" +
////                    "Content-Length: 23\r\n" +
////                    "\r\n" +
////                    "<h1>File Not Found</h1>";
////            output.write(errorMessage.getBytes());
////        }
////        finally {
////            if (fis!=null)
////                fis.close();
////        }
////    }


    /** implementation of ServletResponse  */

    @Override
    public void addCookie(Cookie cookie) {

    }

    @Override
    public boolean containsHeader(String s) {
        return false;
    }

    @Override
    public String encodeURL(String s) {
        return null;
    }

    @Override
    public String encodeRedirectURL(String s) {
        return null;
    }

    @Override
    public String encodeUrl(String s) {
        return null;
    }

    @Override
    public String encodeRedirectUrl(String s) {
        return null;
    }

    @Override
    public void sendError(int i, String s) throws IOException {

    }

    @Override
    public void sendError(int i) throws IOException {

    }

    @Override
    public void sendRedirect(String s) throws IOException {

    }

    @Override
    public void setDateHeader(String s, long l) {

    }

    @Override
    public void addDateHeader(String s, long l) {

    }

    @Override
    public void setHeader(String s, String s1) {

    }

    @Override
    public void addHeader(String s, String s1) {

    }

    @Override
    public void setIntHeader(String s, int i) {

    }

    @Override
    public void addIntHeader(String s, int i) {

    }

    @Override
    public void setStatus(int i) {
        this.status = i;
    }

    @Override
    public void setStatus(int i, String s) {
        this.status = i;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public String getHeader(String s) {
        return null;
    }

    @Override
    public Collection<String> getHeaders(String s) {
        return null;
    }

    @Override
    public Collection<String> getHeaderNames() {
        return null;
    }

    @Override
    public String getCharacterEncoding() {
        return null;
    }

    @Override
    public String getContentType() {
        return contentType;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return null;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return writer;
    }

    @Override
    public void setCharacterEncoding(String s) {

    }

    @Override
    public void setContentLength(int i) {

    }

    @Override
    public void setContentLengthLong(long l) {

    }

    @Override
    public void setContentType(String s) {
        this.contentType = s;
    }

    @Override
    public void setBufferSize(int i) {

    }

    @Override
    public int getBufferSize() {
        return 0;
    }

    @Override
    public void flushBuffer() throws IOException {

    }

    @Override
    public void resetBuffer() {

    }

    @Override
    public boolean isCommitted() {
        return false;
    }

    @Override
    public void reset() {

    }

    @Override
    public void setLocale(Locale locale) {

    }

    @Override
    public Locale getLocale() {
        return null;
    }
}
