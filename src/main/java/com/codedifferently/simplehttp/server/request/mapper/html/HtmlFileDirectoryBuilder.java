package com.codedifferently.simplehttp.server.request.mapper.html;

import com.codedifferently.simplehttp.server.config.Config;

import java.io.File;

public class HtmlFileDirectoryBuilder {
    public static String[] getPublicFileNames() {
        return getFile("").list();
    }

    public static File getFile(String path) {
        return new File(Config.PUBLIC_DIRECTORY + path);
    }
}
