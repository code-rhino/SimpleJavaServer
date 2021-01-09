package com.codedifferently.simplehttp.server.request.mapper;

import com.codedifferently.simplehttp.server.request.Request;
import com.codedifferently.simplehttp.server.response.Response;

public interface RouteMapper {
    Response processRequest(Request request);
}
