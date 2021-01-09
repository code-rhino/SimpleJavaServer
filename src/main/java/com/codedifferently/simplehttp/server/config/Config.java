package com.codedifferently.simplehttp.server.config;

public class Config {
    public static final String PUBLIC_DIRECTORY = getRootDirectory();

    private static String getRootDirectory() {
        String directory = System.getProperty("resource_directory");
        if (directory == null) {
            directory = System.getProperty("user.dir") + "/static";
        }
        return directory;
    }

    public static final String ENCODE = "UTF-8";
}
