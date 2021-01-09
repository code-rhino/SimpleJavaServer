package com.codedifferently.simplehttp.server.request.mapper.html;

import com.codedifferently.simplehttp.mocks.MockHtmlFile;
import com.codedifferently.simplehttp.server.request.Request;
import com.codedifferently.simplehttp.server.request.enums.HttpMethod;
import com.codedifferently.simplehttp.server.response.Response;
import com.codedifferently.simplehttp.server.response.ResponseCode;
import  org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.nio.charset.StandardCharsets;

public class HtmlRouteMapperTest {
    HtmlRouteMapper processor;

    @Before
    public void setUp(){
        processor = new HtmlRouteMapper();
    }

    @Test
    public void testProcess_whenRequestMethodIsNotGet() throws Exception {
        Request request = new Request();
        request.setMethod(HttpMethod.POST);

        Response response = new Response(ResponseCode.METHOD_NOT_ALLOW);
        Assert.assertEquals("",response, processor.processRequest(request));
    }

    @Test
    public void testProcess_whenFileDoesNotExist(){
        Request request = createGetRequest("random path");

        Response response = new Response(ResponseCode.NOT_FOUND);
        Assert.assertEquals(response, processor.processRequest(request));
    }

    @Test
    public void testProcess_whenFileExist() throws Exception {
        assertProcessReturns200Response("/HelloWorld.html", null);
    }

    private void assertProcessReturns200Response(String filePath, String contentType) throws Exception {
        Request request = createGetRequest(filePath);
        Response response = create200Response(filePath, contentType);

        Assert.assertEquals(response, processor.processRequest(request));
    }


    private Response create200Response(String filePath, String contentType) throws Exception {
        Response response = new Response(ResponseCode.OK);
        response.setBody(MockHtmlFile.readFile(filePath, StandardCharsets.US_ASCII));
        response.setContentType(contentType);
        response.setFileLength(0);
        return response;
    }

    private Request createGetRequest(String filePath) {
        Request request = new Request();
        request.setMethod(HttpMethod.GET);
        request.setPath(filePath);
        return request;
    }


}

