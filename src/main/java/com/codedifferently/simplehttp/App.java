package com.codedifferently.simplehttp;


import com.codedifferently.simplehttp.server.Server;
import com.codedifferently.simplehttp.server.SimpleHttpServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class App {

    private static final Logger logger = Logger.getGlobal();

    public static void main(String[] args) {
        try {
            startServer();
        }catch (Exception e){
            handleException(e);
        }
    }

    private static void startServer() throws IOException{
        ServerSocket serverSocket = new ServerSocket(5000);
        ExecutorService threadPool = Executors.newCachedThreadPool();
        logger.info("Starting Server on Port 5000");
        Server server  = new  SimpleHttpServer(serverSocket, threadPool);
        server.start();
    }

    private static void handleException(Exception e){
        logger.info("Failed to start server");
        e.printStackTrace();
        System.exit(1);
    }

}
