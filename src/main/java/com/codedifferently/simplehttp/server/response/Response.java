package com.codedifferently.simplehttp.server.response;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Response {
    private ResponseCode code;
    private byte[] body;
    private Map<String, String> header = new HashMap<>();
    private String contentType;
    private long fileLength;

    public Response(){}

    public Response(ResponseCode code) {
        this.code = code;
    }

    public void setCode(ResponseCode code){
        this.code = code;
    }

    public ResponseCode getCode() {
        return code;
    }

    public void setBody(String body) {
        if (body != null) {
            this.body = body.getBytes();
        }
    }

    public void setBody(byte[] body){
        this.body = body;
    }

    public byte[] getBody() {
        return body;
    }

    public void addHeader(String type, String value) {
        header.put(type, value);
    }

    public int getContentLength(){
        return (body == null) ? 0 : body.length;
    }

    public Map<String, String> getHeader() {
        return header;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentType() {
        return contentType;
    }

    public void setFileLength(long fileSize) {
        this.fileLength = fileSize;
    }

    public long getFileLength() {
        return fileLength;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Response response = (Response) o;

        if (code != response.code) return false;
        if (fileLength != response.fileLength) return false;
        if (!Arrays.equals(body, response.body)) return false;
        if (!Objects.equals(contentType, response.contentType))
            return false;
        if (!Objects.equals(header, response.header)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = code.getCode();
        result = 31 * result + (body != null ? Arrays.hashCode(body) : 0);
        result = 31 * result + (header != null ? header.hashCode() : 0);
        result = 31 * result + (contentType != null ? contentType.hashCode() : 0);
        result = 31 * result + (int) (fileLength ^ (fileLength >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Response{" +
                "code=" + code +
                ", body=" + Arrays.toString(body) +
                ", header=" + header +
                ", contentType='" + contentType + '\'' +
                ", fileLength=" + fileLength +
                '}';
    }

}

