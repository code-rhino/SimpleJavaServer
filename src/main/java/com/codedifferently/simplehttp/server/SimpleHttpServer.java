package com.codedifferently.simplehttp.server;

import com.codedifferently.simplehttp.server.request.RequestHandler;
import com.codedifferently.simplehttp.server.request.mapper.MappingRouter;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

public class SimpleHttpServer implements Server{
    private ServerSocket serverSocket;
    private final ExecutorService threadPool;
    private final MappingRouter mappingRouter;

    public SimpleHttpServer(ServerSocket serverSocket, ExecutorService threadPool) {
        this.threadPool = threadPool;
        this.serverSocket = serverSocket;
        this.mappingRouter = new MappingRouter();
    }

    @Override
    public void start() throws IOException {
        while(!serverSocket.isClosed()){
            Socket clientSocket = serverSocket.accept();
            threadPool.execute(new RequestHandler(mappingRouter, clientSocket));
        }
    }
}
