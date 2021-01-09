package com.codedifferently.simplehttp.server.request.mapper.html;

import com.codedifferently.simplehttp.server.request.Request;
import com.codedifferently.simplehttp.server.request.enums.HttpMethod;
import com.codedifferently.simplehttp.server.request.mapper.RouteMapper;
import com.codedifferently.simplehttp.server.response.Response;
import com.codedifferently.simplehttp.server.response.ResponseCode;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HtmlRouteMapper implements RouteMapper {
    @Override
    public Response processRequest(Request request) {
        Response response = new Response();
        if(HttpMethod.GET != request.getHttpMethod())
            response.setCode(ResponseCode.METHOD_NOT_ALLOW);
        else
            buildRequestAndHandleException(request, response);
        return response;
    }

    private void buildRequestAndHandleException(Request request, Response response) {
        try {
            buildRequest(request, response);
        } catch (Exception e) {
            response.setCode(ResponseCode.NOT_FOUND);
        }
    }

    private void buildRequest(Request request, Response response) throws IOException, URISyntaxException {
        setBody(request, response);
        response.setCode(ResponseCode.OK);
    }

    private Response setBody(Request request, Response response) throws IOException, URISyntaxException {
        File file = HtmlFileDirectoryBuilder.getFile(request.getPath());
        String content = readFile(file.getPath(), StandardCharsets.US_ASCII);
        response.setBody(content);
        return response;
    }

    private String readFile(String path , Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}
