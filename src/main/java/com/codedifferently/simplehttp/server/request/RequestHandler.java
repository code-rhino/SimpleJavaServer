package com.codedifferently.simplehttp.server.request;

import com.codedifferently.simplehttp.server.request.mapper.MappingRouter;
import com.codedifferently.simplehttp.server.response.Response;
import com.codedifferently.simplehttp.server.response.ResponseWriter;

import java.io.IOException;
import java.net.Socket;

public class RequestHandler implements Runnable {
    private final MappingRouter processFactory;
    private final Socket clientSocket;


    public RequestHandler(MappingRouter mappingRouter, Socket clientSocket){
        this.processFactory = mappingRouter;
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            handleRequest();
        } catch (IOException e) {
            //Logger.error("Cannot handle request because " + e.getMessage(), e);
        } finally {
            closeSocket();
        }
    }

    private void closeSocket() {
        if (clientSocket != null){
            try {
                clientSocket.close();
            } catch (IOException e) {
                //  Logger.error("Cannot close socket because " + e.getMessage(), e);
            }
        }
    }

    private void handleRequest() throws IOException{
        Request request = parseRequest();
        Response response = processRequest(request);
        writeRequest(response);
        clientSocket.close();
    }

    private Request parseRequest() throws IOException {
        RequestParser requestParser = new RequestParser();
        return requestParser.parse(clientSocket.getInputStream());
    }

    private Response processRequest(Request request) {
        return processFactory.get(request.getPath()).processRequest(request);
    }

    private void writeRequest(Response response) throws IOException {
        ResponseWriter writer = new ResponseWriter();
        writer.write(clientSocket.getOutputStream(), response);
    }
}
