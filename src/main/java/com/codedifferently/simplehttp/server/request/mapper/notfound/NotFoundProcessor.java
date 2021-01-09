package com.codedifferently.simplehttp.server.request.mapper.notfound;

import com.codedifferently.simplehttp.server.request.Request;
import com.codedifferently.simplehttp.server.request.mapper.RouteMapper;
import com.codedifferently.simplehttp.server.response.Response;
import com.codedifferently.simplehttp.server.response.ResponseCode;

public class NotFoundProcessor implements RouteMapper {
    @Override
    public Response processRequest(Request request) {
        return new Response(ResponseCode.NOT_FOUND);
    }
}
