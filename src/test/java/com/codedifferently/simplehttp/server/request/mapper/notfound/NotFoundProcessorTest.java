package com.codedifferently.simplehttp.server.request.mapper.notfound;

import com.codedifferently.simplehttp.server.request.Request;
import com.codedifferently.simplehttp.server.response.Response;
import com.codedifferently.simplehttp.server.response.ResponseCode;
import org.junit.Assert;
import org.junit.Test;

public class NotFoundProcessorTest {



    @Test
    public void testProcess(){
        NotFoundProcessor processor = new NotFoundProcessor();
        Response response = new Response(ResponseCode.NOT_FOUND);
        Assert.assertEquals(response, processor.processRequest(new Request()));
    }

}
