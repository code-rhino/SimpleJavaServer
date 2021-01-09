package com.codedifferently.simplehttp.server;

import com.codedifferently.simplehttp.server.request.RequestHandler;
import org.easymock.EasyMock;
import org.junit.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;

public class SimpleHttpServerTest {
    @Test
    public void testStart_whenSocketIsClosed() throws IOException {
        ServerSocket serverSocket = getMockServerSocket(true);
        EasyMock.replay(serverSocket);

        Server server = new SimpleHttpServer(serverSocket, null);
        server.start();

        EasyMock.verify(serverSocket);
    }

    @Test
    public void testStart_whenSocketIsOpen() throws IOException {
        ServerSocket serverSocket = getMockServerSocket(false, true);
        EasyMock.expect(serverSocket.accept()).andReturn(null);
        EasyMock.replay(serverSocket);

        ExecutorService threadPool = getExecutorService();

        Server server = new SimpleHttpServer(serverSocket, threadPool);
        server.start();

        EasyMock.verify(serverSocket, threadPool);
    }

    private ServerSocket getMockServerSocket(boolean... returnValues) {
        ServerSocket serverSocket = EasyMock.createMock(ServerSocket.class);
        for (boolean value : returnValues){
            EasyMock.expect(serverSocket.isClosed()).andReturn(value);
        }
        return serverSocket;
    }

    private ExecutorService getExecutorService() {
        ExecutorService threadPool = EasyMock.createMock(ExecutorService.class);
        threadPool.execute(EasyMock.anyObject(RequestHandler.class));
        EasyMock.expectLastCall().once();
        EasyMock.replay(threadPool);
        return threadPool;
    }
}
