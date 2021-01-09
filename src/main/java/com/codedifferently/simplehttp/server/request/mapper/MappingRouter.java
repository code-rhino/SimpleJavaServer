package com.codedifferently.simplehttp.server.request.mapper;

import com.codedifferently.simplehttp.server.request.mapper.html.HtmlFileDirectoryBuilder;
import com.codedifferently.simplehttp.server.request.mapper.html.HtmlRouteMapper;
import com.codedifferently.simplehttp.server.request.mapper.notfound.NotFoundProcessor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public class MappingRouter {
    private final static RouteMapper DEFAULT_PROCESSOR = new NotFoundProcessor();
    private static final Logger logger = Logger.getGlobal();
    private Map<String, RouteMapper> routeMappings = new ConcurrentHashMap<String, RouteMapper>();

    public MappingRouter(){
        loadProcessorMapping();
    }

    public void loadProcessorMapping() {
        addStaticMappingsFromDirectory();
        addMappers();
    }

    private void addStaticMappingsFromDirectory(){
        RouteMapper routeMapper = new HtmlRouteMapper();
        for (String url : HtmlFileDirectoryBuilder.getPublicFileNames()){
            String routeUrl = "/" + url;
            logger.info("Get Mapping route: " + routeUrl);
            routeMappings.put(routeUrl, routeMapper);
        }
    }

    private void addMappers() {
        routeMappings.put("/", new HtmlRouteMapper());
    }

    public RouteMapper get(String path) {
        RouteMapper processor = routeMappings.get(path);
        if (processor == null){
            return DEFAULT_PROCESSOR;
        } else {
            return processor;
        }
    }
}
